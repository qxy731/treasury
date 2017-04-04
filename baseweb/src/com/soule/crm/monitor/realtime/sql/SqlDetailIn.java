package com.soule.crm.monitor.realtime.sql;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数Sql监控:Sql监控明细
 */
public class SqlDetailIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String sqlId;


    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId= sqlId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}