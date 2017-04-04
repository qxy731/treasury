package com.soule.comm.enu;

public enum ReturnCodeType {
    SUCCESS("1"),FAILURE("0"),EXCEPTION("-1");
    private String value;
    
    private ReturnCodeType(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    
    public static ReturnCodeType getInstance(String str) {
        ReturnCodeType ret = SUCCESS;
        if (str!= null && str.length() > 0 ) {
            if ("1".equals(str)) {
                ret = SUCCESS;
            }
            if ("0".equals(str)) {
                ret = FAILURE;
            }
            if ("-1".equals(str)) {
                ret = EXCEPTION;
            }
        }
        return ret;
    }
}
