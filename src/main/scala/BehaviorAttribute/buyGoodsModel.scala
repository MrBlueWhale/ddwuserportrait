package BehaviorAttribute

import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.{DataFrame, SparkSession}

object buyGoodsModel {
  def main(args: Array[String]): Unit = {

    def catalog1 =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_orders"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "memberId":{"cf":"cf", "col":"memberId", "type":"string"},
         |    "packageId":{"cf":"cf", "col":"packageId", "type":"string"},
         |    "paymentStatus":{"cf":"cf", "col":"paymentStatus", "type":"string"}
         |  }
         |}""".stripMargin

    def catalog2 =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_goods"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "productName":{"cf":"cf", "col":"productName", "type":"string"},
         |    "brandId":{"cf":"cf", "col":"brandId", "type":"string"}
         |  }
         |}""".stripMargin

    val spark = SparkSession.builder()
      .appName("shc test")
      .master("local[10]")
      .getOrCreate()

    import spark.implicits._
    val orders: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog1)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    orders.createTempView("orders")
    orders.show()

    val goods: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog2)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    goods.createTempView("goods")
    goods.show()


    val myGoods = spark.sql(
      """
        |select memberId, paymentStatus, productName from orders join goods on orders.packageId = goods.brandId
      """.stripMargin)

    myGoods.show()

    val good = myGoods.select('memberId as "id", 'productName).where('paymentStatus === 1)
    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |  "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |  "productName":{"cf":"Behavior", "col":"productName", "type":"string"}
         |}
         |}""".stripMargin

    val newResult = good.select('id, 'productName).where('id + 0 <= 950)
    newResult.show()

    newResult.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}