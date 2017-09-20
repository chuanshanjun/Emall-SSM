package com.dayuanit.emall.enums;

import com.dayuanit.emall.exception.EmallException;

public enum MallAddressDefault {

    DEFAULT(1, "默认"), NOT_DEFAULT(2, "非默认");

    private int code;

    private String v;

    private MallAddressDefault(int code, String v) {
        this.code = code;
        this.v = v;
    }

    public static MallAddressDefault getEnum(int code) {
        for (MallAddressDefault mad : MallAddressDefault.values()) {
            if (code == mad.getCode()) {
                return mad;
            }
        }

        throw new EmallException("没有可显示的默认地址");
    }

    public int getCode() {
        return code;
    }

    public String getV() {
        return v;
    }
}
