import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog

object interactModel {
  def main(args: Array[String]): Unit = {
    def catalog = s"""{
                     |"table":{"namespace":"default", "name":"user_profile"},
                     |"rowkey":"id",
                     |"columns":{
                     |"id":{"cf":"rowkey", "col":"id", "type":"string"},
                     |"marriage":{"cf":"cf", "col":"marriage", "type":"string"}
                     |}
                     |}""".stripMargin

    val spark = SparkSession.builder()
      .appName("shc test")
      .master("local[10]")
      .getOrCreate()

    spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()
      .show(false)
  }
}
