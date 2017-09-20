/*购物车Mapper*/

package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallShoppingCartMapper {

    int addCart(MallShoppingCart mallShoppingCart);

    List<MallShoppingCart> listCart(@Param("userId") Integer userId);

    int deleteShoppingCartGoods(@Param("shoppingCartId") Integer shoppingCartId);

    MallShoppingCart getMallShoppingCartById(@Param("id") Integer id);
}