package com.soule.comm.enu;

public enum VaildType {

    VALID("1"), INVALID("0");
    private String value;

    private VaildType(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }
    public static VaildType getInstance(String val) {
        if ("1".equals(val)){
            return VALID;
        }
        else if ("0".equals(val)) {
            return INVALID;
        }
        return null;
    }
}
