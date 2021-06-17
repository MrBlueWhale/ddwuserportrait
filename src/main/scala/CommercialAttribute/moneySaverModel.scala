package CommercialAttribute

import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Column, DataFrame, SparkSession}

object moneySaverModel {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    //礼品卡优惠金额
    //优惠码编码
    //优惠码优惠金额
    //订单总金额
    def catalog =
      s"""{
         |  "table":{"namespace":"default", "name":"tbl_orders"},
         |  "rowkey":"id",
         |  "columns":{
         |    "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |    "memberId":{"cf":"cf", "col":"memberId", "type":"string"},
         |    "giftCardAmount":{"cf":"cf", "col":"giftCardAmount", "type":"string"},
         |    "couponCode":{"cf":"cf", "col":"couponCode", "type":"string"},
         |    "couponCodeValue":{"cf":"cf", "col":"couponCodeValue", "type":"string"},
         |    "orderAmount":{"cf":"cf", "col":"orderAmount", "type":"string"}
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

    source.show(50,false)

    //val filterSource = source.select('memberId,'giftCardAmount,'couponCode).where('filterCouponCode =!="")

    val colSavePercentage = "savePercentage"
    val colCouponCount = "couponCount"
    val colGiftCardCount = "giftCardCount"
    val colFeature = "feature"
    val colPredict = "predict"//预测是哪一个分类

    //统计节省的总金额占消费总金额的占比
    val savePercentageCol = (sum('giftCardAmount) + sum('couponCodeValue))/sum('orderAmount)*10000 as colSavePercentage
    //统计优惠码优惠次数
    val couponCountCol = count('couponCode=!="") as colCouponCount
    //统计礼品卡优惠次数
    val giftCardCountCol = count('giftCardAmount.isNotNull) as colGiftCardCount


    val RFMResult = source.groupBy('memberId)
      .agg(savePercentageCol, couponCountCol, giftCardCountCol)

    RFMResult.show(50, false)


    //2.为RFM打分(数据归一化处理)
    val savePercentageScore: Column = when((col(colSavePercentage) >= 0) && (col(colSavePercentage) < 20), 5)
      .when((col(colSavePercentage) >= 20) && (col(colSavePercentage) < 40), 4)
      .when((col(colSavePercentage) >= 40) && (col(colSavePercentage) < 80), 3)
      .when((col(colSavePercentage) >= 80) && (col(colSavePercentage) < 120), 2)
      .when(col(colSavePercentage) >= 120, 1)
      .as(colSavePercentage)

    val couponCountScore: Column = when((col(colCouponCount) >= 0) && (col(colCouponCount) < 50), 5)
      .when((col(colCouponCount) >= 50) && (col(colCouponCount) < 80), 4)
      .when((col(colCouponCount) >= 80) && (col(colCouponCount) < 100), 3)
      .when((col(colCouponCount) >= 100) && (col(colCouponCount) < 150), 2)
      .when(col(colCouponCount) >= 150, 1)
      .as(colCouponCount)


    val RFMScoreResult = RFMResult.select('memberId, savePercentageScore, couponCountScore)

    RFMScoreResult.show(50, false)

    //3、调用K-Means模型
    val vectorDF = new VectorAssembler()
      .setInputCols(Array(colSavePercentage, colCouponCount))
      .setOutputCol(colFeature)  //在原表基础上，添加向量那一列
      .transform(RFMScoreResult) //进行向量转化处理的原表

    val kmeans = new KMeans()
        .setK(3)
        .setSeed(1000)//设置种子用来找中心点,
        .setMaxIter(5)
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
      when('predict === "0", "3折-4折")
        .when('predict === "1", "5折-7折")
        .when('predict === "2", "8折-9折")
        .otherwise("未知")
        .as("moneySaver")
    )

    newResult.show(100,false)
    def catalogWrite =
      s"""{
         |"table":{"namespace":"default", "name":"user_profile"},
         |"rowkey":"id",
         |"columns":{
         |  "id":{"cf":"rowkey", "col":"id", "type":"string"},
         |  "moneySaver":{"cf":"Commercial", "col":"moneySaver", "type":"string"}
         |}
         |}""".stripMargin

    newResult.write
      .option(HBaseTableCatalog.tableCatalog, catalogWrite)
      .option(HBaseTableCatalog.newTable, "5")
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .save()


    spark.stop()
  }

}
