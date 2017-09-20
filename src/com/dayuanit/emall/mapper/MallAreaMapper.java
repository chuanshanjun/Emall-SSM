package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallArea;
import com.dayuanit.emall.pojo.MallAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallAreaMapper {
    long countByExample(MallAreaExample example);

    int deleteByExample(MallAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallArea record);

    int insertSelective(MallArea record);

    List<MallArea> selectByExample(MallAreaExample example);

    MallArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallArea record, @Param("example") MallAreaExample example);

    int updateByExample(@Param("record") MallArea record, @Param("example") MallAreaExample example);

    int updateByPrimaryKeySelective(MallArea record);

    int updateByPrimaryKey(MallArea record);
}