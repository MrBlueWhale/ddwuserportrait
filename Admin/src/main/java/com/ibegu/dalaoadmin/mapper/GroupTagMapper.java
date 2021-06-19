package com.ibegu.dalaoadmin.mapper;

import com.ibegu.dalaoadmin.domain.GroupTag;
import com.ibegu.dalaoadmin.domain.GroupTagExample;
import com.ibegu.dalaoadmin.mapper.extendMapper.GroupTagMapperExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupTagMapper extends GroupTagMapperExtend {
    long countByExample(GroupTagExample example);

    int deleteByExample(GroupTagExample example);

    int deleteByPrimaryKey(@Param("gtId") Long gtId, @Param("bt5Id") Long bt5Id);

    int insert(GroupTag record);

    int insertSelective(GroupTag record);

    List<GroupTag> selectByExample(GroupTagExample example);

    GroupTag selectByPrimaryKey(@Param("gtId") Long gtId, @Param("bt5Id") Long bt5Id);

    int updateByExampleSelective(@Param("record") GroupTag record, @Param("example") GroupTagExample example);

    int updateByExample(@Param("record") GroupTag record, @Param("example") GroupTagExample example);

    int updateByPrimaryKeySelective(GroupTag record);

    int updateByPrimaryKey(GroupTag record);
}