/*购物车功能接口*/

package com.dayuanit.emall.service;

import com.dayuanit.emall.pojo.MallShoppingCart;

import java.util.List;

public interface CartService {

    void addCart(int userId, int goodId, int counts);

    List<MallShoppingCart> listCart(int userId);

    void deleteShoppingCartGoodsId(int shoppingCartGoodId);

    MallShoppingCart getCartShoppingId(int mallShoppingId);

}
