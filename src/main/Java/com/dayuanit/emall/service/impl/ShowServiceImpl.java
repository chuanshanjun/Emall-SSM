package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallGoodsMapper;
import com.dayuanit.emall.mapper.MallTypeMapper;
import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.pojo.MallType;
import com.dayuanit.emall.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{

    @Autowired
    private MallTypeMapper mallTypeMapper;

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    /**
     *遍历商品类目
     */
    @Override
    public List<MallType> showType() {
        List<MallType> list = mallTypeMapper.listType();
        if (null == list) {
            throw new EmallException("类目不存在");
        }

        return list;
    }

    /**
     * 遍历每个商品类目中的商品详情
     */
    @Override
    public List<MallGoods> showGoods(int typeId) {
        List<MallGoods> list = mallGoodsMapper.listGoods(typeId);
        if (null == list) {
            throw new EmallException("商品下架");
        }

        return list;
    }

    //通过货物ID来查找货物
    @Override
    public MallGoods showGoodsInCart(int goodsId) {

        MallGoods mallGoods = mallGoodsMapper.getGoodById(goodsId);

        if (null == mallGoods) {
            throw new EmallException("商品不存在");
        }

        return mallGoods;
    }

}
