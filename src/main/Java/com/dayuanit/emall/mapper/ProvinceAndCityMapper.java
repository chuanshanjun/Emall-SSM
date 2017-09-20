package com.dayuanit.emall.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProvinceAndCityMapper {

    List<Map<String, String>> listProvince();

    List<Map<String, String>> listCity(@Param("provinceCode") String provinceCode);

    List<Map<String, String>> listArea(@Param("cityCode") String cityCode);

    String getProvinceName(@Param("provinceCode") Integer provinceCode);

    String getCityName(@Param("cityCode") Integer cityCode);

    String getAreaName(@Param("areaCode") Integer areaCode);
}
