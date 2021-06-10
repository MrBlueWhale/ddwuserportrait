import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._

object PoliticalFaceModel {
  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"tbl_users"},
         |"rowkey":"id",
         |"columns":{
         |"id":{"cf":"rowkey", "col":"id", "type":"string"},
         |"politicalFace":{"cf":"cf", "col":"politicalFace", "type":"string"}
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


    val result = readDF.select('id,when('politicalFace === 1,"群众")
    .when('politicalFace === 2,"党员")
      .otherwise("无党派人士").as("politicalFace")
    )
    //result.show()

        def catalogWrite =
          s"""{
             |"table":{"namespace":"default", "name":"user_profile"},
             |"rowkey":"id",
             |"columns":{
             |"id":{"cf":"rowkey", "col":"id", "type":"string"},
             |"politicalFace":{"cf":"cf", "col":"politicalFace", "type":"string"}
             |}
             |}""".stripMargin

        result.write
          .option(HBaseTableCatalog.tableCatalog, catalogWrite)
          .option(HBaseTableCatalog.newTable, "5")
          .format("org.apache.spark.sql.execution.datasources.hbase")
          .save()
  }
}
