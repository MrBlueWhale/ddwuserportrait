package com.ibegu.dalaoadmin.controller;

import com.ibegu.dalaoadmin.req.DemoReq;
import com.ibegu.dalaoadmin.resp.CommonResp;
import com.ibegu.dalaoadmin.resp.DemoResp;
import com.ibegu.dalaoadmin.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/4/9 11:46
 **/

@RestController    //用来返回字符串（controll+responsebody）
// @Controller   //用来返回页面
@RequestMapping("/demo")
public class DemoController {
    @Resource
    DemoService demoService;



    @GetMapping("/list")
    //Controller层不出现domain实体类

    // public CommonResp list(){
    //
    //     CommonResp<List<Demo>> resp = new CommonResp<>();
    //     List<Demo> list = demoService.list();
    //     resp.setContent(list);
    //     return resp;
    //
    // }

    public CommonResp list(DemoReq demoReq){

        CommonResp<List<DemoResp>> resp = new CommonResp<>();
        List<DemoResp> list = demoService.list(demoReq);
        resp.setContent(list);
        return resp;

    }

}
