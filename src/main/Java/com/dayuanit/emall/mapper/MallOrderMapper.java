package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderMapper {

    int createOrder(MallOrder mallOrder);

    MallOrder getOrderById(@Param("mallOrderId") Integer mallOrderId);

    int changeOrderStatus(@Param("status")Integer status,
                          @Param("userId") Integer userId, @Param("mallOrderId") Integer mallOrderId);

    int updateOrderInfo(MallOrder mallOrder);

    List<MallOrder> listOrderByUserIdAndStatus(@Param("userId") Integer userId, @Param("status") Integer status,
                                               @Param("offset") Integer offset, @Param("pageNum") Integer pageNum);

    int countListOrderByUserIdAndStatus(@Param("userId") Integer userId, @Param("status") Integer status);

    MallOrder getOrderById4Lock(@Param("mallOrderId") Integer mallOrderId);

    List<MallOrder> listExpiredOrder();

    List<MallOrder> listOrderByStatus(@Param("status") Integer status,
                                               @Param("offset") Integer offset, @Param("pageNum") Integer pageNum);
}
