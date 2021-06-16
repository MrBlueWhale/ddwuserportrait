package com.ibegu.dalaoadmin.service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HBaseService {

    private Connection getConnection() throws IOException {
        //创建配置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "master");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        //创建连接
        return ConnectionFactory.createConnection(conf);
    }


    //创建表
    public void createTable() throws IOException {

        //创建admin
        Admin admin = getConnection().getAdmin();
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
        Admin admin = getConnection().getAdmin();
        //调用API进行判断表是否存在
        System.out.println(admin.tableExists(TableName.valueOf("user_profile")));
        //System.out.println(admin);
    }


    //向表中插入数据
    public void putData2Table() throws IOException {

        //创建table类
        Table student = getConnection().getTable(TableName.valueOf("student"));
        //创建put类
        Put put = new Put(Bytes.toBytes("1001"));
        //想put中添加列簇，列名，值  注意：需要转化成字节数组
        put.addColumn(Bytes.toBytes("Info"), Bytes.toBytes("name"), Bytes.toBytes("zhangsan"));
        //调用API操作进行插入操作
        student.put(put);
    }

    //查看一条数据
    public void getDataFromTable() throws IOException {

        //创建table类
        Table student = getConnection().getTable(TableName.valueOf("user_profile"));
        Scan scan = new Scan();

        //创建get类
        Get get = new Get(Bytes.toBytes("1"));
        //调用API进行获取数据
        Result result = student.get(get);
        //将返回的结果进行遍历输出
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println("rowkey:" + Bytes.toString(CellUtil.cloneRow(cell)));
            System.out.println("列簇:" + Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println("列名:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println("值:" + Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    public JSONObject genderRatio() throws IOException {
        Table table = getConnection().getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());
        int boy = 0;
        int girl = 0;
        for (Result result : results) {

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if (Bytes.toString(CellUtil.cloneQualifier(cell)).equals("gender")) {
                    if (Bytes.toString(CellUtil.cloneValue(cell)).equals("男")) {
                        boy++;
                    } else {
                        girl++;
                    }
                }
            }
        }
        JSONObject json = new JSONObject();
        json.put("男", boy);
        json.put("女", girl);
        System.out.println(json);
        return json;
    }

    public JSONObject ageGroup() throws IOException {
        Table table = getConnection().getTable(TableName.valueOf("user_profile"));
        ResultScanner results = table.getScanner(new Scan());

        int zero = 0, one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine = 0;

        for (Result result : results) {
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                if (Bytes.toString(CellUtil.cloneQualifier(cell)).equals("ageGroup")) {
                    if (Bytes.toString(CellUtil.cloneValue(cell)).equals("00")) {
                        zero++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("10")) {
                        one++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("20")) {
                        two++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("30")) {
                        three++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("40")) {
                        four++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("50")) {
                        five++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("60")) {
                        six++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("70")) {
                        seven++;
                    } else if (Bytes.toString(CellUtil.cloneValue(cell)).equals("80")) {
                        eight++;
                    } else {
                        nine++;
                    }
                }
            }

        }
        JSONObject json = new JSONObject();
        json.put("00", zero);
        json.put("10", one);
        json.put("20", two);
        json.put("30", three);
        json.put("40", four);
        json.put("60", six);
        json.put("50", five);
        json.put("70", seven);
        json.put("80", eight);
        json.put("90", nine);
        System.out.println(json);
        return json;
    }


    public JSONObject genderAndAgeGroupRatio() throws IOException {
        JSONObject json = new JSONObject();
        json.putAll(genderRatio());
        json.putAll(ageGroup());
        System.out.println(json);
        return json;
    }

    /*
    参数：family   user_profile 列族:
    返回：{family：[{id: ,col1: , coln:},{},{}]}

     */
    public JSONObject family(String family) throws IOException {
        Table table = getConnection().getTable(TableName.valueOf("user_profile"));
        ResultScanner population = table.getScanner(Bytes.toBytes(family));
        JSONObject outPut = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Result result : population) {
            List<Cell> listCells = result.listCells();
            System.out.println(Bytes.toString(result.getRow()));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", Bytes.toString(result.getRow()));
            for (Cell cell : listCells) {
                jsonObject.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }

            jsonArray.add(jsonObject);
        }
        outPut.put(family, jsonArray);
        return outPut;
    }


    public JSONObject searchByTel(String tel) throws IOException {
        Table table = getConnection().getTable(TableName.valueOf("user_profile"));
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("Population"), Bytes.toBytes("mobile")
                , CompareFilter.CompareOp.EQUAL, Bytes.toBytes(tel));
        filter.setFilterIfMissing(true);
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);

        JSONObject outP = new JSONObject();
        for (Result result : scanner) {
            //System.out.println(Bytes.toString(result.getRow()));
            outP.put("id", Bytes.toString(result.getRow()));
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                //System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell))+Bytes.toString(CellUtil.cloneValue(cell)));
                outP.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        return outP;
    }



    public JSONObject queryTable() throws IOException {
//        System.out.println("--------------------查询整表的数据--------");

        //获取数据表对象
        Table table = getConnection().getTable(TableName.valueOf("user_profile"));


        //获取表中的数据
        ResultScanner scanner = table.getScanner(new Scan());
        JSONObject outPut = new JSONObject();
        JSONArray jsonArray = new JSONArray();


        //循环输出表中的数据
        for (Result result : scanner) {

            byte[] row = result.getRow();
//            System.out.println("row key is:"+new String(row));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", new String(row));
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                jsonObject.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }
            jsonArray.add(jsonObject);

        }
        outPut.put("user_profile", jsonArray);
        //System.out.println(outPut);
//        System.out.println("---------------查询整表数据结束----------");
        return outPut;
    }

    //删除表操作
    public void dropTable() throws IOException {
        //创建admin
        Admin admin = getConnection().getAdmin();
        //调用API禁用表
        admin.disableTable(TableName.valueOf("student"));
        //调用API删除表
        admin.deleteTable(TableName.valueOf("student"));
    }


         /* 获取数据（全表数据）
            * @param tableName 表名
     * @return map
     */

    public List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            //获取数据表对象
            Table table = getConnection().getTable(TableName.valueOf("user_profile"));
            Scan scan = new Scan();
            ResultScanner resultScanner = table.getScanner(scan);
            for(Result result : resultScanner) {
                HashMap<String, String> map = new HashMap<>();
                //rowkey
                String row = Bytes.toString(result.getRow());
                map.put("row", row);
                for (Cell cell : result.listCells()) {
                    //列族
                    String family = Bytes.toString(cell.getFamilyArray(),
                            cell.getFamilyOffset(), cell.getFamilyLength());
                    //列
                    String qualifier = Bytes.toString(cell.getQualifierArray(),
                            cell.getQualifierOffset(), cell.getQualifierLength());
                    //值
                    String data = Bytes.toString(cell.getValueArray(),
                            cell.getValueOffset(), cell.getValueLength());
                    map.put(family + ":" + qualifier, data);
                }
                list.add(map);
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }





    public JSONObject paymentCodeRate() throws IOException{
        Table table = getConnection().getTable(TableName.valueOf("tbl_orders"));

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


        JSONObject json = new JSONObject();
        json.put("货到付款",cdd);
        json.put("微信支付",wx);
        json.put("支付宝",alipay);
        json.put("银行卡",card);
        json.put("其他",other);
        System.out.println(json);
        return  json;
    }


    public String searchByTelAndCol(String tel,String col) throws IOException{
        Table table = getConnection().getTable(TableName.valueOf("user_profile"));
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
}
