package com.dayuanit.pay.service;

import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.domain.PayType;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;

@WebService
public interface PayService {

    Map<String, Object> addPayOrder(PayOrder payOrder);

    List<PayType> listPayType();

    String getPayName(int payType);

    PayOrder getPayOrder(int payId);

}
