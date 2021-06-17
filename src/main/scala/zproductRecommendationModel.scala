
import org.apache.log4j.{Level, Logger}
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

object zproductRecommendationModel {
  def main(args: Array[String]): Unit = {

    //设定spark的日志级别为warning，只是打印警告和错误信息
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
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

    logsDF.show()

    val product = logsDF.select(
      'global_user_id.as("memberId").cast(DataTypes.IntegerType),
      url2ProductId('loc_url).as("productId").cast(DataTypes.IntegerType)
    ).filter('productId.isNotNull)

    product.show(100,false)

    val ratingDF = logsDF.select(
      'global_user_id.as("userId").cast(DataTypes.IntegerType),
      url2ProductId('loc_url).as("productId").cast(DataTypes.IntegerType)
    ).filter('productId.isNotNull)
      .groupBy('userId, 'productId)
      .agg(count('productId) as "rating")

    ratingDF.show()

    val als = new ALS()
      .setUserCol("userId")
      .setItemCol("productId")
      .setRatingCol("rating")
      .setPredictionCol("predict")
      .setColdStartStrategy("drop")
      .setAlpha(10)
      .setMaxIter(10)
      .setRank(10)
      .setRegParam(1.0)
      .setImplicitPrefs(true)

    val model: ALSModel = als.fit(ratingDF)
//    // 将数据集切分为两份，其中训练集占80%(0.8), 测试集占20%(0.2)
//    val Array(trainSet, testSet) = ratingDF.randomSplit(Array(0.8, 0.2))
//    trainSet.show()
//
//    // 回归模型评测器
//    val evaluator: RegressionEvaluator = new RegressionEvaluator()
//      .setLabelCol("rating")
//      .setPredictionCol("predict")
//      .setMetricName("rmse")
//
//     //通过训练集进行训练，建立模型
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
////
////
////
////
////    val model: ALSModel = als.fit(ratingDF)
////
//    model.save("model/product/als")
//
    //val model = ALSModel.load("model/product/als")

    val predict2StringFunc = udf(predict2String _)

    // 为每个用户推荐
    val result: DataFrame = model.recommendForAllUsers(5)
      .withColumn("favorProducts", predict2StringFunc('recommendations))
      .withColumnRenamed("userId", "id")
      .drop('recommendations)
      .select('id.cast(LongType), 'favorProducts)

    result.show(100, false)

    //去重

    val resultDf = result.withColumn("arrayCol",split(col("favorProducts"),",")).withColumn("expCol",explode(col("arrayCol"))).select('id as "memberId", 'expCol as "productId")

    val resultDuplication = resultDf.except(product).select('memberId as "id", 'productId as "favorProducts")

    //resultDuplication.where('id>1000).show(100,false)
    resultDuplication.show(100,false)
    resultDuplication.createOrReplaceTempView("test");
    val Df1 = spark.sql(
      """
        |select id,concat_ws(',',collect_set(favorProducts)) as favorProducts from test group by id
      """.stripMargin)

    Df1.show(100,false)
    def recommendationCatalog =
      s"""{
         |  "table":{"namespace":"default", "name":"user_profile"},
         |  "rowkey":"id",
         |   "columns":{
         |     "id":{"cf":"rowkey", "col":"id", "type":"Long"},
         |     "favorProducts":{"cf":"Recommendation", "col":"favorProducts", "type":"string"}
         |   }
         |}""".stripMargin

    Df1.write
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