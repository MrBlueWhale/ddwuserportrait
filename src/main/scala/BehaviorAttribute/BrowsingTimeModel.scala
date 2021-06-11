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
      if (day >= 0 && day<2){
        result="0时--2时"
      }else if(day < 4){
        result="2时--4时"
      }else if(day < 6){
        result="4时--6时"
      }else if(day < 8){
        result="6时--8时"
      }else if(day < 10){
        result="8时--10时"
      }else if(day < 12){
        result="10时--12时"
      }else if(day < 14){
        result="12时--14时"
      }else if(day < 16){
        result="14时--16时"
      }else if(day < 18){
        result="16时--18时"
      }else if(day < 20){
        result="18时--20时"
      }else if(day < 22){
        result="20时--22时"
      }else{
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
