package com.dayuanit.emall.test;

import com.dayuanit.emall.mapper.MallShoppingCartMapper;
import com.dayuanit.emall.pojo.MallShoppingCart;
import com.dayuanit.emall.service.CartService;
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
public class MallShoppingCartTest {

    private MallShoppingCart mallShoppingCart;

    @Autowired
    private MallShoppingCartMapper mallShoppingCartMapper;

    @Before
    public void init() {
        mallShoppingCart = new MallShoppingCart();
        mallShoppingCart.setUserId(1);
        mallShoppingCart.setGoodsId(1);
        mallShoppingCart.setStatus((byte)1);
        mallShoppingCart.setCounts(2);
    }

    @Test
    @Rollback
    public void testAddCart() {
        int rows = mallShoppingCartMapper.addCart(mallShoppingCart);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testListCart() {
        List<MallShoppingCart> list = mallShoppingCartMapper.listCart(5);
        assertEquals(4, list.size());
    }

    @Test
    @Rollback
    public void testDeleteShoppingCartGoods() {
        int rows = mallShoppingCartMapper.deleteShoppingCartGoods(2);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testGetMallShoppingCartById() {
        MallShoppingCart mallShoppingCart = mallShoppingCartMapper.getMallShoppingCartById(16);
        int rows = mallShoppingCart.getGoodsId();
        assertEquals(15, rows);
    }
}
