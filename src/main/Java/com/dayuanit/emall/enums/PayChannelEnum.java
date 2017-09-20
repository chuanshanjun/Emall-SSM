package com.dayuanit.emall.enums;

import com.dayuanit.emall.exception.EmallException;

public enum PayChannelEnum {

    OTHER(0, "其他支付方式"),CASH_ON_DELIVERY(1, "货到付款"), ALIPAY(2, "支付宝"), WECHATPAY(3, "微信支付"), SINAPAY(4, "新浪支付");

    private int payType;
    private String desc;

    private PayChannelEnum(int payType, String desc) {
        this.payType = payType;
        this.desc = desc;
    }

    public static PayChannelEnum getEnum(int payType) {
        for (PayChannelEnum pce : PayChannelEnum.values()) {
            if (payType == pce.getPayType()) {
                return pce;
            }
        }

        throw new EmallException("没有匹配的支付方式");
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
