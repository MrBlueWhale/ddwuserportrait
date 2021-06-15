package com.ibegu.dalaoadmin.mapper;

import com.ibegu.dalaoadmin.domain.Rank3Tags;
import com.ibegu.dalaoadmin.domain.Rank3TagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Rank3TagsMapper {
    long countByExample(Rank3TagsExample example);

    int deleteByExample(Rank3TagsExample example);

    int deleteByPrimaryKey(Long t3Id);

    int insert(Rank3Tags record);

    int insertSelective(Rank3Tags record);

    List<Rank3Tags> selectByExample(Rank3TagsExample example);

    Rank3Tags selectByPrimaryKey(Long t3Id);

    int updateByExampleSelective(@Param("record") Rank3Tags record, @Param("example") Rank3TagsExample example);

    int updateByExample(@Param("record") Rank3Tags record, @Param("example") Rank3TagsExample example);

    int updateByPrimaryKeySelective(Rank3Tags record);

    int updateByPrimaryKey(Rank3Tags record);
}