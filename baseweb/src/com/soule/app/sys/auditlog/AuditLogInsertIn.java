package com.soule.app.sys.auditlog;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数审计日志:新增日志
 */
public class AuditLogInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private AuditLogLogPo log;

    public AuditLogLogPo getLog()  {
        return log;
    }

    public void setLog(AuditLogLogPo input) {
        this.log = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}