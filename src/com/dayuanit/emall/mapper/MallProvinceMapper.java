package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallProvince;
import com.dayuanit.emall.pojo.MallProvinceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProvinceMapper {
    long countByExample(MallProvinceExample example);

    int deleteByExample(MallProvinceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallProvince record);

    int insertSelective(MallProvince record);

    List<MallProvince> selectByExample(MallProvinceExample example);

    MallProvince selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallProvince record, @Param("example") MallProvinceExample example);

    int updateByExample(@Param("record") MallProvince record, @Param("example") MallProvinceExample example);

    int updateByPrimaryKeySelective(MallProvince record);

    int updateByPrimaryKey(MallProvince record);
}