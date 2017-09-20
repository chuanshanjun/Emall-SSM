package com.dayuanit.emall.service;

import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.pojo.MallType;

import java.util.List;

public interface ShowService {

    List<MallType> showType();

    List<MallGoods> showGoods(int typeId);

    MallGoods showGoodsInCart(int goodsId);
}
