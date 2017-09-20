package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.datautils.JedisUtils;
import com.dayuanit.emall.mapper.ProvinceAndCityMapper;
import com.dayuanit.emall.service.JedisService;
import com.dayuanit.emall.service.ProvinceAndCityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProvinceAndCityServiceImpl implements ProvinceAndCityService{

    Logger log = LoggerFactory.getLogger(ProvinceAndCityServiceImpl.class);

    @Resource(name="redisTempServiceImpl")//通过name来区分要注入哪个类
    private JedisService jedisService;

    @Autowired
    private ProvinceAndCityMapper provinceAndCityMapper;

    @Override
    public List<Map<String, String>> listProvince() {
        //先到缓存中查询，如果不存在到数据库中查询，然后再将数据放入到缓存中
        String provinceKey = "mall:province";

        boolean flag = jedisService.haskey(provinceKey);

        if (flag) {
            log.info(">>>>>>>>>>>>>>>>>>省份是走缓存查询的");
            return jedisService.getProvince(provinceKey);
        }

        List<Map<String, String>> provinceData = provinceAndCityMapper.listProvince();

        jedisService.setProvince(provinceKey, provinceData);

        return provinceData;
    }

    @Override
    public List<Map<String, String>> listCity(String provinceCode) {
        //先到缓冲中查询，如果不存在再到数据库中查询，然后再将数据放入redis中
        String cityKey = String.format("mall:city:%s", provinceCode);

        boolean flag = jedisService.haskey(cityKey);

        if (flag) {
            log.info(">>>>>>>>>>>>>>>>>>>>城市走缓存{}", cityKey);
            return jedisService.getCity(cityKey);
        }

        List<Map<String, String>> cityData = provinceAndCityMapper.listCity(provinceCode);

        jedisService.setCity(cityKey, cityData);

        return cityData;
    }

    @Override
    public List<Map<String, String>> listArea(String cityCode) {
        String areaKey = String.format("mall:area:%s", cityCode);

        boolean flag = jedisService.haskey(areaKey);

        if (flag) {
            log.info(">>>>>>>>>>>>区域走缓存查");
            return jedisService.getAres(areaKey);
        }

        List<Map<String, String>> areaData = provinceAndCityMapper.listArea(cityCode);

        jedisService.setArea(areaKey, areaData);

        return areaData;
    }

    @Override
    public String getProvinceName(int provinceCode) {
        return provinceAndCityMapper.getProvinceName(provinceCode);
    }

    @Override
    public String getCityName(int cityCode) {
        return provinceAndCityMapper.getCityName(cityCode);
    }

    @Override
    public String getAreaName(int areaCode) {
        return provinceAndCityMapper.getAreaName(areaCode);
    }
}
