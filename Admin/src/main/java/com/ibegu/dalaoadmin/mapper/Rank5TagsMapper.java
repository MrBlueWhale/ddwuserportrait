package com.ibegu.dalaoadmin.mapper;

import com.ibegu.dalaoadmin.domain.Rank5Tags;
import com.ibegu.dalaoadmin.domain.Rank5TagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Rank5TagsMapper {
    long countByExample(Rank5TagsExample example);

    int deleteByExample(Rank5TagsExample example);

    int deleteByPrimaryKey(Long t5Id);

    int insert(Rank5Tags record);

    int insertSelective(Rank5Tags record);

    List<Rank5Tags> selectByExample(Rank5TagsExample example);

    Rank5Tags selectByPrimaryKey(Long t5Id);

    int updateByExampleSelective(@Param("record") Rank5Tags record, @Param("example") Rank5TagsExample example);

    int updateByExample(@Param("record") Rank5Tags record, @Param("example") Rank5TagsExample example);

    int updateByPrimaryKeySelective(Rank5Tags record);

    int updateByPrimaryKey(Rank5Tags record);
}