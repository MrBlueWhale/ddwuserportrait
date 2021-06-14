package com.ibegu.dalaoadmin.mapper;

import com.ibegu.dalaoadmin.domain.User;
import com.ibegu.dalaoadmin.domain.UserExample;
import java.util.List;

import com.ibegu.dalaoadmin.mapper.extendMapper.UserMapperExtend;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends UserMapperExtend {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}