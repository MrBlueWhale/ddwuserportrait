package PopulationAttribute

import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.{DataFrame, SparkSession}

object AgeGroupModel {

    def main(args: Array[String]): Unit = {
      def catalog =
        s"""{
           |"table":{"namespace":"default", "name":"tbl_users"},
           |"rowkey":"id",
           |"columns":{
           |"id":{"cf":"rowkey", "col":"id", "type":"string"},
           |"ageGroup":{"cf":"cf", "col":"birthday", "type":"string"}
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

      val birth2AgeGroup = (date: String) => {
        val year = date.charAt(2)
        s"${year}0"
      }
      spark.udf.register("birth2AgeGroup",birth2AgeGroup)

      readDF.createTempView("tb")

      val result = spark.sql("select id, birth2AgeGroup(ageGroup) as ageGroup from tb").toDF()


      result.show()

      def catalogWrite =
        s"""{
           |"table":{"namespace":"default", "name":"user_profile"},
           |"rowkey":"id",
           |"columns":{
           |"id":{"cf":"rowkey", "col":"id", "type":"string"},
           |"ageGroup":{"cf":"Population", "col":"ageGroup", "type":"string"}
           |}
           |}""".stripMargin

      result.write
        .option(HBaseTableCatalog.tableCatalog, catalogWrite)
        .option(HBaseTableCatalog.newTable, "5")
        .format("org.apache.spark.sql.execution.datasources.hbase")
        .save()
    }
  }
