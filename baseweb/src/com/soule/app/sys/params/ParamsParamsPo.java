package com.soule.app.sys.params;

import java.io.Serializable;

/**
 * 参数传递查询参数配置的类
 */
public class ParamsParamsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String paraId;
    private String paraRank;
    private String paraValue;
    private String remark;

    /**
     * @return ID
     */
    public String getParaId() {
        return paraId;
    }

    /**
     * @param paraId
     *            ID
     */
    public void setParaId(String paraId) {
        this.paraId = paraId;
    }

    /**
     * @return 分级
     */
    public String getParaRank() {
        return paraRank;
    }

    /**
     * @param paraRank
     *            分级
     */
    public void setParaRank(String paraRank) {
        this.paraRank = paraRank;
    }

    /**
     * @return 值
     */
    public String getParaValue() {
        return paraValue;
    }

    /**
     * @param paraValue
     *            值
     */
    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }

    /**
     * @return 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}