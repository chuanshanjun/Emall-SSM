package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.pojo.MallType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallGoodsMapper {

    int addGoods(MallGoods mallGoods);

    List listGoods(@Param("typeId") Integer typeId);

    MallGoods getGoodById(@Param("goodId") Integer goodId);

    int subGoodsNum(@Param("goodId") Integer goodId, @Param("counts") Integer counts);

    MallGoods getGoodById4Update(@Param("goodId") Integer goodId);
}
