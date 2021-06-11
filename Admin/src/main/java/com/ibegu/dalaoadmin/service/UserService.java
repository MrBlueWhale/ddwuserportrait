package com.ibegu.dalaoadmin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibegu.dalaoadmin.domain.User;
import com.ibegu.dalaoadmin.domain.UserExample;
import com.ibegu.dalaoadmin.exception.BusinessException;
import com.ibegu.dalaoadmin.exception.BusinessExceptionCode;
import com.ibegu.dalaoadmin.mapper.UserMapper;
import com.ibegu.dalaoadmin.req.UserQueryReq;
import com.ibegu.dalaoadmin.req.UserResetPasswordReq;
import com.ibegu.dalaoadmin.req.UserSaveReq;
import com.ibegu.dalaoadmin.resp.PageResp;
import com.ibegu.dalaoadmin.resp.UserQueryResp;
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
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        if (!ObjectUtils.isEmpty(req.getUserName())) {
            criteria.andUserNameEqualTo(req.getUserName());
        }

        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> userList = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        // List<UserResp> respList = new ArrayList<>();
        // for (User user : userList) {
        //     // UserResp userResp = new UserResp();
        //     // BeanUtils.copyProperties(user, userResp);
        //     // 对象复制
        //     UserResp userResp = CopyUtil.copy(user, UserResp.class);
        //
        //     respList.add(userResp);
        // }

        // 列表复制
        List<UserQueryResp> list = CopyUtil.copyList(userList, UserQueryResp.class);

        PageResp<UserQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

     /**
      * 保存
      */
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            User userDB = selectByUserName(req.getUserName());
            if (ObjectUtils.isEmpty(userDB)) {
                // 新增
                user.setUid(snowFlake.nextId());
                user.setCreateTime(new Date());
                userMapper.insert(user);
            } else {
                // 用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            // 更新
            user.setUserName(null);
            user.setPassword(null);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    public void delete(Long uid) {
        userMapper.deleteByPrimaryKey(uid);
    }

    public User selectByUserName(String UserName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(UserName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 修改密码
     */
    public void resetPassword(UserResetPasswordReq req) {
        User user = CopyUtil.copy(req, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 登录
     */
    // public UserLoginResp login(UserLoginReq req) {
    //     User userDb = selectByUserName(req.getUserName());
    //     if (ObjectUtils.isEmpty(userDb)) {
    //         // 用户名不存在
    //         LOG.info("用户名不存在, {}", req.getUserName());
    //         throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
    //     } else {
    //         if (userDb.getPassword().equals(req.getPassword())) {
    //             // 登录成功
    //             UserLoginResp userLoginResp = CopyUtil.copy(userDb, UserLoginResp.class);
    //             return userLoginResp;
    //         } else {
    //             // 密码不对
    //             LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", req.getPassword(), userDb.getPassword());
    //             throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
    //         }
    //     }
    // }
}
