
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.{DataFrame, SparkSession}

object Trans {

  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_users"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"email":{"cf":"cf", "col":"email", "type":"string"},
         |"userName":{"cf":"cf", "col":"username", "type":"string"},
         |"mobile":{"cf":"cf", "col":"mobile", "type":"string"},
         |"qq":{"cf":"cf", "col":"qq", "type":"string"}
         |}
         |}""".stripMargin

    val spark = SparkSession.builder()
      .appName("shc test")
      .master("local[10]")
      .getOrCreate()

    val readDF: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    readDF.show()

    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"email":{"cf":"Population", "col":"email", "type":"string"},
         |"userName":{"cf":"Population", "col":"userName", "type":"string"},
         |"mobile":{"cf":"Population", "col":"mobile", "type":"string"},
         |"qq":{"cf":"Population", "col":"qq", "type":"string"}
         |}
         |}""".stripMargin

    readDF.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
