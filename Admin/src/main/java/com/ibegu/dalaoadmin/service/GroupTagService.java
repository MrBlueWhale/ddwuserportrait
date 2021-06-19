package com.ibegu.dalaoadmin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibegu.dalaoadmin.domain.*;
import com.ibegu.dalaoadmin.mapper.*;
import com.ibegu.dalaoadmin.req.Rank4TagsQueryReq;
import com.ibegu.dalaoadmin.req.Rank5TagsQueryReq;
import com.ibegu.dalaoadmin.resp.*;
import com.ibegu.dalaoadmin.utils.CopyUtil;
import com.ibegu.dalaoadmin.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/6/11 15:37
 **/


@Service
public class GroupTagService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupTagService.class);

    @Resource
    private GroupTagMapper groupTagMapper;

    @Resource
    private Rank3TagsMapper rank3TagsMapper;

    @Resource
    private Rank4TagsMapper rank4TagsMapper;

    @Resource
    private Rank5TagsMapper rank5TagsMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private BaseTagMapper baseTagMapper;


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

    

    public List<GroupTagQueryResp> listGroupTags() {

        List<GroupTagQueryResp> list = new ArrayList<>();


        List<Long> groupTagIds =  groupTagMapper.findGroupTagIds();
        LOG.info("groupTagIds: {}", groupTagIds);
        if(ObjectUtils.isEmpty(groupTagIds)){
            return null;
        }

        for(Long groupTagId : groupTagIds){
            List<GroupTag> base5Tags = groupTagMapper.findBaseTagByGtId(groupTagId);
            LOG.info("base5Tags:{}", base5Tags);
            Map<String,String> baseTagNames = new HashMap<>();

            GroupTagQueryResp groupTagQueryResp = new GroupTagQueryResp();
            groupTagQueryResp.setGtId(groupTagId);
            groupTagQueryResp.setDesc(base5Tags.get(0).getDesc());
            groupTagQueryResp.setStatus(base5Tags.get(0).getStatus());

            for (GroupTag groupTag : base5Tags) {
//                LOG.info("groupTag:{}", groupTag);

                String rank4TagName = baseTagMapper.findParentNameById(groupTag.getBt5Id());

                baseTagNames.put(rank4TagName, groupTag.getBt5Name());
//                LOG.info("baseTagNames:{}", baseTagNames);
            }
            groupTagQueryResp.setBaseTagNames(baseTagNames);
            //baseTagNames.clear();
            list.add(groupTagQueryResp);
//            LOG.info("list:{}", list);
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

//    public List listGroupTagsTree() {
//        Rank5TagsExample rank5TagsExample = new Rank5TagsExample();
//        List<Rank5Tags> rank5TagsList = rank5TagsMapper.selectByExample(rank5TagsExample);
//        List<TreeNode> rank5TNodes = new ArrayList<>();
//         for (Rank5Tags rank5Tag : rank5TagsList) {
//
//             TreeNode treeNode = new TreeNode(rank5Tag.getT5Id(),rank5Tag.getT5Name(),rank5Tag.getT4Id());
//             LOG.info("rank5Node:{}",treeNode);
//             // demoResp.setId(123L);
//             rank5TNodes.add(treeNode);
//         }
//
//        Rank4TagsExample rank4TagsExample = new Rank4TagsExample();
//        List<Rank4Tags> rank4TagsList = rank4TagsMapper.selectByExample(rank4TagsExample);
//        List<TreeNode> rank4TNodes = new ArrayList<>();
//        for (Rank4Tags rank4Tag : rank4TagsList) {
//
//            //单体对象复制
//            TreeNode treeNode = new TreeNode(rank4Tag.getT4Id(),rank4Tag.getT4Name(),rank4Tag.getT3Id());
//            // demoResp.setId(123L);
//            rank4TNodes.add(treeNode);
//        }
//
//        Rank3TagsExample rank3TagsExample = new Rank3TagsExample();
//        List<Rank3Tags> rank3TagsList = rank3TagsMapper.selectByExample(rank3TagsExample);
//        List<TreeNode> rank3TNodes = new ArrayList<>();
//        for (Rank3Tags rank3Tag : rank3TagsList) {
//
//            //单体对象复制
//            TreeNode treeNode = new TreeNode(rank3Tag.getT3Id(),rank3Tag.getT3Name(),Long.valueOf(10));
//            // demoResp.setId(123L);
//            rank3TNodes.add(treeNode);
//        }
//
//        List<TreeNode> tagNodes = new ArrayList<>();
//        tagNodes.addAll(rank3TNodes);
//        tagNodes.addAll(rank4TNodes);
//        tagNodes.addAll(rank5TNodes);
//
//        List<TreeNode> trees1 = TreeBuilder.bulid(tagNodes);
//        LOG.info("trees1:{}",trees1);
//
//        //第二种方式
////        List<TreeNode> trees2 = TreeBuilder.buildByRecursive(tagNodes);
////        LOG.info("trees2:{}",trees2);
//
//
//        return trees1;
////        return null;
//
//
//    }

//    public String offlineGroupTag(Long btId) {
//        // 五级标签处理手段---直接上线
//        if (btId >= 300) {
//            GroupTag groupTag = groupTagMapper.selectByPrimaryKey(btId);
//            // if (groupTag.getProcessStatus() == 3 || groupTag.getProcessStatus() == 5) {
//            groupTag.setProcessStatus(5);
//            groupTagMapper.updateByPrimaryKeySelective(groupTag);
//            // }
//        } else if (btId >= 200) {
//            //    四级标签
//            GroupTag groupTag = groupTagMapper.selectByPrimaryKey(btId);
//            groupTag.setProcessStatus(5);
//            groupTagMapper.updateByPrimaryKeySelective(groupTag);
//
//            GroupTagExample example = new GroupTagExample();
//            GroupTagExample.Criteria criteria = example.createCriteria();
//            criteria.andParentIdEqualTo(btId);
//
//            List<GroupTag> groupTagList = groupTagMapper.selectByExample(example);
//            for (GroupTag groupTagDB : groupTagList) {
//                if (groupTagDB.getProcessStatus() == 4) {
//                    groupTagDB.setProcessStatus(5);
//                    groupTagMapper.updateByPrimaryKeySelective(groupTagDB);
//                }
//
//            }
//
//        }
//
//        String success = "能下的都下了!!";
//        return success;
//
//    }

//    public String onlineGroupTag(Long btId) {
//        // 五级标签处理手段---直接上线
//        if (btId >= 300) {
//            GroupTag groupTag = groupTagMapper.selectByPrimaryKey(btId);
//            // if (groupTag.getProcessStatus() == 3 || groupTag.getProcessStatus() == 5) {
//            groupTag.setProcessStatus(4);
//            groupTagMapper.updateByPrimaryKeySelective(groupTag);
//            // }
//        } else if (btId >= 200) {
//            //    四级标签
//            GroupTag groupTag = groupTagMapper.selectByPrimaryKey(btId);
//            groupTag.setProcessStatus(4);
//            groupTagMapper.updateByPrimaryKeySelective(groupTag);
//
//            GroupTagExample example = new GroupTagExample();
//            GroupTagExample.Criteria criteria = example.createCriteria();
//            criteria.andParentIdEqualTo(btId);
//
//            List<GroupTag> groupTagList = groupTagMapper.selectByExample(example);
//            for (GroupTag groupTagDB : groupTagList) {
//                if (groupTagDB.getProcessStatus() == 3 || groupTagDB.getProcessStatus() == 5) {
//                    groupTagDB.setProcessStatus(4);
//                    groupTagMapper.updateByPrimaryKeySelective(groupTagDB);
//                }
//
//            }
//
//        }
//
//        String success = "能上的都上了!!";
//        return success;
//
//    }

//    public void banGroupTag(Long btId) {
//        // 五级标签处理手段---直接禁用
//        if (btId >= 300) {
//            GroupTag groupTag = groupTagMapper.selectByPrimaryKey(btId);
//            groupTag.setProcessStatus(6);
//            groupTagMapper.updateByPrimaryKeySelective(groupTag);
//        } else if (btId >= 200) {
//            //    四级标签
//            GroupTag groupTag = groupTagMapper.selectByPrimaryKey(btId);
//            groupTag.setProcessStatus(6);
//            groupTagMapper.updateByPrimaryKeySelective(groupTag);
//
//            GroupTagExample example = new GroupTagExample();
//            GroupTagExample.Criteria criteria = example.createCriteria();
//            criteria.andParentIdEqualTo(btId);
//
//            List<GroupTag> groupTagList = groupTagMapper.selectByExample(example);
//            for (GroupTag groupTagDB : groupTagList) {
//                // if(groupTagDB.getProcessStatus()){
//                groupTagDB.setProcessStatus(6);
//                groupTagMapper.updateByPrimaryKeySelective(groupTagDB);
//                // }
//
//            }
//
//        }
//
//    }



    // public PageResp<Rank5TagsQueryResp> listRank5Tags(Rank5TagsQueryReq req) {
    //
    //     return null;
    //
    // }
}
