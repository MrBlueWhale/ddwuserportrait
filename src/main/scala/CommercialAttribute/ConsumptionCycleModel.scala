package CommercialAttribute

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._

object ConsumptionCycleModel {
  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_orders"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"memberId":{"cf":"cf", "col":"memberId", "type":"string"},
         |"isTest":{"cf":"cf", "col":"isTest", "type":"string"},
         |"finishTime":{"cf":"cf", "col":"finishTime", "type":"string"}
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

    readDF.show()

    val result = readDF.where("isTest = false").groupBy('memberId).agg(max('finishTime+0).as("finishTime"))
    val userDiffTime = result.select('memberId,
      (datediff(current_timestamp(), from_unixtime('finishTime)) - 720).as('finishTime))


    val ConsumptionCycle = (date: String) => {
      val day = date.toInt
      var result:String = null
      if (day > 0 && day<=7){
        result="7日"
      }else if(day <= 15){
        result="2周"
      }else if(day <= 30){
        result="1月"
      }else if(day <= 60){
        result="2月"
      }else if(day <= 90){
        result="3月"
      }else if(day <= 120){
        result="4月"
      }else if(day <= 150){
        result="5月"
      }else if(day <= 180){
        result="6月"
      }else{
        result="超过半年"
      }
      result
    }
    spark.udf.register("ConsumptionCycle",ConsumptionCycle)

    userDiffTime.createTempView("tb")

    val Fina = spark.sql(
      """
        |select memberId as id,ConsumptionCycle(finishTime) as ConsumptionCycle from tb
      """.stripMargin)

    Fina.show()


    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"ConsumptionCycle":{"cf":"Commercial", "col":"ConsumptionCycle", "type":"string"}
         |}
         |}""".stripMargin

    Fina.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
