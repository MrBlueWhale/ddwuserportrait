package com.ibegu.dalaoadmin.service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class HBaseService {

    private static final String URL="jdbc:mysql://master:3306/tags_dat?";//数据库连接字符串，这里的deom为数据库名
    private static final String NAME="root";//登录名
    private static final String PASSWORD="mysqlroot";//密码

    private static java.sql.Connection connection;

    private static Connection HbaseConn;

    public HBaseService(){
        connection = TheSqlConnection();
        try {
            HbaseConn = getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public java.sql.Connection TheSqlConnection()
    {
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");
            //添加一个println，如果加载驱动异常，检查是否添加驱动，或者添加驱动字符串是否错误
            e.printStackTrace();
        }
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("获取数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
            //添加一个println，如果连接失败，检查连接字符串或者登录名以及密码是否错误
            e.printStackTrace();
        }
        return conn;
        //数据库打开后就要关闭
//        if(conn!=null)
//        {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                conn=null;
//            }
//        }
    }


    private Connection getConnection() throws IOException{
        //创建配置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","master");
        conf.set("hbase.zookeeper.property.clientPort","2181");

        //创建连接
        return ConnectionFactory.createConnection(conf);
    }


    //创建表
    public void createTable() throws IOException {

        //创建admin
        Admin admin = HbaseConn.getAdmin();
        //创建表的描述信息
        HTableDescriptor student = new HTableDescriptor(TableName.valueOf("student"));
        //添加列簇
        student.addFamily(new HColumnDescriptor("Info"));
        student.addFamily(new HColumnDescriptor("score"));
        //调用API进行建表操作
        admin.createTable(student);
    }


    //判断表是否存在
    public void isTableExists() throws IOException {
        //创建admin
        Admin admin = HbaseConn.getAdmin();
        //调用API进行判断表是否存在
        System.out.println(admin.tableExists(TableName.valueOf("user_profile")));
        //System.out.println(admin);
    }


    //向表中插入数据
    public void putData2Table() throws IOException {

        //创建table类
        Table student = HbaseConn.getTable(TableName.valueOf("student"));
        //创建put类
        Put put = new Put(Bytes.toBytes("1001"));
        //想put中添加列簇，列名，值  注意：需要转化成字节数组
        put.addColumn(Bytes.toBytes("Info"),Bytes.toBytes("name"),Bytes.toBytes("zhangsan"));
        //调用API操作进行插入操作
        student.put(put);
    }

    //查看一条数据
    @Test
    public void getDataFromTable() throws IOException {

        //创建table类
        Table student = HbaseConn.getTable(TableName.valueOf("user_profile"));
        Scan scan = new Scan();

        //创建get类
        Get get = new Get(Bytes.toBytes("912"));
        //调用API进行获取数据
        Result result = student.get(get);
        //将返回的结果进行遍历输出
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println("rowkey:"+Bytes.toString(CellUtil.cloneRow(cell)));
            System.out.println("列簇:"+Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println("列名:"+Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println("值:"+Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    public JSONObject genderRatio() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());
        int boy = 0;
        int girl = 0 ;
        for (Result result : results) {

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("gender")){
                    if (Bytes.toString(CellUtil.cloneValue(cell)).equals("男")){
                        boy++;
                    }else{
                        girl++;
                    }
                }
            }
        }
        JSONObject out = new JSONObject();
        JSONArray  temp = new JSONArray();

        JSONObject json = new JSONObject();
        json.put("name","男");
        json.put("value",boy);

        temp.add(json);
        json.clear();

        json.put("name","女");
        json.put("女",girl);
        temp.add(json);

        out.put("genderRatio",temp);
        return  out;
    }

    public JSONObject ageGroup() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());

        int zero = 0, one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0 , nine = 0;

        for (Result result : results) {
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if (Bytes.toString(CellUtil.cloneQualifier(cell)).equals("ageGroup")){
                    if (Bytes.toString(CellUtil.cloneValue(cell)).equals("00")){
                        zero++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("10")){
                        one++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("20")){
                        two++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("30")){
                        three++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("40")){
                        four++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("50")){
                        five++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("60")){
                        six++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("70")){
                        seven++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("80")){
                        eight++;
                    }else {
                        nine++;
                    }
                }
            }

        }
        JSONObject out = new JSONObject();
        JSONArray  temp = new JSONArray();

        int[] counts = {zero,one,two,three,four,five,six,seven,eight,nine};
        String[] names = {"00","10","20","30","40","50","60","70","80","90"};

        for (int i = 0;i < 10;i++){
            JSONObject json = new JSONObject();
            json.put("name",names[i]);
            json.put("value",counts[i]);
            temp.add(json);
        }
        out.put("ageGroupRatio",temp);
        return  out;
    }


    public JSONObject genderAndAgeGroupRatio() throws IOException{
        JSONObject json = new JSONObject();
        json.putAll(genderRatio());
        json.putAll(ageGroup());
        System.out.println(json);
        return  json;
    }

    /*
    参数：family   user_profile 列族:
    返回：{family：[{id: ,col1: , coln:},{},{}]}

     */
    public JSONObject family(String family) throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        ResultScanner population = table.getScanner(Bytes.toBytes(family));
        JSONObject outPut = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Result result : population) {
            List<Cell> listCells = result.listCells();
            System.out.println(Bytes.toString(result.getRow()));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",Bytes.toString(result.getRow()));
            for (Cell cell : listCells) {
                jsonObject.put(Bytes.toString(CellUtil.cloneQualifier(cell)),Bytes.toString(CellUtil.cloneValue(cell)));
            }

            jsonArray.add(jsonObject);
        }
        outPut.put(family,jsonArray);
        return outPut;
    }



    public JSONObject searchByTel(String tel) throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("Population"),Bytes.toBytes("mobile")
        , CompareFilter.CompareOp.EQUAL,Bytes.toBytes(tel));
        filter.setFilterIfMissing(true);
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);

        JSONObject outP = new JSONObject();
        for (Result result : scanner){
            //System.out.println(Bytes.toString(result.getRow()));
            outP.put("id",Bytes.toString(result.getRow()));
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                //System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell))+Bytes.toString(CellUtil.cloneValue(cell)));
                if (Bytes.toString(CellUtil.cloneQualifier(cell)).equals("favorProducts")){
                    List<String> lists = Arrays.asList(",".split(Bytes.toString(CellUtil.cloneValue(cell))));
                    outP.put(Bytes.toString(CellUtil.cloneQualifier(cell)),id2ProductName(lists));
                }else {
                    outP.put(Bytes.toString(CellUtil.cloneQualifier(cell)),Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
        }

        return outP;
    }

    @Test
    public void test(){
        try {
            System.out.println(searchByTel("13908735198"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject spendPowerRatio() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());
        int one = 0, two = 0, three = 0, four = 0, five = 0,six = 0,seven = 0;
        for (Result result : results) {

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("spendPower")){
                    if(Bytes.toString(CellUtil.cloneValue(cell)).equals("很低")){
                        one++;
                    }else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("低")){
                        two++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("中下")){
                        three++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("中")){
                        four++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("中上")){
                        five++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("高")){
                        six++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("超高")){
                        seven++;
                    }
                }
            }
        }
        JSONObject out = new JSONObject();
        JSONArray  temp = new JSONArray();

        int[] counts = {one,two,three,four,five,six,seven};
        String[] names = {"很低","低","中下","中","中上","高","超高"};

        for (int i = 0;i < 7;i++){
            JSONObject json = new JSONObject();
            json.put("name",names[i]);
            json.put("value",counts[i]);
            temp.add(json);
        }
        out.put("spendPowerRatio",temp);
        //System.out.println(json);
        return  out;
    }

    @Test
    public void te(){
        try {
            jobRatio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JSONObject jobRatio() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());
        int one = 0, two = 0, three = 0, four = 0, five = 0,six = 0,seven = 0;
        for (Result result : results) {

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("job")){
                    if(Bytes.toString(CellUtil.cloneValue(cell)).equals("学生")){
                        one++;
                    }else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("公务员")){
                        two++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("军人")){
                        three++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("警察")){
                        four++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("教师")){
                        five++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("白领")){
                        six++;
                    }
                }
            }
        }
        JSONObject out = new JSONObject();
        JSONArray  temp = new JSONArray();


        int[] counts = {one,two,three,four,five,six};
        String[] names = {"学生","公务员","军人","警察","教师","白领"};

        for (int i = 0;i < 6;i++){
            JSONObject json = new JSONObject();
            json.put("name",names[i]);
            json.put("value",counts[i]);
            temp.add(json);
        }
        out.put("jobRatio",temp);
        System.out.println(out);
        return  out;
    }

    public JSONObject DeviceRatio() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());
        int one = 0, two = 0, three = 0, four = 0, five = 0;
        for (Result result : results) {

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("DeviceType")){
                    if(Bytes.toString(CellUtil.cloneValue(cell)).equals("Android")){
                        one++;
                    }else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("Windows")){
                        two++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("iOS")){
                        three++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("Mac")){
                        four++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("Linux")){
                        five++;
                    }
                }
            }
        }

        JSONObject out = new JSONObject();
        JSONArray  temp = new JSONArray();
        int[] counts = {one,two,three,four,five};
        String[] names = {"Android","Windows","iOS","Mac","Linux"};

        for (int i = 0;i < 5;i++){
            JSONObject json = new JSONObject();
            json.put("name",names[i]);
            json.put("value",counts[i]);
            temp.add(json);
        }
        out.put("DeviceRatio",temp);
        //System.out.println(json);
        return  out;
    }

    public JSONObject queryTable() throws IOException {
//        System.out.println("--------------------查询整表的数据--------");

        //获取数据表对象
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));


        //获取表中的数据
        ResultScanner scanner = table.getScanner(new Scan());
        JSONObject outPut = new JSONObject();
        JSONArray jsonArray = new JSONArray();


        //循环输出表中的数据
        for (Result result : scanner) {

            byte[] row = result.getRow();
//            System.out.println("row key is:"+new String(row));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",new String(row));
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                jsonObject.put(Bytes.toString(CellUtil.cloneQualifier(cell)),Bytes.toString(CellUtil.cloneValue(cell)));
            }
            jsonArray.add(jsonObject);

        }
        outPut.put("user_profile",jsonArray);
        //System.out.println(outPut);
//        System.out.println("---------------查询整表数据结束----------");
        return outPut;
    }

    //删除表操作
    public void dropTable() throws IOException {
        //创建admin
        Admin admin = HbaseConn.getAdmin();
        //调用API禁用表
        admin.disableTable(TableName.valueOf("student"));
        //调用API删除表
        admin.deleteTable(TableName.valueOf("student"));
    }



    public JSONObject paymentCodeRate() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("tbl_orders"));

        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("paymentCode"));
        ResultScanner results = table.getScanner(scan);

        int alipay = 0, wx = 0, card = 0, cdd = 0, other = 0;

        for (Result result : results) {
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if (Bytes.toString(CellUtil.cloneQualifier(cell)).equals("paymentCode")){
                    if (Bytes.toString(CellUtil.cloneValue(cell)).equals("cod")){
                        cdd++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("alipay")){
                        alipay++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("wxpay")||
                            Bytes.toString(CellUtil.cloneValue(cell)).equals("wspay")){
                        wx++;
                    }else if(Bytes.toString(CellUtil.cloneValue(cell)).equals("chinapay")||
                            Bytes.toString(CellUtil.cloneValue(cell)).equals("chinaecpay")||
                            Bytes.toString(CellUtil.cloneValue(cell)).equals("ccb")){
                        card++;
                    }else {
                        other++;
                    }
                }
            }
        }

        JSONObject out = new JSONObject();
        JSONArray  temp = new JSONArray();


        int[] counts = {alipay,cdd,wx,card,other};
        String[] names = {"支付宝","货到付款","微信支付","银行卡","其它"};

        for (int i = 0;i < 5;i++){
            JSONObject json = new JSONObject();
            json.put("name",names[i]);
            json.put("value",counts[i]);
            temp.add(json);
        }
        out.put("jobRatio",temp);
        //System.out.println(out);
        return  out;
    }


    public String searchByTelAndCol(String tel,String col) throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("Population"),Bytes.toBytes("mobile")
                , CompareFilter.CompareOp.EQUAL,Bytes.toBytes(tel));
        filter.setFilterIfMissing(true);
        String out = "";
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner results = table.getScanner(scan);
        for (Result result : results){

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells){
                if (Bytes.toString(CellUtil.cloneQualifier(cell)).equals(col)){
                    out = Bytes.toString(CellUtil.cloneValue(cell));
                    //System.out.println(out);
                }
            }
        }
        return out;
    }


    public int cell2Hours(Cell cell){
        return Integer.parseInt(Bytes.toString(CellUtil.cloneValue(cell)).split(" ")[1].split(":")[0]);
    }

    public JSONObject LogTimeRatio() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("tbl_logs"));
        ResultScanner results = table.getScanner(new Scan());
        int one = 0, two = 0, three = 0, four = 0, five = 0;
        for (Result result : results) {

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("log_time")){
                    if(cell2Hours(cell) >= 0 && cell2Hours(cell)<1){
                        one++;
                    }else if (cell2Hours(cell) >= 1 && cell2Hours(cell)<8){
                        two++;
                    }else if(cell2Hours(cell) < 13){
                        three++;
                    }else if(cell2Hours(cell) < 18){
                        four++;
                    }else if(cell2Hours(cell) < 22){
                        five++;
                    }else if(cell2Hours(cell) < 24){
                        one++;
                    }
                }
            }
        }
        JSONObject json = new JSONObject();
        json.put("22时--24时",one);
        json.put("1时--7时",two);
        json.put("8时--12时",three);
        json.put("13时--17时",four);
        json.put("18时--21时",five);
        //System.out.println(json);
        return  json;
    }


    public JSONObject politicsFaceRatio() throws IOException{
        Table table = HbaseConn.getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());
        int one = 0, two = 0, three = 0;
        for (Result result : results) {
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("politicalFace")){
                    if(Bytes.toString(CellUtil.cloneValue(cell)).equals("群众")){
                        one++;
                    }else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("党员")){
                        two++;
                    }else{
                        three++;
                    }
                }
            }
        }
        JSONObject out = new JSONObject();
        JSONArray  temp = new JSONArray();


        int[] counts = {one,two,three};
        String[] names = {"群众","党员","无党派人士"};

        for (int i = 0;i < 3;i++){
            JSONObject json = new JSONObject();
            json.put("name",names[i]);
            json.put("value",counts[i]);
            temp.add(json);
        }
        out.put("politicsFaceRatio",temp);
        System.out.println(out);
        return  out;
    }


    public String id2ProductName(List<String> strings){
        PreparedStatement statement;
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("进入顶顶顶顶顶顶顶顶顶顶顶顶");
        try {
            statement = connection.prepareStatement("select productName from tbl_goods where productId = ?");
            for (String s:strings){
                statement.setString(1,s);
                statement.addBatch();
            }
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                stringBuilder.append(resultSet.getString("productName")+',');
                System.out.println(resultSet.getString("productName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


}
