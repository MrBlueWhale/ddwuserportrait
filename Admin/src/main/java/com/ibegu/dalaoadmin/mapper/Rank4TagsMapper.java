package com.ibegu.dalaoadmin.mapper;

import com.ibegu.dalaoadmin.domain.Rank4Tags;
import com.ibegu.dalaoadmin.domain.Rank4TagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Rank4TagsMapper {
    long countByExample(Rank4TagsExample example);

    int deleteByExample(Rank4TagsExample example);

    int deleteByPrimaryKey(Long t4Id);

    int insert(Rank4Tags record);

    int insertSelective(Rank4Tags record);

    List<Rank4Tags> selectByExample(Rank4TagsExample example);

    Rank4Tags selectByPrimaryKey(Long t4Id);

    int updateByExampleSelective(@Param("record") Rank4Tags record, @Param("example") Rank4TagsExample example);

    int updateByExample(@Param("record") Rank4Tags record, @Param("example") Rank4TagsExample example);

    int updateByPrimaryKeySelective(Rank4Tags record);

    int updateByPrimaryKey(Rank4Tags record);
}