package com.dayuanit.emall.enums;

public enum GoodStatusEnum {

    SHELVE(1, "上架"), OFF_SHELVE(2, "下架");

    private int k;
    private String v;

    private GoodStatusEnum(int k, String v) {
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
