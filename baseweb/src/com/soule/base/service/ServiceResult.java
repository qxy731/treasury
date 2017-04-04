package com.soule.base.service;

public class ServiceResult {

    private String retCode = "";
    private String retMsg = "";
    private long total = 0;

    public String getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public long getTotal() {
        return total;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public void setTotal(long t) {
        this.total = t;
    }

    public boolean isSuccess() {
        return (retCode != null && retCode.length() > 0 && retCode.charAt(0) == 'I');
    }
}
