package com.dayuanit.emall.test;

import static org.junit.Assert.assertEquals;

import com.dayuanit.emall.mapper.MallUserMapper;
import com.dayuanit.emall.pojo.MallUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MallUserMapperTest {

    private MallUser mallUser;

    @Autowired
    private MallUserMapper mallUserMapper;

    @Before
    public void init() {
        mallUser = new MallUser();
        mallUser.setUsername("young");
        mallUser.setPassword("111111");
        mallUser.setBirthday("1990-10-10");
        mallUser.setEmail("xxx@126.com");
        mallUser.setSex((byte)0);
        mallUser.setCellphone(666666);
        mallUser.setStatus((byte)1);
    }

    @Test
    @Rollback
    public void testAddMallUser() {
        int rows = mallUserMapper.addMallUser(mallUser);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testGetMallUserByUserName() {
        mallUserMapper.addMallUser(mallUser);
        MallUser mallUser = mallUserMapper.getMallUserByUserName("young");
        System.out.println(mallUser.getUsername());
    }
}
