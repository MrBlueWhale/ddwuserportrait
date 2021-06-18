
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.ml.tuning.{CrossValidator, CrossValidatorModel, ParamGridBuilder}
import org.apache.spark.sql.execution.columnar.STRUCT
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DataTypes, LongType}

import scala.util.Try

object productRecommendationModel {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("GenderName")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_logs"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"global_user_id":{"cf":"cf", "col":"global_user_id", "type":"string"},
         |"loc_url":{"cf":"cf", "col":"loc_url", "type":"string"}
         |}
         |}""".stripMargin

    val url2ProductId = udf(getProductId _)

    val logsDF: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    //logsDF.show()

    val ratingDF = logsDF.select(
      'global_user_id.as("userId").cast(DataTypes.IntegerType),
      url2ProductId('loc_url).as("productId").cast(DataTypes.IntegerType)
    ).filter('productId.isNotNull)
      .groupBy('userId, 'productId)
      .agg(count('productId) as "rating")

    ratingDF.createTempView("tb")
    //ratingDF.show()
    val befor = spark.sql("select userId as id,concat_ws(',',collect_set(productId)) as favorProductsTwo from tb group by userId").toDF()
    //befor.show(false)

//    val als = new ALS()
//      .setUserCol("userId")
//      .setItemCol("productId")
//      .setRatingCol("rating")
//      .setPredictionCol("predict")
//      .setColdStartStrategy("drop")
//      .setAlpha(10)
//      .setMaxIter(10)
//      .setRank(10)
//      .setRegParam(1.0)
//      .setImplicitPrefs(true)
//
//    // 将数据集切分为两份，其中训练集占80%(0.8), 测试集占20%(0.2)
//    val Array(trainSet, testSet) = ratingDF.randomSplit(Array(0.8, 0.2))
//    trainSet.show()
//
//    // 回归模型评测器
//    val evaluator: RegressionEvaluator = new RegressionEvaluator()
//      .setLabelCol("rating")
//      .setPredictionCol("predict")
//      .setMetricName("rmse")

     //通过训练集进行训练，建立模型
//    val model: ALSModel = als.fit(trainSet)
//
//
//    // 通过模型进行预测
//    val predictions = model.transform(trainSet)
////
//    val rmse = evaluator.evaluate(predictions)
////
//    println(s"rmse value is ${rmse}")
////
////
//    model.transform(ratingDF).show()
//
//
//
//
//    val model: ALSModel = als.fit(ratingDF)
//
    //model.save("model/product/als")

    def sub(str1:String,str2:String): String ={
      val List1 = str1.split(",").flatMap(id => Try(id.trim).toOption)
      val List2 = str2.split(",").flatMap(id => Try(id.trim).toOption)
      List1.diff(List2).mkString(",")
    }
    val subFunc = udf(sub _)
    val model = ALSModel.load("model/product/als")

    val predict2StringFunc = udf(predict2String _)

    // 为每个用户推荐
    var result: DataFrame = model.recommendForAllUsers(10)
      .withColumn("favorProducts", predict2StringFunc('recommendations))
      .withColumnRenamed("userId", "id")
      .drop('recommendations)
      .select('id.cast(LongType), 'favorProducts)
    //result.show(false)

    result = result.join(befor,result.col("id") === befor.col("id"))
        .select(result.col("id"),
          befor.col("favorProductsTwo"),
          result.col("favorProducts"))

    result = result.select('id,subFunc('favorProducts,'favorProductsTwo).as("favorProducts"))
    result.show(100, false)


//

    def recommendationCatalog =
      s"""{
         |  "table":{"namespace":"default", "name":"user_profile"},
         |  "rowkey":"id",
         |   "columns":{
         |     "id":{"cf":"rowkey", "col":"id", "type":"Long"},
         |     "favorProducts":{"cf":"Recommendation", "col":"favorProducts", "type":"string"}
         |   }
         |}""".stripMargin

    result.write
      .option(HBaseTableCatalog.tableCatalog, recommendationCatalog)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()

    spark.stop()
  }

  def getProductId(url: String) = {
    var productId: String = null
    if (url.contains("/product/") && url.contains(".html")) {
      val start: Int = url.indexOf("/product/")
      val end: Int = url.indexOf(".html")
      if (end > start) {
        productId = url.substring(start + 9, end)
      }
    }
    productId
  }

  def predict2String(arr: Seq[Row]) = {
    arr.map(_.getAs[Int]("productId")).mkString(",")
  }
}