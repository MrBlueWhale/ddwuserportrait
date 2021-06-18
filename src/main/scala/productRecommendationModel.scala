//商品推荐预测文件
//根据用户购买记录向用户推荐10商品
//针对用户已经购买过的商品，需要进行去重

import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DataTypes, LongType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object ProductRecommendationModel {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("GenderName")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    def logsCatalog =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_logs"},
         |  "rowkey":"id",
         |   "columns":{
         |     "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |     "global_user_id":{"cf":"cf", "col":"global_user_id", "type":"string"},
         |     "loc_url":{"cf":"cf", "col":"loc_url", "type":"string"}
         |   }
         |}""".stripMargin

    val url2ProductId = udf(getProductId _)

    val logsDF: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, logsCatalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    val ratingDF = logsDF.select(
      'global_user_id.as("userId").cast(DataTypes.IntegerType),
      url2ProductId('loc_url).as("productId").cast(DataTypes.IntegerType)
    ).filter('productId.isNotNull)
      .groupBy('userId, 'productId)
      .agg(count('productId) as "rating")

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

    val originDF = logsDF.select(
      'global_user_id.as("userId").cast(DataTypes.IntegerType),
      url2ProductId('loc_url).as("productId").cast(DataTypes.IntegerType)
    ).filter('productId.isNotNull)


    val modelLoad: ALSModel = als.fit(ratingDF)

    val predict2StringFunc = udf(predict2String _)

    //为每个用户推荐
    val result1: DataFrame = modelLoad.recommendForAllUsers(10)
      .withColumn("favorProducts", predict2StringFunc('recommendations))
      .withColumnRenamed("userId", "id")
      .drop('recommendations)
      .select('id.cast(LongType), 'favorProducts)

    //DEBUG
    //    val result3 = result1
    //      .withColumn("productId", explode(col("favorProducts")))
    //      .drop("favorProducts")
    //      .groupBy('id)
    //      .agg(count("productId"))
    //      .where("id <= 950")
    //      .orderBy("id")
    //      result3.show(950,false)

    var result2: DataFrame = result1
      .withColumn("productId", explode(col("favorProducts")))
      .drop("favorProducts")
      .select('id.as("userId").cast(DataTypes.IntegerType), 'productId)


    result2.createOrReplaceTempView("test");    result2 = result2.except(originDF)
      .select('userId.as("id")cast(DataTypes.StringType), 'productId)
      //        .groupBy('id)
      //        .agg(count("productId"))
      .where("id <= 950")

    result2 = spark.sql("select id,concat_ws(',',collect_set(productId)) as favorProducts from test group by id")

    result2.show(950,false)

    //写入HBase
    def recommendationCatalog =
      s"""{
         |  "table":{"namespace":"default", "name":"user_profile"},
         |  "rowkey":"id",
         |   "columns":{
         |     "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |     "favorProducts":{"cf":"Recommendation", "col":"favorProducts", "type":"string"}
         |   }
         |}""".stripMargin

    result2.write
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
    arr.map(_.getAs[Int]("productId")) //.mkString(",")
  }
}
