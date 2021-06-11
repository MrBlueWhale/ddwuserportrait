package PopulationAttribute

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions.when

object JobModel {
  def main(args: Array[String]): Unit = {
    // 屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)

    val spark = SparkSession.builder()
      .appName("politicalFace")
      .master("local")
      .getOrCreate()
    //import spark.implicits._
    import spark.implicits._
    //1、读取HBase的用户性别信息
    def catalog =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_users"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "job":{"cf":"Population", "col":"job", "type":"string"}
         |  }
         |}""".stripMargin

    //table: namespace 空间: default  name:表名
    //rowkey
    //columns:定义获取哪些列的数据

    val source = spark.read
      .option(HBaseTableCatalog.tableCatalog, catalog)
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

    val result = source.select('id,
      when('job  === "1","学生")
        .when('job  === "2", "公务员")
        .when('job  === "3", "军人")
        .when('job  === "4", "警察")
        .when('job  === "5", "教师")
        .otherwise("白领")
        .as("job")
    )

    result.show(10, false)

    def writeCatalog =
      s"""{
         |  "table":{"namespace":"default", "name":"user_profile"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "job":{"cf":"cf", "col":"job", "type":"string"}
         |  }
         |}""".stripMargin

    result.write
      .option(HBaseTableCatalog.tableCatalog, writeCatalog)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()
  }
}
