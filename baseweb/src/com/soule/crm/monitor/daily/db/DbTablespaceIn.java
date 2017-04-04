package com.soule.crm.monitor.daily.db;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数数据库监控:表空间信息
 */
public class DbTablespaceIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String dbId;


    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId= dbId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}