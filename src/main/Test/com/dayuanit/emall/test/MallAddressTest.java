package com.dayuanit.emall.test;

import com.dayuanit.emall.mapper.MallAddressMapper;
import com.dayuanit.emall.pojo.MallAddress;
import org.junit.Before;
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

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/spring/spring-app.xml")
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class MallAddressTest {

    Logger log = LoggerFactory.getLogger(MallAddressTest.class);

    @Autowired
    private MallAddressMapper mallAddressMapper;

    private MallAddress mallAddress;

    @Before
    public void init() {
        mallAddress = new MallAddress();
        mallAddress.setAccurateAddress("星火E放");
        mallAddress.setArea("215400");
        mallAddress.setAreaWord("浦口");
        mallAddress.setCellphoneNum(333);
        mallAddress.setCity("110000");
        mallAddress.setCityWord("北京");
        mallAddress.setDefaultAddress(1);
        mallAddress.setProvince("1100");
        mallAddress.setProvinceWord("江苏");
        mallAddress.setUserId(1100);
        mallAddress.setRealName("yang");
        mallAddress.setStatus(1);
    }

    @Test
    @Rollback
    public void testAddAddress() {
        int rows = mallAddressMapper.addAddress(mallAddress);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testListAddress() {
        List<MallAddress> list = mallAddressMapper.listAddress(13);
        log.info("============list.size():{}", list.size());
        assertEquals(3, list.size());
    }

    @Test
    @Rollback
    public void deleteAddress() {
        int rows = mallAddressMapper.deleteAddress(2);
        log.info("===============rows:{}", rows);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testGetAddressById() {
        MallAddress mallAddress = mallAddressMapper.getAddressById(2);
        log.info("=========mallAddress userId:{}", mallAddress.getUserId());
        assertEquals(13, mallAddress.getUserId().intValue());
    }
}
