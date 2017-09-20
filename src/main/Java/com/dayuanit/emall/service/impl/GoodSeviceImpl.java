package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallGoodsMapper;
import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodSeviceImpl implements GoodService{

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
}
