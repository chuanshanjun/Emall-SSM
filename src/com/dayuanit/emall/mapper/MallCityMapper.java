package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallCity;
import com.dayuanit.emall.pojo.MallCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallCityMapper {
    long countByExample(MallCityExample example);

    int deleteByExample(MallCityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallCity record);

    int insertSelective(MallCity record);

    List<MallCity> selectByExample(MallCityExample example);

    MallCity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallCity record, @Param("example") MallCityExample example);

    int updateByExample(@Param("record") MallCity record, @Param("example") MallCityExample example);

    int updateByPrimaryKeySelective(MallCity record);

    int updateByPrimaryKey(MallCity record);
}