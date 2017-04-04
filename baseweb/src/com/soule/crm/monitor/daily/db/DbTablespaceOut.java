package com.soule.crm.monitor.daily.db;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数数据库监控:表空间信息
 */
public class DbTablespaceOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<DbTsPo> ts;


    public List<DbTsPo> getTs() {
        return ts;
    }

    public void setTs(List<DbTsPo> output) {
        this.ts = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}