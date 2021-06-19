package com.ibegu.dalaoadmin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibegu.dalaoadmin.domain.*;
import com.ibegu.dalaoadmin.mapper.BaseTagMapper;
import com.ibegu.dalaoadmin.mapper.Rank3TagsMapper;
import com.ibegu.dalaoadmin.mapper.Rank4TagsMapper;
import com.ibegu.dalaoadmin.mapper.Rank5TagsMapper;
import com.ibegu.dalaoadmin.req.Rank4TagsQueryReq;
import com.ibegu.dalaoadmin.req.Rank5TagsQueryReq;
import com.ibegu.dalaoadmin.resp.*;
import com.ibegu.dalaoadmin.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

// import com.ibegu.dalaoadmin.domain.UserRank3Tags;
// import com.ibegu.dalaoadmin.domain.UserRank3TagsExample;
// import com.ibegu.dalaoadmin.mapper.UserRank3TagsMapper;
// import com.ibegu.dalaoadmin.req.Rank3TagsQueryReq;
// import com.ibegu.dalaoadmin.req.Rank3TagsSaveReq;
// import com.ibegu.dalaoadmin.resp.Rank3TagsQueryResp;

/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/6/11 15:37
 **/


@Service
public class BaseTagService {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTagService.class);

    @Resource
    private BaseTagMapper baseTagMapper;

    @Resource
    private Rank3TagsMapper rank3TagsMapper;

    @Resource
    private Rank4TagsMapper rank4TagsMapper;

    @Resource
    private Rank5TagsMapper rank5TagsMapper;

    @Resource
    private SnowFlake snowFlake;

    // public PageResp<Rank3TagsQueryResp> list(Rank3TagsQueryReq req) {
    //     Rank3TagsExample rank3TagsExample = new Rank3TagsExample();
    //     Rank3TagsExample.Criteria criteria = rank3TagsExample.createCriteria();
    //
    //     if (!ObjectUtils.isEmpty(req.getRank3TagsName())) {
    //         criteria.andRank3TagsNameLike("%" + req.getRank3TagsName() + "%");
    //     }
    //
    //     PageHelper.startPage(req.getPage(), req.getSize());
    //     List<Rank3Tags> rank3TagsList = rank3TagsMapper.selectByExample(rank3TagsExample);
    //
    //     PageInfo<Rank3Tags> pageInfo = new PageInfo<>(rank3TagsList);
    //     LOG.info("总行数：{}", pageInfo.getTotal());
    //     LOG.info("总页数：{}", pageInfo.getPages());
    //
    //
    //     // 列表复制
    //     List<Rank3TagsQueryResp> list = CopyUtil.copyList(rank3TagsList, Rank3TagsQueryResp.class);
    //
    //     PageResp<Rank3TagsQueryResp> pageResp = new PageResp();
    //     pageResp.setTotal(pageInfo.getTotal());
    //     pageResp.setList(list);
    //
    //     return pageResp;
    // }

    /**
     * 保存
     * @return
     */
    // public void save(Rank3TagsSaveReq req) {
    //     Rank3Tags rank3Tags = CopyUtil.copy(req, Rank3Tags.class);
    //     if (ObjectUtils.isEmpty(req.getRid())) {
    //         Rank3Tags rank3TagsDB = selectByRank3TagsName(req.getRank3TagsName());
    //         if (ObjectUtils.isEmpty(rank3TagsDB)) {
    //             // 新增
    //             rank3Tags.setRid(snowFlake.nextId());
    //             rank3Tags.setActive(0);
    //             rank3Tags.setCreateTime(new Date());
    //             rank3TagsMapper.insert(rank3Tags);
    //         } else {
    //             // 角色名已存在
    //             throw new BusinessException(BusinessExceptionCode.ROLE_NAME_EXIST);
    //         }
    //     } else {
    //         // 更新
    //         rank3TagsMapper.updateByPrimaryKeySelective(rank3Tags);
    //     }
    // }

    // public void delete(Long rid) {
    //     rank3TagsMapper.deleteByPrimaryKey(rid);
    // }

    // public Rank3Tags selectByRank3TagsName(String Rank3TagsName) {
    //     Rank3TagsExample rank3TagsExample = new Rank3TagsExample();
    //     Rank3TagsExample.Criteria criteria = rank3TagsExample.createCriteria();
    //     criteria.andRank3TagsNameEqualTo(Rank3TagsName);
    //     List<Rank3Tags> rank3TagsList = rank3TagsMapper.selectByExample(rank3TagsExample);
    //     if (CollectionUtils.isEmpty(rank3TagsList)) {
    //         return null;
    //     } else {
    //         return rank3TagsList.get(0);
    //     }
    // }

    // public void distributeRank3Tags(UserRank3Tags userRank3Tags) {
    //     if(userRank3Tags.getUid()==null){
    //         LOG.info("结果：{}", userRank3Tags);
    //     }
    //     UserRank3TagsExample example = new UserRank3TagsExample();
    //     UserRank3TagsExample.Criteria criteria = example.createCriteria();
    //     criteria.andUidEqualTo(userRank3Tags.getUid());
    //     List<UserRank3Tags> userRank3Tagss = userRank3TagsMapper.selectByExample(example);
    //
    //     LOG.info("结果：{}", userRank3Tags);
    //     if(!CollectionUtils.isEmpty(userRank3Tagss)) {
    //         userRank3TagsMapper.deleteByPrimaryKey(userRank3Tags.getUid(),userRank3Tags.getRid());
    //     }
    //
    //     userRank3TagsMapper.insert(userRank3Tags);
    // }

    public List<BaseTagQueryResp> listBaseTags() {

        BaseTagExample example = new BaseTagExample();
        List<BaseTag> baseTagList = baseTagMapper.selectByExample(example);

        // rank3TagsList.size();

        // 列表复制
        List<BaseTagQueryResp> list = CopyUtil.copyList(baseTagList, BaseTagQueryResp.class);

        List<TreeNode> tagNodes = new ArrayList<>();
        for (BaseTag baseTag : baseTagList) {

            //单体对象复制
            TreeNode treeNode = new TreeNode(baseTag.getBtId(),baseTag.getBtName(),baseTag.getParentId());
            // demoResp.setId(123L);
            tagNodes.add(treeNode);
        }



        LOG.info("我的结果:{}", list);
        return list;

    }

public List<TagNode> listBaseTagsTreeNodes() {

        BaseTagExample example = new BaseTagExample();
        List<BaseTag> baseTagList = baseTagMapper.selectByExample(example);

        List<TagNode> tagNodes = new ArrayList<>();
        for (BaseTag baseTag : baseTagList) {
            //单体对象复制
            TagNode tagNode = new TagNode(baseTag.getBtId(),baseTag.getBtName(),baseTag.getParentId());
            // demoResp.setId(123L);
            tagNodes.add(tagNode);
        }
        LOG.info("我的结果:{}", tagNodes);
        return tagNodes;

    }

    public List<BaseTagQueryResp> listBaseTagsAudit() {
        BaseTagExample example = new BaseTagExample();
        BaseTagExample.Criteria criteria = example.createCriteria();
        criteria.andProcessStatusEqualTo(1);
        List<BaseTag> baseTagList = baseTagMapper.selectByExample(example);

        // 列表复制
        List<BaseTagQueryResp> list = CopyUtil.copyList(baseTagList, BaseTagQueryResp.class);

        //把它父节点也查出来 前端构建树
        for (BaseTag baseTag : baseTagList) {
            //加入列表前 判断列表内是否已有该父节点
            // if()
            BaseTag parent = baseTagMapper.selectByPrimaryKey(baseTag.getParentId());

            Boolean tagDulplite = false;

            for (BaseTagQueryResp checkTag : list) {
                if (checkTag.getBtId().equals(baseTag.getParentId())) {
                    tagDulplite = true;
                    break;
                } else {
                    continue;
                }
            }

            if (!tagDulplite) {
                list.add(CopyUtil.copy(parent, BaseTagQueryResp.class));
            }


        }


        LOG.info("我的结果:{}", list);
        return list;

    }

    public TagResp<Rank3TagsQueryResp> listRank3Tags() {
        Rank3TagsExample example = new Rank3TagsExample();
        List<Rank3Tags> rank3TagsList = rank3TagsMapper.selectByExample(example);

        // rank3TagsList.size();

        // 列表复制
        List<Rank3TagsQueryResp> list = CopyUtil.copyList(rank3TagsList, Rank3TagsQueryResp.class);

        TagResp<Rank3TagsQueryResp> tagResp = new TagResp();
        tagResp.setTotal(rank3TagsList.size());
        tagResp.setList(list);


        LOG.info("我的结果:{}", list);
        return tagResp;
    }

    public PageResp<Rank4TagsQueryResp> listRank4Tags(Rank4TagsQueryReq req) {

        Rank4TagsExample rank4TagsExample = new Rank4TagsExample();
        Rank4TagsExample.Criteria criteria = rank4TagsExample.createCriteria();

        if (!ObjectUtils.isEmpty(req.getT4Name())) {
            criteria.andT4NameLike("%" + req.getT4Name() + "%");
        }

        if (!ObjectUtils.isEmpty(req.getProcessStatus())) {
            criteria.andProcessStatusEqualTo(req.getProcessStatus());
        }

        if (!ObjectUtils.isEmpty(req.getAuditStatus())) {
            criteria.andAuditStatusEqualTo(req.getAuditStatus());
        }

        if (!ObjectUtils.isEmpty(req.getDesc())) {
            criteria.andDescLike("%" + req.getDesc() + "%");
        }

        if (!ObjectUtils.isEmpty(req.getT3Name())) {
            Rank3Tags rank3Tag = getT3IdByT3Name(req.getT3Name());
            criteria.andT3IdEqualTo(rank3Tag.getT3Id());
        }



        PageHelper.startPage(req.getPage(), req.getSize());
        List<Rank4Tags> rank4TagsList = rank4TagsMapper.selectByExample(rank4TagsExample);

        PageInfo<Rank4Tags> pageInfo = new PageInfo<>(rank4TagsList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        // 列表复制
        List<Rank4TagsQueryResp> list = CopyUtil.copyList(rank4TagsList, Rank4TagsQueryResp.class);

        PageResp<Rank4TagsQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

        // return null;

    }

    public PageResp<Rank5TagsQueryResp> listRank5Tags(Rank5TagsQueryReq req) {

        Rank5TagsExample rank5TagsExample = new Rank5TagsExample();
        Rank5TagsExample.Criteria criteria = rank5TagsExample.createCriteria();

        if (!ObjectUtils.isEmpty(req.getT5Name())) {
            criteria.andT5NameLike("%" + req.getT5Name() + "%");
        }  if (!ObjectUtils.isEmpty(req.getProcessStatus())) {
            criteria.andProcessStatusEqualTo(req.getProcessStatus());
        }  if (!ObjectUtils.isEmpty(req.getAuditStatus())) {
            criteria.andAuditStatusEqualTo(req.getAuditStatus());
        }  if (!ObjectUtils.isEmpty(req.getDesc())) {
            criteria.andDescLike("%" + req.getDesc() + "%");
        }

        if (!ObjectUtils.isEmpty(req.getT4Name())) {
            Rank4Tags rank4Tag = getT4IdByT3Name(req.getT4Name());
            criteria.andT4IdEqualTo(rank4Tag.getT4Id());
        }



        PageHelper.startPage(req.getPage(), req.getSize());
        List<Rank5Tags> rank5TagsList = rank5TagsMapper.selectByExample(rank5TagsExample);

        PageInfo<Rank4Tags> pageInfo = new PageInfo(rank5TagsList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        // 列表复制
        List<Rank5TagsQueryResp> list = CopyUtil.copyList(rank5TagsList, Rank5TagsQueryResp.class);

        PageResp<Rank5TagsQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

        // return null;

    }

    public Rank3Tags getT3IdByT3Name(String T3Name) {
        Rank3TagsExample rank3TagsExample = new Rank3TagsExample();
        Rank3TagsExample.Criteria criteria = rank3TagsExample.createCriteria();
        criteria.andT3NameEqualTo(T3Name);
        List<Rank3Tags> rank3Tags = rank3TagsMapper.selectByExample(rank3TagsExample);
        if (CollectionUtils.isEmpty(rank3Tags)) {
            return null;
        } else {
            return rank3Tags.get(0);
        }
    }

    public Rank4Tags getT4IdByT3Name(String T4Name) {
        Rank4TagsExample rank4TagsExample = new Rank4TagsExample();
        Rank4TagsExample.Criteria criteria = rank4TagsExample.createCriteria();
        criteria.andT4NameEqualTo(T4Name);
        List<Rank4Tags> rank4Tags = rank4TagsMapper.selectByExample(rank4TagsExample);
        if (CollectionUtils.isEmpty(rank4Tags)) {
            return null;
        } else {
            return rank4Tags.get(0);
        }
    }

    public List listBaseTagsTree() {
        Rank5TagsExample rank5TagsExample = new Rank5TagsExample();
        List<Rank5Tags> rank5TagsList = rank5TagsMapper.selectByExample(rank5TagsExample);
        List<TreeNode> rank5TNodes = new ArrayList<>();
         for (Rank5Tags rank5Tag : rank5TagsList) {

             TreeNode treeNode = new TreeNode(rank5Tag.getT5Id(),rank5Tag.getT5Name(),rank5Tag.getT4Id());
             LOG.info("rank5Node:{}",treeNode);
             // demoResp.setId(123L);
             rank5TNodes.add(treeNode);
         }

        Rank4TagsExample rank4TagsExample = new Rank4TagsExample();
        List<Rank4Tags> rank4TagsList = rank4TagsMapper.selectByExample(rank4TagsExample);
        List<TreeNode> rank4TNodes = new ArrayList<>();
        for (Rank4Tags rank4Tag : rank4TagsList) {

            //单体对象复制
            TreeNode treeNode = new TreeNode(rank4Tag.getT4Id(),rank4Tag.getT4Name(),rank4Tag.getT3Id());
            // demoResp.setId(123L);
            rank4TNodes.add(treeNode);
        }

        Rank3TagsExample rank3TagsExample = new Rank3TagsExample();
        List<Rank3Tags> rank3TagsList = rank3TagsMapper.selectByExample(rank3TagsExample);
        List<TreeNode> rank3TNodes = new ArrayList<>();
        for (Rank3Tags rank3Tag : rank3TagsList) {

            //单体对象复制
            TreeNode treeNode = new TreeNode(rank3Tag.getT3Id(),rank3Tag.getT3Name(),Long.valueOf(10));
            // demoResp.setId(123L);
            rank3TNodes.add(treeNode);
        }

        List<TreeNode> tagNodes = new ArrayList<>();
        tagNodes.addAll(rank3TNodes);
        tagNodes.addAll(rank4TNodes);
        tagNodes.addAll(rank5TNodes);

        List<TreeNode> trees1 = TreeBuilder.bulid(tagNodes);
        LOG.info("trees1:{}",trees1);

        //第二种方式
//        List<TreeNode> trees2 = TreeBuilder.buildByRecursive(tagNodes);
//        LOG.info("trees2:{}",trees2);


        return trees1;
//        return null;


    }

    public String offlineBaseTag(Long btId) {
        // 五级标签处理手段---直接上线
        if (btId >= 300) {
            BaseTag baseTag = baseTagMapper.selectByPrimaryKey(btId);
            // if (baseTag.getProcessStatus() == 3 || baseTag.getProcessStatus() == 5) {
            baseTag.setProcessStatus(5);
            baseTagMapper.updateByPrimaryKeySelective(baseTag);
            // }
        } else if (btId >= 200) {
            //    四级标签
            BaseTag baseTag = baseTagMapper.selectByPrimaryKey(btId);
            baseTag.setProcessStatus(5);
            baseTagMapper.updateByPrimaryKeySelective(baseTag);

            BaseTagExample example = new BaseTagExample();
            BaseTagExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(btId);

            List<BaseTag> baseTagList = baseTagMapper.selectByExample(example);
            for (BaseTag baseTagDB : baseTagList) {
                if (baseTagDB.getProcessStatus() == 4) {
                    baseTagDB.setProcessStatus(5);
                    baseTagMapper.updateByPrimaryKeySelective(baseTagDB);
                }

            }

        }

        String success = "能下的都下了!!";
        return success;

    }

    public String onlineBaseTag(Long btId) {
        // 五级标签处理手段---直接上线
        if (btId >= 300) {
            BaseTag baseTag = baseTagMapper.selectByPrimaryKey(btId);
            // if (baseTag.getProcessStatus() == 3 || baseTag.getProcessStatus() == 5) {
            baseTag.setProcessStatus(4);
            baseTagMapper.updateByPrimaryKeySelective(baseTag);
            // }
        } else if (btId >= 200) {
            //    四级标签
            BaseTag baseTag = baseTagMapper.selectByPrimaryKey(btId);
            baseTag.setProcessStatus(4);
            baseTagMapper.updateByPrimaryKeySelective(baseTag);

            BaseTagExample example = new BaseTagExample();
            BaseTagExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(btId);

            List<BaseTag> baseTagList = baseTagMapper.selectByExample(example);
            for (BaseTag baseTagDB : baseTagList) {
                if (baseTagDB.getProcessStatus() == 3 || baseTagDB.getProcessStatus() == 5) {
                    baseTagDB.setProcessStatus(4);
                    baseTagMapper.updateByPrimaryKeySelective(baseTagDB);
                }

            }

        }

        String success = "能上的都上了!!";
        return success;

    }

    public void banBaseTag(Long btId) {
        // 五级标签处理手段---直接禁用
        if (btId >= 300) {
            BaseTag baseTag = baseTagMapper.selectByPrimaryKey(btId);
            baseTag.setProcessStatus(6);
            baseTagMapper.updateByPrimaryKeySelective(baseTag);
        } else if (btId >= 200) {
            //    四级标签
            BaseTag baseTag = baseTagMapper.selectByPrimaryKey(btId);
            baseTag.setProcessStatus(6);
            baseTagMapper.updateByPrimaryKeySelective(baseTag);

            BaseTagExample example = new BaseTagExample();
            BaseTagExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(btId);

            List<BaseTag> baseTagList = baseTagMapper.selectByExample(example);
            for (BaseTag baseTagDB : baseTagList) {
                // if(baseTagDB.getProcessStatus()){
                baseTagDB.setProcessStatus(6);
                baseTagMapper.updateByPrimaryKeySelective(baseTagDB);
                // }

            }

        }

    }

    public void editBaseTag(Long btId) {

    }






    // public BaseTagsTreeResp listBaseTagsTree() {
    //
    //     return null;
    // }



    // public PageResp<Rank5TagsQueryResp> listRank5Tags(Rank5TagsQueryReq req) {
    //
    //     return null;
    //
    // }
}
