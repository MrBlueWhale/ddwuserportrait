package CommercialAttribute

import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object CustomerPriceModel {
  def main(args: Array[String]): Unit = {
    def catalog =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_orders"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "memberId":{"cf":"cf", "col":"memberId", "type":"string"},
         |    "paidAmount":{"cf":"cf", "col":"paidAmount", "type":"string"}
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

    val result = source.groupBy('memberId as "id")
      .agg(max('paidAmount + 0.0) as "highestPay")

    result.show(20, false)

    val newResult = result.select('id, 'highestPay).where('id + 0 <= 950)
    newResult.show()

    val price = newResult.select('id,
      when('highestPay + 0.0 < 1000, "1-999")
        .when('highestPay + 0.0 >= 1000 && 'highestPay + 0.0 < 2000, "1000-2999")
        .when('highestPay + 0.0 >= 3000 && 'highestPay + 0.0 < 5000, "3000-4999")
        .otherwise("5000-9999")
        .as("customerPrice")
    )
    price.show()

    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |  "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |  "customerPrice":{"cf":"Commercial", "col":"customerPrice", "type":"string"}
         |}
         |}""".stripMargin

    price.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}