package com.dayuanit.emall.enums;

import com.dayuanit.emall.exception.EmallException;

public enum OrderStatusEnum {

    WAIT_SETTLEMENT(0, "待结算"), WAIT_PAY(1, "待付款"), PAID(2, "已支付"), OUT_OF_VALUE(3, "失效");

    private int k;
    private String v;

    private OrderStatusEnum(int k, String v) {
        this.k = k;
        this.v = v;
    }

    public static OrderStatusEnum getEnum(int key) {
        for (OrderStatusEnum ose : OrderStatusEnum.values()) {
            if (key == ose.getK()) {
                return ose;
            }
        }

        throw new EmallException("没有对应的订单状态");
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
