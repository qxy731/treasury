package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

public class QtyDefQueryIn implements Serializable {
    private static final long serialVersionUID = -5354161641360314311L;
    private ServiceInput inputHead = new ServiceInput();
    // 指标代码
    private String tarCode;
    // 指标名称
    private String tarName;
    // 创建机构
    private String createOrg;
    // 指标分类编码
    private String tarSortCode;
    // 适用对象
    private String tarScope;

    private String tarType;

    private String storeDate;

    public String getTarCode() {
        return tarCode;
    }

    public void setTarCode(String tarCode) {
        this.tarCode = tarCode;
    }

    public String getTarName() {
        return tarName;
    }

    public void setTarName(String tarName) {
        this.tarName = tarName;
    }

    public String getCreateOrg() {
        return createOrg;
    }

    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg;
    }

    public String getTarSortCode() {
        return tarSortCode;
    }

    public void setTarSortCode(String tarSortCode) {
        this.tarSortCode = tarSortCode;
    }

    public String getTarScope() {
        return tarScope;
    }

    public void setTarScope(String tarScope) {
        this.tarScope = tarScope;
    }

    public String getTarType() {
        return tarType;
    }

    public void setTarType(String tarType) {
        this.tarType = tarType;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

    public String getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
    }
}