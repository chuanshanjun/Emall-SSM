package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallGoodsMapper;
import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;

@Service
public class GoodSeviceImpl implements GoodService{

    private static final Logger log = LoggerFactory.getLogger(GoodSeviceImpl.class);

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    /**
     * 通过商品ID获取商品对象
     */
    @Override
    public MallGoods getGoodsById(int goodId) {
        MallGoods mallGoods = mallGoodsMapper.getGoodById(goodId);
        if (null == mallGoods) {
            throw new EmallException("物品已经下架");
        }

        return mallGoods;
    }

    /**
     * 减库存的时候一定要开启事物配合悲观锁，打开锁之后还要再次验证库存数目，防止高并发的情况
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void subGoodsNum(int goodId, int count) {
        MallGoods mallGoods = mallGoodsMapper.getGoodById4Update(goodId);
        if (count > mallGoods.getInStock()) {
            throw new EmallException("库存数量不足");
        }

        log.info(">>>删除库存的信息: goodId={},goodnum={}", goodId, count);
        int rows = mallGoodsMapper.subGoodsNum(goodId, -count);
        if (1 != rows) {
            throw new EmallException("减库存失败");
        }
    }
}
