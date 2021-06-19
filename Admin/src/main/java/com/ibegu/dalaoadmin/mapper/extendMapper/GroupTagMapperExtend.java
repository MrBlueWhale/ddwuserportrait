package com.ibegu.dalaoadmin.mapper.extendMapper;

import com.ibegu.dalaoadmin.domain.GroupTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupTagMapperExtend {

    List<Long> findGroupTagIds();

    List<GroupTag> findBaseTagByGtId(@Param("groupTagId") Long groupTagId);
}