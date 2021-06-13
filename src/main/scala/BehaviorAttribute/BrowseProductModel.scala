package BehaviorAttribute

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._

object BrowseProductModel {
  def main(args: Array[String]): Unit = {
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


    def catalogGoods =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_goods"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"productId":{"cf":"cf", "col":"productId", "type":"string"},
         |"productType":{"cf":"cf", "col":"productType", "type":"string"}
         |}
         |}""".stripMargin

    val spark = SparkSession.builder()
      .appName("shc test")
      .master("local[10]")
      .getOrCreate()

    import spark.implicits._

    val readDF: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    val readGoods: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalogGoods)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    val url2Product = (date: String) => {
      var result:String = null
      if (date.contains("comment")){
        result = date.split('/').apply(5)
        result = result.split('.').apply(0)
      }else{
        result = date.split('/').apply(4)
        result = result.split('.').apply(0)
      }
      result
    }
    spark.udf.register("url2Product",url2Product)

    var result = readDF.select('global_user_id.as("id"),'loc_url).where("loc_url like '%product/%'")

    result.createTempView("tb")

    result = spark.sql(
      """
        |select id,url2Product(loc_url) as product from tb
      """.stripMargin).toDF()

    //result.show()


    val goods = readGoods.select('productId.as("product"),'productType)
    result = result.join(goods,result.col("product") === goods.col("product"))
      .select('id,'productType).distinct().orderBy('id)

    result.createTempView("tbw")
    result = spark.sql(
      """
        |select id,concat_ws(',',collect_set(productType)) as BrowseProduct
        |from tbw
        |group by id
      """.stripMargin)
    result.show()

//    result = result.where("BrowsePage != '其他'").groupBy('id,'BrowsePage).agg(count("*").as("number"))
//    // result.show()
//    val window:WindowSpec = Window.partitionBy('id).orderBy('number.desc)
//
//    result = result.select('id,'BrowsePage,dense_rank() over window as "rank").where("rank = 1").drop("rank")
//    result.orderBy('id).show()


    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"BrowseProduct":{"cf":"Behavior", "col":"BrowseProduct", "type":"string"}
         |}
         |}""".stripMargin

    result.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
