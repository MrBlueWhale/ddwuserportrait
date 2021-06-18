
import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DataTypes, LongType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object test {
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
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"favorProducts":{"cf":"Recommendation", "col":"favorProducts", "type":"string"}
         |}
         |}""".stripMargin


    val logsDF: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    //logsDF.select('id, 'favorProducts).where('id==="26").show()
    logsDF.select('id, 'favorProducts).where('id==="18").show()


    spark.stop()
  }

}