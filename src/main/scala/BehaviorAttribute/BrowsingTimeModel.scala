package BehaviorAttribute

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.expressions.{Window, WindowSpec}
import org.apache.spark.sql.functions._

object BrowsingTimeModel {
  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_logs"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"global_user_id":{"cf":"cf", "col":"global_user_id", "type":"string"},
         |"log_time":{"cf":"cf", "col":"log_time", "type":"string"}
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

    val logTime = (date: String) => {
      val day = date.toInt
      var result:String = null
      if(day >= 0 && day<1){
        result="22时--24时"
      }else if (day >= 1 && day<8){
        result="1时--7时"
      }else if(day < 13){
        result="8时--12时"
      }else if(day < 18){
        result="13时--17时"
      }else if(day < 22){
        result="18时--21时"
      }else if(day < 24){
        result="22时--24时"
      }
      result
    }
    spark.udf.register("logTime",logTime)

    var result = readDF.select('global_user_id.as("id"),
      date_format(col("log_time"),"h").as("log_time"))

    result.createTempView("tb")
    result = spark.sql("select id, logTime(log_time) as log_time from tb").toDF()
        .groupBy('id,'log_time).agg(count('*).as("number"))

    //result.show()

    val window:WindowSpec = Window.partitionBy('id).orderBy('number.desc)

    result = result.select('id,'log_time,dense_rank() over window as "rank").where("rank = 1").drop("rank")
    result.show()
    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"log_time":{"cf":"Behavior", "col":"log_time", "type":"string"}
         |}
         |}""".stripMargin

    result.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
