import org.apache.spark.ml.recommendation.ALSModel
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions.{count, udf}
import org.apache.spark.sql.types.{DataTypes, LongType}
import productRecommendationModel.{getProductId, predict2String, sub}

object yy {
  def main(args: Array[String]): Unit = {
    //Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
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
         |"id":{"cf":"rowkey", "col":"id", "type":"Long"},
         |"favorProducts":{"cf":"Recommendation", "col":"favorProducts", "type":"string"},
         |"favorBrands":{"cf":"Recommendation", "col":"favorBrands", "type":"string"}
         |}
         |}""".stripMargin


    val logsDF: DataFrame = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    logsDF.show(false)




    spark.stop()
  }
}
