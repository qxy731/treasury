package com.soule.crm.monitor.daily.db;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数数据库监控:表信息
 */
public class DbTableIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String tsId;


    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId= tsId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}