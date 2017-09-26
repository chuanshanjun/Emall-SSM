package com.dayuanit.emall.test;

import com.dayuanit.emall.mapper.MallGoodsMapper;
import com.dayuanit.emall.pojo.MallGoods;
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
public class MallGoodsMapperTest {

    private MallGoods mallGoods;

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Before
    public void init() {
        mallGoods = new MallGoods();
        mallGoods.setName("电脑");
        mallGoods.setPrice("100");
        mallGoods.setInStock(10);
        mallGoods.setGoodsDesc("帅气电脑");
        mallGoods.setStatus((byte)1);
        mallGoods.setTypeId(1);
        mallGoods.setSelledCounts(10);
        mallGoods.setPhoto("001");
    }

    @Test
    @Rollback
    public void testAddGoods() {
        int rows = mallGoodsMapper.addGoods(mallGoods);
        assertEquals(1, rows);
    }

    @Test
    @Rollback
    public void testListGoods() {
        List<MallGoods> list = mallGoodsMapper.listGoods(1);
        assertEquals(16, list.size());
    }

    @Test
    @Rollback
    public void testGetGoodById() {
        MallGoods mallGoods = mallGoodsMapper.getGoodById(1);
//        assertEquals(4768, (int)mallGoods.getPrice());
    }

    @Test
    @Rollback
    public void testSubGoodsNum() {
        int rows = mallGoodsMapper.subGoodsNum(1, 100);
        assertEquals(1, rows);
    }
}
