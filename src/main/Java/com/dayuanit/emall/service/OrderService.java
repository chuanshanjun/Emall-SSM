package com.dayuanit.emall.service;

import com.dayuanit.emall.dto.GoodsOrderInfoDTO;
import com.dayuanit.emall.dto.MyOrderDTO;
import com.dayuanit.emall.pojo.MallOrder;
import com.dayuanit.emall.service.impl.OrderServiceImpl;
import com.dayuanit.emall.util.PageUtils;
import com.dayuanit.emall.vo.CartVO;
import com.dayuanit.pay.dto.PayOrderDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderServiceImpl.BuyGoodDetail> createOrderFromCart(String buyMsg, int userId);

    MallOrder createOrderFromCart(List<CartVO> vos, int userId, int orderFrom);

    GoodsOrderInfoDTO loadOrderInfo(int mallOrderId, int userId);

    Map<String, Object> pay(int mallOrderId, int checkedAddressId, int checkedPayChannel, int userId);

    PageUtils<MyOrderDTO> listEffectiveOrder(int currentPage, Integer userId, Integer status);

    void processPayResult(int orderId, String payId);

    PayOrderDTO payByDTO(int mallOrderId, int checkedAddressId, int checkedPayChannel, int userId);

    Map<String, Object> payFromOrder(int mallOrderId, int userId);

    void processExpiredOrder(MallOrder mallOrder);
}
