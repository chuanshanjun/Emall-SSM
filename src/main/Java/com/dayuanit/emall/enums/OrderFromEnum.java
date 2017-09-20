package com.dayuanit.emall.enums;

public enum OrderFromEnum {

    NOW_PAT(1, "立即支付"), CART(2, "购物车");

    private int k;
    private String v;

    private OrderFromEnum(int k, String v) {
        this.k = k;
        this.v = v;
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
