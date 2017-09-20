package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallAddress;
import com.dayuanit.emall.pojo.MallAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallAddressMapper {
    long countByExample(MallAddressExample example);

    int deleteByExample(MallAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallAddress record);

    int insertSelective(MallAddress record);

    List<MallAddress> selectByExample(MallAddressExample example);

    MallAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallAddress record, @Param("example") MallAddressExample example);

    int updateByExample(@Param("record") MallAddress record, @Param("example") MallAddressExample example);

    int updateByPrimaryKeySelective(MallAddress record);

    int updateByPrimaryKey(MallAddress record);
}