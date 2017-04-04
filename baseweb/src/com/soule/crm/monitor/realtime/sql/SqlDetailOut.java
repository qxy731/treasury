package com.soule.crm.monitor.realtime.sql;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数Sql监控:Sql监控明细
 */
public class SqlDetailOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private SqlDetailPo detail;


    public SqlDetailPo getDetail() {
        return detail;
    }

    public void setDetail(SqlDetailPo output) {
        this.detail = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}