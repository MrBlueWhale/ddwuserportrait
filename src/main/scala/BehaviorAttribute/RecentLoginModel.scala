package BehaviorAttribute

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions.{current_timestamp, datediff, from_unixtime, max}

object RecentLoginModel {
  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_users"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"lastLoginTime":{"cf":"cf", "col":"lastLoginTime", "type":"string"}
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

    readDF.na.fill("0",List("lastLoginTime"))

    val result = readDF.select('id,
      (datediff(current_timestamp(), from_unixtime('lastLoginTime)) - 720).as('lastLogin))

    result.show()


    val RecentLogin = (date: String) => {
      val day = date.toInt
      var result:String = null
      if (day > 0 && day<=1){
        result="1日内"
      }else if(day <= 7){
        result="7日内"
      }else if(day <= 14){
        result="14日内"
      }else if(day <= 30){
        result="30日内"
      }else{
        result="超过30日"
      }
      result
    }
    spark.udf.register("RecentLogin",RecentLogin)

    result.createTempView("tb")

    val Fina = spark.sql(
      """
        |select id,RecentLogin(lastLogin) as RecentLogin from tb
      """.stripMargin)

    Fina.show()


    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"RecentLogin":{"cf":"cf", "col":"RecentLogin", "type":"string"}
         |}
         |}""".stripMargin

    Fina.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
