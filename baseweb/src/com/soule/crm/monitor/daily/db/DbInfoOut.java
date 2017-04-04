package com.soule.crm.monitor.daily.db;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数数据库监控:数据库状态
 */
public class DbInfoOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<DbDbPo> db;


    public List<DbDbPo> getDb() {
        return db;
    }

    public void setDb(List<DbDbPo> output) {
        this.db = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}