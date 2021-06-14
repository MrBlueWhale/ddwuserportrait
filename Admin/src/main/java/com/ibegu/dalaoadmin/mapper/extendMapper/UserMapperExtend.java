package com.ibegu.dalaoadmin.mapper.extendMapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapperExtend {


    String findRoleNameByUid(@Param("uid") Long uid);

}