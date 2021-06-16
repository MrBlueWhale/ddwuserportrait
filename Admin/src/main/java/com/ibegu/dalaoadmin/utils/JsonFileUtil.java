package com.ibegu.dalaoadmin.utils;
//

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * @Author: jiee
 * @Date: 2020/8/6 9:49
 */
public class JsonFileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonFileUtil.class);


    /**
     * 从文件中读取数据
     * @param path 文件路径
     * @return 文件内容
     */
    //从给定位置读取Json文件
    public String readJson(String path) {
        //从指定位置读取文件
        File file = new File(path);

        BufferedReader br = null;

        LOG.info("开始读取，请稍等...");

        //返回值，使用StringBuffer
        StringBuffer data = new StringBuffer();
        try {
            br = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while ((temp = br.readLine()) != null) {
                data.append(temp);
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOG.info("读取成功！");
        return data.toString();
    }

    /**
     * 向文件中写入数据
     * @param path 文件路径
     * @param json  json数据
     * @param fileName 文件名称
     */
    public void writeJson(String path, Object json, String fileName) {
        BufferedWriter bw = null;
        File file = new File(path + fileName);

        LOG.info("开始写入，请稍等...");

        //如果文件不存在，则新建一个
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入数据
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOG.info("写入成功！"+ "文件地址：" + path + fileName);
    }


    public static void main(String[] args) {

        JsonFileUtil jsonFileUtil = new JsonFileUtil();

        jsonFileUtil.writeJson("D://","song2","test.txt");
        jsonFileUtil.readJson("D://test.txt");
    }
}


