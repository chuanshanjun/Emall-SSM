package com.dayuanit.emall.test;

import com.dayuanit.emall.mapper.MallTypeMapper;
import com.dayuanit.emall.pojo.MallType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MallTypeTest {

    private MallType mallType;

    @Autowired
    private MallTypeMapper mallTypeMapper;

    @Before
    public void init() {
        mallType = new MallType();
        mallType.setTypeName("日用品");
        mallType.setStatus((byte)1);
    }

    @Test
    @Rollback
    public void testAddType() {
        int rows = mallTypeMapper.addType(mallType);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testListType() {
        List listType = mallTypeMapper.listType();
        assertEquals(2, listType.size());
    }
}
