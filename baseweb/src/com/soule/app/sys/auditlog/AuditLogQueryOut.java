package com.soule.app.sys.auditlog;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数审计日志:查询
 */
public class AuditLogQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<AuditLogLogPo> log;


    public List<AuditLogLogPo> getLog() {
        return log;
    }

    public void setLog(List<AuditLogLogPo> output) {
        this.log = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}