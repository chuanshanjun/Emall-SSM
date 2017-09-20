package com.dayuanit.emall.enums;

import com.dayuanit.emall.exception.EmallException;

public enum MallAddressStatus {

    ENABLE(1, "可用"), DISABLE(2, "不可用");

    private int code;

    private String v;

    private MallAddressStatus(int code, String v) {
        this.code = code;
        this.v = v;
    }

    public static MallAddressStatus getEnum(int code) {
        for (MallAddressStatus mas : MallAddressStatus.values()) {
            if (code == mas.getCode()) {
                return mas;
            }
        }

        throw new EmallException("没有合适的个人状态");
    }

    public int getCode() {
        return code;
    }

    public String getV() {
        return v;
    }

}
