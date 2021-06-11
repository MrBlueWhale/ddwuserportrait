package BehaviorAttribute

import com.google.re2j.Pattern
import eu.bitwalker.useragentutils.{OperatingSystem, UserAgent}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.expressions.{Window, WindowSpec}
import org.apache.spark.sql.functions._

object DeviceTypeModel {
  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_logs"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"global_user_id":{"cf":"cf", "col":"global_user_id", "type":"string"},
         |"user_agent":{"cf":"cf", "col":"user_agent", "type":"string"},
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


    var result = readDF.select('global_user_id.as("id"),unix_timestamp('log_time).as("log_time"),'user_agent)

    result.show()

    val window:WindowSpec = Window.partitionBy('id).orderBy('log_time.desc)

    result = result.select('id,'user_agent,dense_rank() over window as "rank").where("rank = 1").drop("rank")
    result.show()


    val DeviceType = (data: String) => {

        val operatingSystem:OperatingSystem =  UserAgent.parseUserAgentString(data).getOperatingSystem
        val deviceType = operatingSystem.getGroup.getName

        deviceType

    }
    spark.udf.register("DeviceType",DeviceType)

    result.createTempView("tb")
    result = spark.sql(
      """
        |select id, DeviceType(user_agent) as DeviceType from tb
      """.stripMargin).toDF()


    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"DeviceType":{"cf":"Behavior", "col":"DeviceType", "type":"string"}
         |}
         |}""".stripMargin

    result.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
