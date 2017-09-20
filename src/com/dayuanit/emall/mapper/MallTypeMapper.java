package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallType;
import com.dayuanit.emall.pojo.MallTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallTypeMapper {
    long countByExample(MallTypeExample example);

    int deleteByExample(MallTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallType record);

    int insertSelective(MallType record);

    List<MallType> selectByExample(MallTypeExample example);

    MallType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallType record, @Param("example") MallTypeExample example);

    int updateByExample(@Param("record") MallType record, @Param("example") MallTypeExample example);

    int updateByPrimaryKeySelective(MallType record);

    int updateByPrimaryKey(MallType record);
}