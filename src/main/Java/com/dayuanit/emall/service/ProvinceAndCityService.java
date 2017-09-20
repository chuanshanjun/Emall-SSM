package com.dayuanit.emall.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProvinceAndCityService {

    List<Map<String, String>> listProvince();

    List<Map<String, String>> listCity(String provinceCode);

    List<Map<String, String>> listArea(String cityCode);

    String getProvinceName(int provinceCode);

    String getCityName(int cityCode);

    String getAreaName(int areaCode);

}
