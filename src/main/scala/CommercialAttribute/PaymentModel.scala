package CommercialAttribute

import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, SparkSession}

object PaymentModel {
  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_orders"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "memberId":{"cf":"cf", "col":"memberId", "type":"string"},
         |    "paymentCode":{"cf":"cf", "col":"paymentCode", "type":"string"}
         |  }
         |}""".stripMargin

    val spark = SparkSession.builder()
      .appName("shc test")
      .master("local[10]")
      .getOrCreate()

    import spark.implicits._

    val source: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    source.show(20, false)

    val result = source.groupBy('memberId, 'paymentCode)
      .agg(count('paymentCode) as "count")
      .withColumn("row_num", row_number() over Window.partitionBy('memberId).orderBy('count.desc))
      .where('row_num === 1)
      .withColumnRenamed("memberId", "id").drop("count", "row_num")

    result.show(20, false)

    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |  "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |  "paymentCode":{"cf":"Commercial", "col":"paymentCode", "type":"string"}
         |}
         |}""".stripMargin

    val newResult = result.select('id, 'paymentCode).where('id + 0 <= 950)
    newResult.show()

    newResult.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}