package com.ibegu.dalaoadmin.mapper.extendMapper;

import org.apache.ibatis.annotations.Param;

public interface BaseTagMapperExtend {

    String findParentNameById(@Param("btId") Long btId);

}