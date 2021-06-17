package CommercialAttribute

import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.{Column, DataFrame, SparkSession, functions}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
object spendingPowerModel {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)

    def catalog =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_orders"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "memberId":{"cf":"cf", "col":"memberId", "type":"string"},
         |    "orderSn":{"cf":"cf", "col":"orderSn", "type":"string"},
         |    "orderAmount":{"cf":"cf", "col":"orderAmount", "type":"string"},
         |    "finishTime":{"cf":"cf", "col":"finishTime", "type":"string"}
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

    val colRencency = "rencency"
    val colFrequency = "frequency"
    val colMoneyTotal = "moneyTotal"
    val colFeature = "feature"
    val colPredict = "predict"//预测是哪一个分类
    val days_range = 665

    // 统计距离最近一次消费的时间
    val recencyCol = datediff(date_sub(current_timestamp(), days_range), from_unixtime(max('finishTime))) as colRencency
    // 统计订单总数
    val frequencyCol = count('orderSn) as colFrequency
    // 统计订单总金额
    val moneyTotalCol = sum('orderAmount) as colMoneyTotal

    val RFMResult = source.groupBy('memberId)
      .agg(recencyCol, frequencyCol, moneyTotalCol)

    RFMResult.show(50, false)


    //2.为RFM打分(数据归一化处理)
    //R: 1-3天=5分，4-6天=4分，7-9天=3分，10-15天=2分，大于16天=1分
    //F: ≥200=5分，150-199=4分，100-149=3分，50-99=2分，1-49=1分
    //M: ≥20w=5分，10-19w=4分，5-9w=3分，1-4w=2分，<1w=1分
    val recencyScore: Column = when((col(colRencency) >= 1) && (col(colRencency) <= 3), 5)
      .when((col(colRencency) >= 4) && (col(colRencency) <= 6), 4)
      .when((col(colRencency) >= 7) && (col(colRencency) <= 9), 3)
      .when((col(colRencency) >= 10) && (col(colRencency) <= 15), 2)
      .when(col(colRencency) >= 16, 1)
      .as(colRencency)

    val frequencyScore: Column = when(col(colFrequency) >= 200, 5)
      .when((col(colFrequency) >= 150) && (col(colFrequency) <= 199), 4)
      .when((col(colFrequency) >= 100) && (col(colFrequency) <= 149), 3)
      .when((col(colFrequency) >= 50) && (col(colFrequency) <= 99), 2)
      .when((col(colFrequency) >= 1) && (col(colFrequency) <= 49), 1)
      .as(colFrequency)

    val moneyTotalScore: Column = when(col(colMoneyTotal) >= 200000, 5)
      .when(col(colMoneyTotal).between(100000, 199999), 4)
      .when(col(colMoneyTotal).between(50000, 99999), 3)
      .when(col(colMoneyTotal).between(10000, 49999), 2)
      .when(col(colMoneyTotal) <= 9999, 1)
      .as(colMoneyTotal)

    val RFMScoreResult = RFMResult.select('memberId, recencyScore, frequencyScore, moneyTotalScore)

    RFMScoreResult.show(50, false)

    //3、调用K-Means模型
    val vectorDF = new VectorAssembler()
      .setInputCols(Array(colRencency, colFrequency, colMoneyTotal))//将三个值转为一个向量
      .setOutputCol(colFeature)  //在原表基础上，添加向量那一列
      .transform(RFMScoreResult) //进行向量转化处理的原表

    val kmeans = new KMeans()
        .setK(7)
        .setSeed(1000)//设置种子用来找中心点,
        .setMaxIter(2)
        .setFeaturesCol(colFeature) //上述处理后得到的向量
        .setPredictionCol(colPredict)

    vectorDF.show(50, false)

    val model = kmeans.fit(vectorDF)

    //保存模型到HDFS中
    //model.save("model/uservalue/kmeans")

    //4、做预测
    val result = model.transform(vectorDF)

    result.show(50, false)


    //5、将预测结果写入hbase中
    val newResult = result.select('memberId as "id",
      when('predict === "0", "超高")
        .when('predict === "1", "高")
        .when('predict === "2", "中上")
        .when('predict === "3", "中")
        .when('predict === "4", "中下")
        .when('predict === "5", "低")
        .when('predict === "6", "很低")
        .otherwise("未知")
        .as("spendPower")
    )

    newResult.show(100,false)
    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |  "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |  "spendPower":{"cf":"Commercial", "col":"spendPower", "type":"string"}
         |}
         |}""".stripMargin

//    newResult.write
//      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
//      .option(HBaseTableCatalog.newTable, "5")
//      .format("org.apache.spark.sql.execution.datasources.hbase")
//      .save()



    spark.stop()
  }

}
