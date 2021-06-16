package com.ibegu.dalaoadmin.controller;


import com.alibaba.fastjson.JSONObject;
import com.ibegu.dalaoadmin.req.Rank4TagsQueryReq;
import com.ibegu.dalaoadmin.req.Rank5TagsQueryReq;
import com.ibegu.dalaoadmin.resp.*;
import com.ibegu.dalaoadmin.service.BaseTagService;
import com.ibegu.dalaoadmin.service.HBaseService;
import com.ibegu.dalaoadmin.utils.JsonFileUtil;
import com.ibegu.dalaoadmin.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;

// import com.ibegu.dalaoadmin.resp.BaseTagQueryResp;


/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/6/11 15:34
 **/
@RestController
@RequestMapping("/user-profile")
public class UserProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileController.class);

    @Resource
    private BaseTagService baseTagService;

    @Resource
    private HBaseService hBaseService;

    @Resource
    private SnowFlake snowFlake;

    // @Resource
    // @Autowired
    // private RedisTemplate redisTemplate;


    // @GetMapping("/list")
    // public CommonResp list(@Valid BaseTagQueryReq req) {
    //     CommonResp<PageResp<BaseTagQueryResp>> resp = new CommonResp<>();
    //     PageResp<BaseTagQueryResp> list = baseTagService.list(req);
    //     resp.setContent(list);
    //     return resp;
    // }


    @GetMapping("/saveUserProfile")
    public void saveUserProfile() {

        JSONObject userProfileJson = null;

        try {
            userProfileJson = hBaseService.queryTable();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonFileUtil jsonFileUtil = new JsonFileUtil();

        jsonFileUtil.writeJson("D://portrait//data//", userProfileJson, "userProfileJson.json");

    }


    @GetMapping("/listUserProfile")
    public CommonResp listUserProfile() {
        CommonResp resp = new CommonResp<>();

        JSONObject userProfileJson = null;
        JsonFileUtil jsonFileUtil = new JsonFileUtil();


        String userProfileString = jsonFileUtil.readJson("D://portrait//data//userProfileJson.json");

        userProfileJson = JSONObject.parseObject(userProfileString);

        resp.setContent(userProfileJson);
//        resp.setContent(jsonFileUtil.readJson("D://portrait//data//userProfileJson.json"));

        return resp;
    }

    @GetMapping("/listRank3Tags")
    public CommonResp<TagResp<Rank3TagsQueryResp>> listRank3Tags() {
        CommonResp<TagResp<Rank3TagsQueryResp>> resp = new CommonResp<>();
        resp.setContent(baseTagService.listRank3Tags());

        return resp;
    }

    // @GetMapping("/listRank4Tags")
    // public CommonResp<List<Rank4Tags>> listRank4Tags(){
    //     CommonResp<List<Rank4Tags>> resp = new CommonResp<>();
    //     resp.setContent(baseTagService.listRank4Tags());
    //
    //     return resp;
    // }

    @GetMapping("/listRank4Tags")
    public CommonResp listRank4Tags(@Valid Rank4TagsQueryReq req) {
        CommonResp<PageResp<Rank4TagsQueryResp>> resp = new CommonResp<>();
        PageResp<Rank4TagsQueryResp> list = baseTagService.listRank4Tags(req);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/listRank5Tags")
    public CommonResp listRank5Tags(@Valid Rank5TagsQueryReq req) {
        CommonResp<PageResp<Rank5TagsQueryResp>> resp = new CommonResp<>();
        PageResp<Rank5TagsQueryResp> list = baseTagService.listRank5Tags(req);
        resp.setContent(list);
        return resp;
    }


}

