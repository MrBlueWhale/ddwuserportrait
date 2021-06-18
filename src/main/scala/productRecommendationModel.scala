
import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DataTypes, LongType}

import scala.util.Try

object productRecommendationModel {
  def main(args: Array[String]): Unit = {
    //Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
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

    val ratingDF = logsDF.select(
      'global_user_id.as("userId").cast(DataTypes.IntegerType),
      url2ProductId('loc_url).as("productId").cast(DataTypes.IntegerType)
    ).filter('productId.isNotNull)
      .groupBy('userId, 'productId)
      .agg(count('productId) as "rating")

    ratingDF.show(false)
    ratingDF.createTempView("tb")

    val befor = spark.sql("select userId as id,concat_ws(',',collect_set(productId)) as favorProductsTwo from tb group by userId").toDF()



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
    result  = result.select('id,'favorProducts).where('favorProducts.isNotNull)

//    val als = new ALS()
//      .setUserCol("userId")
//      .setItemCol("productId")
//      .setRatingCol("rating")
//      .setPredictionCol("predict")
//      .setColdStartStrategy("drop")
//      .setAlpha(10)
//      .setMaxIter(10)
//      .setRank(5)
//      .setRegParam(0.8)
//      .setImplicitPrefs(true)
//
//    val model: ALSModel = als.fit(ratingDF)
//
//    model.save("model/product/als")

//    val model = ALSModel.load("model/product/als")
//
//    val predict2StringFunc = udf(predict2String _)
//
//    // 为每个用户推荐
//    val result: DataFrame = model.recommendForAllUsers(10)
//      .withColumn("favorProducts", predict2StringFunc('recommendations))
//      .withColumnRenamed("userId", "id")
//      .drop('recommendations)
//      .select('id.cast(LongType), 'favorProducts)
//
//    result.show(100, false)
//
//    def recommendationCatalog =
//      s"""{
//         |  "table":{"namespace":"default", "name":"user_profile"},
//         |  "rowkey":"id",
//         |   "columns":{
//         |     "id":{"cf":"rowkey", "col":"id", "type":"Long"},
//         |     "favorProducts":{"cf":"cf", "col":"favorProducts", "type":"string"}
//         |   }
//         |}""".stripMargin
//
//    result.write
//      .option(HBaseTableCatalog.tableCatalog, recommendationCatalog)
//      .option(HBaseTableCatalog.newTable, "5")
//      .format("org.apache.spark.sql.execution.datasources.hbase")
//      .save()

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

  def sub(str1:String,str2:String): String ={
    val List1 = str1.split(",").flatMap(id => Try(id.trim).toOption)
    val List2 = str2.split(",").flatMap(id => Try(id.trim).toOption)
    List1.diff(List2).mkString(",")
  }

  def predict2String(arr: Seq[Row]) = {
    arr.map(_.getAs[Int]("productId")).mkString(",")
  }
}