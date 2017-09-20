package com.dayuanit.emall.test;

import com.dayuanit.emall.mapper.ProvinceAndCityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/spring/spring-app.xml")
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ProvinceAndCityMapperTest {

    Logger log = LoggerFactory.getLogger(ProvinceAndCityMapperTest.class);

    @Autowired
    private ProvinceAndCityMapper provinceAndCityMapper;

    @Test
    @Rollback
    public void testListProvince() {
        List<Map<String, String>> list = provinceAndCityMapper.listProvince();
        log.info("==============list.size:{}", list.size());
        assertEquals(34, list.size());
    }

    @Test
    @Rollback
    public void testListCity() {
        List<Map<String, String>> list = provinceAndCityMapper.listCity("130000");
        log.info("==================list.size:{}", list.size());
        assertEquals(11, list.size());
    }

    @Test
    @Rollback
    public void testListArea() {
        List<Map<String, String>> list = provinceAndCityMapper.listArea("110100");
        log.info("==========list.size:{}", list.size());
        assertEquals(16, list.size());
    }

    @Test
    @Rollback
    public void testGetProvinceName() {
        String provinceName = provinceAndCityMapper.getProvinceName(370000);
        log.info("==================provinceName:{}", provinceName);
        assertEquals("山东省", provinceName);
    }

    @Test
    @Rollback
    public void testGetCityName() {
        String cityName = provinceAndCityMapper.getCityName(130900);
        log.info("==================provinceName:{}", cityName);
        assertEquals("沧州市", cityName);
    }

    @Test
    @Rollback
    public void testGetAreaName() {
        String areaName = provinceAndCityMapper.getAreaName(110101);
        log.info("==================provinceName:{}", areaName);
        assertEquals("东城区", areaName);
    }
}
