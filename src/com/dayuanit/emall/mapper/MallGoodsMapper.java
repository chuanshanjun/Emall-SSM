package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.pojo.MallGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallGoodsMapper {
    long countByExample(MallGoodsExample example);

    int deleteByExample(MallGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallGoods record);

    int insertSelective(MallGoods record);

    List<MallGoods> selectByExample(MallGoodsExample example);

    MallGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallGoods record, @Param("example") MallGoodsExample example);

    int updateByExample(@Param("record") MallGoods record, @Param("example") MallGoodsExample example);

    int updateByPrimaryKeySelective(MallGoods record);

    int updateByPrimaryKey(MallGoods record);
}