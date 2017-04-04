package com.soule.app.demo.charts.single;

import java.io.Serializable;

/**
 * 参数传递显示图形的类
 */
public class SingleDataPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String custNo;
    private Double amt;
    private Integer month;

    /**
     * @return 客户号
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * @param custNo 客户号
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
    /**
     * @return 月存款额
     */
    public Double getAmt() {
        return amt;
    }

    /**
     * @param amt 月存款额
     */
    public void setAmt(Double amt) {
        this.amt = amt;
    }
    /**
     * @return 月份
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * @param month 月份
     */
    public void setMonth(Integer month) {
        this.month = month;
    }
}