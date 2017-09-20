package com.dayuanit.emall.controller;

import com.dayuanit.emall.dto.AjaxResultDTO;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.pay.domain.PayType;
import com.dayuanit.pay.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pay")
public class PayChannel extends BaseController{

    Logger log = LoggerFactory.getLogger(PayChannel.class);

    @Autowired
    private PayService payService;

    @RequestMapping("/getPayChannle")
    @ResponseBody
    public AjaxResultDTO getPayChannle() {
        List<PayType> list = null;

        try {
            list = payService.listPayType();
        } catch (EmallException ee) {
            log.error("获取支付渠道失败{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("获取支付渠道异常{}", e);
            return AjaxResultDTO.failed("系统异常请联系客服");
        }

        return AjaxResultDTO.success(list);
    }
}
