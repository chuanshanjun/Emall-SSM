package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallShoppingCart;
import com.dayuanit.emall.pojo.MallShoppingCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallShoppingCartMapper {
    long countByExample(MallShoppingCartExample example);

    int deleteByExample(MallShoppingCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallShoppingCart record);

    int insertSelective(MallShoppingCart record);

    List<MallShoppingCart> selectByExample(MallShoppingCartExample example);

    MallShoppingCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallShoppingCart record, @Param("example") MallShoppingCartExample example);

    int updateByExample(@Param("record") MallShoppingCart record, @Param("example") MallShoppingCartExample example);

    int updateByPrimaryKeySelective(MallShoppingCart record);

    int updateByPrimaryKey(MallShoppingCart record);
}