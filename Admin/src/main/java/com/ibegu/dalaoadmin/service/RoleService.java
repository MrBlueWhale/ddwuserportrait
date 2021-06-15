package com.ibegu.dalaoadmin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibegu.dalaoadmin.domain.Role;
import com.ibegu.dalaoadmin.domain.RoleExample;
import com.ibegu.dalaoadmin.domain.UserRole;
import com.ibegu.dalaoadmin.domain.UserRoleExample;
import com.ibegu.dalaoadmin.exception.BusinessException;
import com.ibegu.dalaoadmin.exception.BusinessExceptionCode;
import com.ibegu.dalaoadmin.mapper.RoleMapper;

import com.ibegu.dalaoadmin.mapper.UserRoleMapper;
import com.ibegu.dalaoadmin.req.RoleQueryReq;

import com.ibegu.dalaoadmin.req.RoleSaveReq;
import com.ibegu.dalaoadmin.resp.PageResp;

import com.ibegu.dalaoadmin.resp.RoleQueryResp;
import com.ibegu.dalaoadmin.utils.CopyUtil;
import com.ibegu.dalaoadmin.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/6/11 15:37
 **/



@Service
public class RoleService {

    private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private SnowFlake snowFlake;

    public PageResp<RoleQueryResp> list(RoleQueryReq req) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();

        if (!ObjectUtils.isEmpty(req.getRoleName())) {
            criteria.andRoleNameLike("%" + req.getRoleName() + "%");
        }

        if (!ObjectUtils.isEmpty(req.getDesc())) {
            criteria.andDescLike("%" + req.getDesc() + "%");
        }

        if (!ObjectUtils.isEmpty(req.getActive())) {
            criteria.andActiveEqualTo(req.getActive());
        }


        PageHelper.startPage(req.getPage(), req.getSize());
        List<Role> roleList = roleMapper.selectByExample(roleExample);

        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        // 列表复制
        List<RoleQueryResp> list = CopyUtil.copyList(roleList, RoleQueryResp.class);

        PageResp<RoleQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

     /**
      * 保存
      */
    public void save(RoleSaveReq req) {
        Role role = CopyUtil.copy(req, Role.class);
        if (ObjectUtils.isEmpty(req.getRid())) {
            Role roleDB = selectByRoleName(req.getRoleName());
            if (ObjectUtils.isEmpty(roleDB)) {
                // 新增
                role.setRid(snowFlake.nextId());
                role.setActive(0);
                role.setCreateTime(new Date());
                roleMapper.insert(role);
            } else {
                // 角色名已存在
                throw new BusinessException(BusinessExceptionCode.ROLE_NAME_EXIST);
            }
        } else {
            // 更新
            roleMapper.updateByPrimaryKeySelective(role);
        }
    }

    public void delete(Long rid) {
        roleMapper.deleteByPrimaryKey(rid);
    }

    public Role selectByRoleName(String RoleName) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRoleNameEqualTo(RoleName);
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        if (CollectionUtils.isEmpty(roleList)) {
            return null;
        } else {
            return roleList.get(0);
        }
    }

    public void distributeRole(UserRole userRole) {
        if(userRole.getUid()==null){
            LOG.info("结果：{}", userRole);
        }
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(userRole.getUid());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);

        LOG.info("结果：{}", userRole);
        if(!CollectionUtils.isEmpty(userRoles)) {
            userRoleMapper.deleteByPrimaryKey(userRole.getUid(),userRole.getRid());
        }

        userRoleMapper.insert(userRole);
    }

    public List<Role> listRole() {
        RoleExample example = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(example);

        LOG.info("我的结果:{}",roleList);
        return roleList;
    }

}
