package com.ibegu.dalaoadmin.mapper;

import com.ibegu.dalaoadmin.domain.BaseTag;
import com.ibegu.dalaoadmin.domain.BaseTagExample;
import com.ibegu.dalaoadmin.mapper.extendMapper.BaseTagMapperExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseTagMapper extends BaseTagMapperExtend {
    long countByExample(BaseTagExample example);

    int deleteByExample(BaseTagExample example);

    int deleteByPrimaryKey(Long btId);

    int insert(BaseTag record);

    int insertSelective(BaseTag record);

    List<BaseTag> selectByExample(BaseTagExample example);

    BaseTag selectByPrimaryKey(Long btId);

    int updateByExampleSelective(@Param("record") BaseTag record, @Param("example") BaseTagExample example);

    int updateByExample(@Param("record") BaseTag record, @Param("example") BaseTagExample example);

    int updateByPrimaryKeySelective(BaseTag record);

    int updateByPrimaryKey(BaseTag record);


}