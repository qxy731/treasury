package com.soule.app.sys.batch;

import java.io.Serializable;

/**
 * 对应表BAT_PARAMS的类
 */
public class BatParamsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 批处理编号
     */
    private String batchId;
    /**
     * 参数编号
     */
    private String paraId;
    /**
     * 参数名称
     */
    private String paraName;
    /**
     * 参数值
     */
    private String paraValue;
    /**
     * 参数描述
     */
    private String remark;

    /**
     * @return 批处理编号
     */
    public String getBatchId () {
        return batchId;
    }

    /**
     * @param batchId 批处理编号
     */
    public void setBatchId (String batchId) {
        this.batchId = batchId;
    }
    /**
     * @return 参数编号
     */
    public String getParaId () {
        return paraId;
    }

    /**
     * @param paraId 参数编号
     */
    public void setParaId (String paraId) {
        this.paraId = paraId;
    }
    /**
     * @return 参数名称
     */
    public String getParaName () {
        return paraName;
    }

    /**
     * @param paraName 参数名称
     */
    public void setParaName (String paraName) {
        this.paraName = paraName;
    }
    /**
     * @return 参数值
     */
    public String getParaValue () {
        return paraValue;
    }

    /**
     * @param paraValue 参数值
     */
    public void setParaValue (String paraValue) {
        this.paraValue = paraValue;
    }
    /**
     * @return 参数描述
     */
    public String getRemark () {
        return remark;
    }

    /**
     * @param remark 参数描述
     */
    public void setRemark (String remark) {
        this.remark = remark;
    }

}