package com.ibegu.dalaoadmin.controller;


// import com.ibegu.dalaoadmin.domain.GroupTag;

import com.ibegu.dalaoadmin.resp.CommonResp;
import com.ibegu.dalaoadmin.resp.GroupTagQueryResp;
import com.ibegu.dalaoadmin.service.GroupTagService;
import com.ibegu.dalaoadmin.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/6/11 15:34
 **/
@RestController
@RequestMapping("/groupTag")
public class GroupTagController {

    private static final Logger LOG = LoggerFactory.getLogger(GroupTagController.class);

    @Resource
    private GroupTagService groupTagService;

    @Resource
    private SnowFlake snowFlake;

    // @Resource
    // @Autowired
    // private RedisTemplate redisTemplate;



//     @GetMapping("/list")
//     public CommonResp list(@Valid GroupTagQueryReq req) {
//         CommonResp<PageResp<GroupTagQueryResp>> resp = new CommonResp<>();
//         PageResp<GroupTagQueryResp> list = groupTagService.list(req);
//         resp.setContent(list);
//         return resp;
//     }

    // @PostMapping("/save")
    // public CommonResp save(@Valid @RequestBody GroupTagSaveReq req) {
    //     // req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
    //     CommonResp resp = new CommonResp<>();
    //     groupTagService.save(req);
    //     return resp;
    // }
    //

    // @DeleteMapping("/delete/{rid}")
    // public CommonResp delete(@PathVariable Long rid) {
    //     CommonResp resp = new CommonResp<>();
    //     groupTagService.delete(rid);
    //     return resp;
    // }

    // @PostMapping("/distribute")
    // public CommonResp distribute(@RequestBody GroupTagDistributeReq req) {
    //     CommonResp resp = new CommonResp<>();
    //     GroupTag groupTag = groupTagService.selectByGroupTagName(req.getGroupTagName());
    //     UserGroupTag userGroupTag = new UserGroupTag();
    //     userGroupTag.setRid(groupTag.getRid());
    //     userGroupTag.setUid(req.getUid());
    //
    //     groupTagService.distributeGroupTag(userGroupTag);
    //     resp.setMessage("角色分配成功！");
    //     return resp;
    // }


    @GetMapping("/listGroupTags")
    public CommonResp<List<GroupTagQueryResp>> listGroupTags(){
        CommonResp<List<GroupTagQueryResp>> resp = new CommonResp<>();
        LOG.info("********listGroupTags**********{}",groupTagService.listGroupTags());
//        System.out.println(groupTagService.listGroupTags());
        resp.setContent(groupTagService.listGroupTags());

        return resp;
    }




//    @GetMapping("/editGroupTag/{btId}")
//    public CommonResp editGroupTag(@PathVariable Long btId) {
//        CommonResp resp = new CommonResp<>();
//
//        groupTagService.editGroupTag(btId);
//
//        // resp.setContent('');
//
//        return resp;
//    }

//    @GetMapping("/banGroupTag/{btId}")
//    public CommonResp banGroupTag(@PathVariable Long btId) {
//        CommonResp resp = new CommonResp<>();
//
//        groupTagService.banGroupTag(btId);
//
//        return resp;
//    }

//    @GetMapping("/onlineGroupTag/{btId}")
//    public CommonResp onlineGroupTag(@PathVariable Long btId) {
//        CommonResp resp = new CommonResp<>();
//
//        String content = groupTagService.onlineGroupTag(btId);
//
//        resp.setContent(content);
//
//        return resp;
//    }
//
//    @GetMapping("/offlineGroupTag/{btId}")
//    public CommonResp offlineGroupTag(@PathVariable Long btId) {
//        CommonResp resp = new CommonResp<>();
//
//        String content = groupTagService.offlineGroupTag(btId);
//        resp.setContent(content);
//
//        // resp.setContent('');
//
//        return resp;
//    }


}

