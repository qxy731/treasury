package com.soule.crm.monitor.daily.db;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数数据库监控:表信息
 */
public class DbTableOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<DbTablePo> table;


    public List<DbTablePo> getTable() {
        return table;
    }

    public void setTable(List<DbTablePo> output) {
        this.table = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}