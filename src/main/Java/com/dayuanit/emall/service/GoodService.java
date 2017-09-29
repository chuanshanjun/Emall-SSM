package com.dayuanit.emall.service;

import com.dayuanit.emall.pojo.MallGoods;

public interface GoodService {

    MallGoods getGoodsById(int goodId);

    void subGoodsNum(int goodId, int count);

    void addGoodsNum(int goodId, int count);
}
