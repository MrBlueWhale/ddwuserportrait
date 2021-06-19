package com.ibegu.dalaoadmin.controller;


// import com.ibegu.dalaoadmin.domain.BaseTag;

import com.ibegu.dalaoadmin.req.Rank4TagsQueryReq;
import com.ibegu.dalaoadmin.req.Rank5TagsQueryReq;
import com.ibegu.dalaoadmin.resp.*;
import com.ibegu.dalaoadmin.service.BaseTagService;
import com.ibegu.dalaoadmin.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

// import com.ibegu.dalaoadmin.domain.UserBaseTag;
// import com.ibegu.dalaoadmin.req.BaseTagDistributeReq;
// import com.ibegu.dalaoadmin.req.BaseTagQueryReq;
// import com.ibegu.dalaoadmin.req.BaseTagSaveReq;
// import com.ibegu.dalaoadmin.resp.BaseTagQueryResp;


/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/6/11 15:34
 **/
@RestController
@RequestMapping("/baseTag")
public class BaseTagController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTagController.class);

    @Resource
    private BaseTagService baseTagService;

    @Resource
    private SnowFlake snowFlake;

    // @Resource
    // @Autowired
    // private RedisTemplate redisTemplate;



//     @GetMapping("/list")
//     public CommonResp list(@Valid BaseTagQueryReq req) {
//         CommonResp<PageResp<BaseTagQueryResp>> resp = new CommonResp<>();
//         PageResp<BaseTagQueryResp> list = baseTagService.list(req);
//         resp.setContent(list);
//         return resp;
//     }

    // @PostMapping("/save")
    // public CommonResp save(@Valid @RequestBody BaseTagSaveReq req) {
    //     // req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
    //     CommonResp resp = new CommonResp<>();
    //     baseTagService.save(req);
    //     return resp;
    // }
    //

    // @DeleteMapping("/delete/{rid}")
    // public CommonResp delete(@PathVariable Long rid) {
    //     CommonResp resp = new CommonResp<>();
    //     baseTagService.delete(rid);
    //     return resp;
    // }

    // @PostMapping("/distribute")
    // public CommonResp distribute(@RequestBody BaseTagDistributeReq req) {
    //     CommonResp resp = new CommonResp<>();
    //     BaseTag baseTag = baseTagService.selectByBaseTagName(req.getBaseTagName());
    //     UserBaseTag userBaseTag = new UserBaseTag();
    //     userBaseTag.setRid(baseTag.getRid());
    //     userBaseTag.setUid(req.getUid());
    //
    //     baseTagService.distributeBaseTag(userBaseTag);
    //     resp.setMessage("角色分配成功！");
    //     return resp;
    // }


    @GetMapping("/listBaseTags")
    public CommonResp<List<BaseTagQueryResp>> listBaseTags(){
        CommonResp<List<BaseTagQueryResp>> resp = new CommonResp<>();
        resp.setContent(baseTagService.listBaseTags());

        return resp;
    }

    @GetMapping("/listBaseTagsTree")
    public CommonResp listBaseTagsTree(){
        CommonResp resp = new CommonResp<>();

        List baseTags = baseTagService.listBaseTagsTree();

        resp.setContent(baseTags);

        return resp;
    }

    @GetMapping("/listBaseTagsTreeNodes")
    public CommonResp listBaseTagsTreeNodes(){
        CommonResp resp = new CommonResp<>();

        List baseTags = baseTagService.listBaseTagsTreeNodes();

        resp.setContent(baseTags);

        return resp;
    }

    @GetMapping("/listBaseTagsAudit")
    public CommonResp<List<BaseTagQueryResp>> listBaseTagsAudit(){
        CommonResp<List<BaseTagQueryResp>> resp = new CommonResp<>();
        resp.setContent(baseTagService.listBaseTagsAudit());

        return resp;
    }


    @GetMapping("/listRank3Tags")
    public CommonResp<TagResp<Rank3TagsQueryResp>> listRank3Tags(){
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



    @GetMapping("/editBaseTag/{btId}")
    public CommonResp editBaseTag(@PathVariable Long btId) {
        CommonResp resp = new CommonResp<>();

        baseTagService.editBaseTag(btId);

        // resp.setContent('');

        return resp;
    }

    @GetMapping("/banBaseTag/{btId}")
    public CommonResp banBaseTag(@PathVariable Long btId) {
        CommonResp resp = new CommonResp<>();

        baseTagService.banBaseTag(btId);

        // resp.setContent('');

        return resp;
    }

    @GetMapping("/onlineBaseTag/{btId}")
    public CommonResp onlineBaseTag(@PathVariable Long btId) {
        CommonResp resp = new CommonResp<>();

        String content = baseTagService.onlineBaseTag(btId);

        resp.setContent(content);

        return resp;
    }

    @GetMapping("/offlineBaseTag/{btId}")
    public CommonResp offlineBaseTag(@PathVariable Long btId) {
        CommonResp resp = new CommonResp<>();

        String content = baseTagService.offlineBaseTag(btId);
        resp.setContent(content);

        // resp.setContent('');

        return resp;
    }


}

