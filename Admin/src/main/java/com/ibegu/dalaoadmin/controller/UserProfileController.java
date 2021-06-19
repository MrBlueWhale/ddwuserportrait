package com.ibegu.dalaoadmin.controller;


import com.alibaba.fastjson.JSONObject;
import com.ibegu.dalaoadmin.resp.CommonResp;
import com.ibegu.dalaoadmin.resp.Rank3TagsQueryResp;
import com.ibegu.dalaoadmin.resp.TagResp;
import com.ibegu.dalaoadmin.resp.UserProfileResp;
import com.ibegu.dalaoadmin.service.BaseTagService;
import com.ibegu.dalaoadmin.service.HBaseService;
import com.ibegu.dalaoadmin.utils.JsonFileUtil;
import com.ibegu.dalaoadmin.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/saveTagRatio")
    public void saveTagRatio() throws IOException {

//        Map<String,JSONObject> tagRatioJson = new HashMap<>();

//        JSONArray jsonArray = new JSONArray();
//////        jsonArray.a;

        JSONObject tagRatioJson = new JSONObject();

        try {
//            JSONObject genderRatioJson = hBaseService.genderRatio();
//            JSONObject pliticsFaceRatioJson = hBaseService.politicsFaceRatio();
//            JSONObject spendPowerRatioJson = hBaseService.spendPowerRatio();
//            JSONObject LogTimeRatioJson = hBaseService.LogTimeRatio();
//            JSONObject deviceRatioJson = hBaseService.DeviceRatio();
//            JSONObject jobRatioJson = hBaseService.jobRatio();


           //tagRatioJson. = hBaseService.jobRatio();

            tagRatioJson.put("jobRatio",hBaseService.jobRatio().get("jobRatio"));
            tagRatioJson.put("genderRatio",hBaseService.genderRatio().get("genderRatio"));
            tagRatioJson.put("politicsFaceRatio",hBaseService.politicsFaceRatio().get("politicsFaceRatio"));
            tagRatioJson.put("spendPowerRatio",hBaseService.spendPowerRatio().get("spendPowerRatio"));
            tagRatioJson.put("deviceRatio",hBaseService.DeviceRatio().get("DeviceRatio"));
            tagRatioJson.put("LogTimeRatio",hBaseService.LogTimeRatio().get("LogTimeRatio"));




//            tagRatioJson.put("sa",genderRatioJson);
//            tagRatioJson.put("saas",pliticsFaceRatioJson);

//            jobRatioJson.put
//            tagRatioJson.put("genderRatio", genderRatioJson);
//            tagRatioJson.put("pliticsFace", pliticsFaceRatioJson);
//
//            tagRatioJson.put("spendPowerRatio", spendPowerRatioJson);
//            tagRatioJson.put("LogTimeRatio", LogTimeRatioJson);
//            tagRatioJson.put("deviceRatio", deviceRatioJson);
//            tagRatioJson.put("jobRatio", jobRatioJson);



        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonFileUtil jsonFileUtil = new JsonFileUtil();

//        jsonFileUtil.writeJson("D://portrait//data//", tagRatioJson, "tagRatioJson.json");
        jsonFileUtil.writeJson("D://", tagRatioJson, "tagRatioJson.json");
    }


    @GetMapping("/showPanorama")
    public CommonResp showPanorama() throws IOException {
        CommonResp resp = new CommonResp<>();

        // Map<String,JSONObject> panorama = new HashMap<>();


        // resp.setContent(panorama);
        JsonFileUtil jsonFileUtil = new JsonFileUtil();

        resp.setContent(JSONObject.parseObject(jsonFileUtil.readJson("Admin//web//src//views//portrait//data//tagRatioJson.json")));

        return resp;
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

    @GetMapping("/listUserProfileMap")
    public CommonResp listUserProfileMap() {
        CommonResp resp = new CommonResp<>();

        List list = null;

        //list = hBaseService.getData();

        resp.setContent(list);
//        resp.setContent(jsonFileUtil.readJson("D://portrait//data//userProfileJson.json"));

        return resp;
    }

    @GetMapping("/listUserProfileByTel/{tel}")
    public CommonResp listUserProfileByTel(@PathVariable String tel) {
        CommonResp resp = new CommonResp<>();

        UserProfileResp userProfileResp = new UserProfileResp();
        try {

            String behaviorAttrs = hBaseService.searchByTelAndCol(tel,"Behavior");
//            String behaviorAttrs = ObjectUtils.isEmpty(hBaseService.searchByTelAndCol(tel,"Behavior")) ? "暂无数据" : hBaseService.searchByTelAndCol(tel,"Behavior");
            String commercialAttrs = hBaseService.searchByTelAndCol(tel,"Commercial");
//            String commercialAttrs = ObjectUtils.isEmpty(hBaseService.searchByTelAndCol(tel,"Commercial")) ? "暂无数据" : hBaseService.searchByTelAndCol(tel,"Commercial");
            String populationAttrs = hBaseService.searchByTelAndCol(tel,"Population");
//            String populationAttrs = ObjectUtils.isEmpty(hBaseService.searchByTelAndCol(tel,"Population")) ? "暂无数据" : hBaseService.searchByTelAndCol(tel,"Population");
            String userValueAttrs = hBaseService.searchByTelAndCol(tel,"userValue");
//            String userValueAttrs = ObjectUtils.isEmpty(hBaseService.searchByTelAndCol(tel,"userValue")) ? "暂无数据" : hBaseService.searchByTelAndCol(tel,"userValue");

            LOG.info("行为属性","{}",behaviorAttrs);
            LOG.info("商业属性","{}",commercialAttrs);
            LOG.info("人口属性","{}",populationAttrs);
            LOG.info("价值属性","{}",userValueAttrs);

            userProfileResp.setBehaviorAttrs(JSONObject.parseObject(behaviorAttrs));
            userProfileResp.setCommercialAttrs(JSONObject.parseObject(commercialAttrs));
            userProfileResp.setPopulationAttrs(JSONObject.parseObject(populationAttrs));
            userProfileResp.setUserValueAttrs(JSONObject.parseObject(userValueAttrs));
        } catch (IOException e) {
            e.printStackTrace();
        }

        resp.setContent(userProfileResp);
//        resp.setContent(jsonFileUtil.readJson("D://portrait//data//userProfileJson.json"));

        return resp;


    }

    @GetMapping("/searchByTel/{tel}")
    public CommonResp searchByTel(@PathVariable String tel) {
        CommonResp resp = new CommonResp<>();

        JSONObject userProfileJson = null;
        JsonFileUtil jsonFileUtil = new JsonFileUtil();

        try {
            userProfileJson =  hBaseService.searchByTel(tel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(userProfileJson.isEmpty()){
            resp.setSuccess(false);
            resp.setMessage("找不到该用户的画像，请核准查询条件！");
//            JSONObject content =
        }

        resp.setContent(userProfileJson);
//        resp.setContent(jsonFileUtil.readJson("D://portrait//data//userProfileJson.json"));

        return resp;
    }

    @GetMapping("/searchByName/{Name}")
    public CommonResp searchByName(@PathVariable String Name) {
        CommonResp resp = new CommonResp<>();

        JSONObject userProfileJson = null;
        JsonFileUtil jsonFileUtil = new JsonFileUtil();

        try {
            userProfileJson =  hBaseService.searchByName(Name);
        } catch (IOException e) {
            e.printStackTrace();
        }

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



}

