package com.ibegu.dalaoadmin.config;

import com.alibaba.fastjson.JSONObject;
import com.ibegu.dalaoadmin.service.HBaseService;
import com.ibegu.dalaoadmin.utils.JsonFileUtil;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.IOException;

class DalaoadminApplicationTests {

    @Resource
    private HBaseService hBaseService;

    @Test
    void contextLoads() {
    }

    @Test
    void saveUserProfile(){

        JSONObject userProfileJson = null;

        try {
            userProfileJson = hBaseService.queryTable();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonFileUtil jsonFileUtil = new JsonFileUtil();

        jsonFileUtil.writeJson("D://", userProfileJson, "userProfileJson.json");

    }


}
