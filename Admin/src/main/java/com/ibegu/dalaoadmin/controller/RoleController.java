package com.ibegu.dalaoadmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.ibegu.dalaoadmin.req.RoleQueryReq;
import com.ibegu.dalaoadmin.req.RoleSaveReq;
import com.ibegu.dalaoadmin.resp.CommonResp;
import com.ibegu.dalaoadmin.resp.PageResp;
import com.ibegu.dalaoadmin.resp.RoleQueryResp;
import com.ibegu.dalaoadmin.service.RoleService;
import com.ibegu.dalaoadmin.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/6/11 15:34
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

    @Resource
    private SnowFlake snowFlake;

    // @Resource
    // @Autowired
    // private RedisTemplate redisTemplate;



    @GetMapping("/list")
    public CommonResp list(@Valid RoleQueryReq req) {
        CommonResp<PageResp<RoleQueryResp>> resp = new CommonResp<>();
        PageResp<RoleQueryResp> list = roleService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody RoleSaveReq req) {
        // req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        roleService.save(req);
        return resp;
    }
    //
    @DeleteMapping("/delete/{rid}")
    public CommonResp delete(@PathVariable Long rid) {
        CommonResp resp = new CommonResp<>();
        roleService.delete(rid);
        return resp;
    }

}

