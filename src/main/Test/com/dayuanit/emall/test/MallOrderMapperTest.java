package com.dayuanit.emall.test;

import com.dayuanit.emall.mapper.MallOrderMapper;
import com.dayuanit.emall.pojo.MallOrder;
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
public class MallOrderMapperTest {

    private MallOrder mallOrder;

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Before
    public void init() {
        mallOrder = new MallOrder();
        mallOrder.setUserId(12);
        mallOrder.setOrderFrom(1);
        mallOrder.setStatus(1);
        mallOrder.setAmount("1200");
        mallOrder.setDetail("hahha");
        mallOrder.setRealName("yyy");
        mallOrder.setPhone(111);
        mallOrder.setArea("浦口");
        mallOrder.setCity("南京");
        mallOrder.setProvince("江苏");
        mallOrder.setPayChannel(1);
    }

    @Test
    @Rollback
    public void testCreateOrder() {
        int rows = mallOrderMapper.createOrder(mallOrder);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testGetOrderById() {
        MallOrder mallOrder = mallOrderMapper.getOrderById(1);
        System.out.println(mallOrder.getUserId());
    }

    @Test
    @Rollback
    public void testChangeOrderStatus() {
        int rows = mallOrderMapper.changeOrderStatus(2, 12, 3);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testUpdateOrderInfo() {
        int rows = mallOrderMapper.updateOrderInfo(mallOrder);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testListOrderByUserIdAndStatus() {
//        List<MallOrder> list = mallOrderMapper.listOrderByUserIdAndStatus(12, null);
//        System.out.println(list.size());
    }
}
