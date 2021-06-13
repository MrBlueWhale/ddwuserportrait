package com.ibegu.dalaoadmin.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HBaseService {

    private Connection getConnection() throws IOException{
        //创建配置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","master");
        conf.set("hbase.zookeeper.property.clientPort","2181");

        //创建连接
        return ConnectionFactory.createConnection(conf);
    }


    @Test
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
    @Test
    //判断表是否存在
    public void isTableExists() throws IOException {
        //创建admin
        Admin admin = getConnection().getAdmin();
        //调用API进行判断表是否存在
        System.out.println(admin.tableExists(TableName.valueOf("user_profile")));
        //System.out.println(admin);
    }

    @Test
    //向表中插入数据
    public void putData2Table() throws IOException {

        //创建table类
        Table student = getConnection().getTable(TableName.valueOf("student"));
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
        Table student = getConnection().getTable(TableName.valueOf("user_profile"));
        //创建get类
        Get get = new Get(Bytes.toBytes("1"));
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

    //删除表操作
    @Test
    public void dropTable() throws IOException {
        //创建admin
        Admin admin = getConnection().getAdmin();
        //调用API禁用表
        admin.disableTable(TableName.valueOf("student"));
        //调用API删除表
        admin.deleteTable(TableName.valueOf("student"));
    }
}
