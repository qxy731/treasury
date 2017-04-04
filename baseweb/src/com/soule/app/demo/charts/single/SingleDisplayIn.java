package com.soule.app.demo.charts.single;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数基本图形:显示图形
 */
public class SingleDisplayIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String custNo;
    private String chartType;


    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo= custNo;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType= chartType;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}