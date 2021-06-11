package BehaviorAttribute

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.expressions.{Window, WindowSpec}
import org.apache.spark.sql.functions._


object BrowsePageModel {
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

    val spark = SparkSession.builder()
      .appName("shc test")
      .master("local[10]")
      .getOrCreate()

    import spark.implicits._

    val readDF: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    val BrowsePage = (date: String) => {
      var result:String = null
      if (date.contains("index")){
        result = "首页"
      }else if(date.contains("toMemberLogin")||(date.contains("Login")&&date.contains("member"))){
        result = "登录页"
      }else if(date.contains("itemList")){
        result = "分类页"
      }else if(date.contains("product")){
        result = "商品页"
      }else if(date.contains("orderList")||date.contains("myorder")){
        result = "我的订单页"
      }else{
        result = "其他"
      }
      result
    }
    spark.udf.register("BrowsePage",BrowsePage)

    var result = readDF.select('global_user_id.as("id"),'loc_url)

    result.createTempView("tb")

    result = spark.sql(
      """
        |select id,BrowsePage(loc_url) as BrowsePage from tb
      """.stripMargin).toDF()

    result = result.where("BrowsePage != '其他'").groupBy('id,'BrowsePage).agg(count("*").as("number"))
   // result.show()
    val window:WindowSpec = Window.partitionBy('id).orderBy('number.desc)

    result = result.select('id,'BrowsePage,dense_rank() over window as "rank").where("rank = 1").drop("rank")
    result.orderBy('id).show()


    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"BrowsePage":{"cf":"Behavior", "col":"BrowsePage", "type":"string"}
         |}
         |}""".stripMargin

    result.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
