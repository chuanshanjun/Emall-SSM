package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallOrder;
import com.dayuanit.emall.pojo.MallOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrderMapper {
    long countByExample(MallOrderExample example);

    int deleteByExample(MallOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallOrder record);

    int insertSelective(MallOrder record);

    List<MallOrder> selectByExample(MallOrderExample example);

    int updateByExampleSelective(@Param("record") MallOrder record, @Param("example") MallOrderExample example);

    int updateByExample(@Param("record") MallOrder record, @Param("example") MallOrderExample example);
}