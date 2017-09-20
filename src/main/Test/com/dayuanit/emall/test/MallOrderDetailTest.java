package com.dayuanit.emall.test;

import com.dayuanit.emall.mapper.MallOrderDetailMapper;
import com.dayuanit.emall.pojo.MallOrderDetail;
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
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class MallOrderDetailTest {

    @Autowired
    private MallOrderDetailMapper mallOrderDetailMapper;

    private MallOrderDetail mallOrderDetail;

    @Before
    public void init() {
        mallOrderDetail = new MallOrderDetail();
        mallOrderDetail.setOrderId(12);
        mallOrderDetail.setGoodId(13);
        mallOrderDetail.setCounts(3);
        mallOrderDetail.setUnitPrice("500");
        mallOrderDetail.setAmount("1500");
        mallOrderDetail.setGoodName("mi");
    }

    @Test
    @Rollback
    public void testSave() {
        int rows = mallOrderDetailMapper.save(mallOrderDetail);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testListMallOrderDetail() {
        List<MallOrderDetail> list = mallOrderDetailMapper.listMallOrderDetail(20);
        assertEquals(2, list.size());
    }

}
