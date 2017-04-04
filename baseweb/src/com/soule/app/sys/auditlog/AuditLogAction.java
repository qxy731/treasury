package com.soule.app.sys.auditlog;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 审计日志维护模块表现层处理类
 */
@Namespace("/sys")
public class AuditLogAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    //private final static Log logger = LogFactory.getLog(AuditLogAction.class);
    @Autowired
    IAuditLogService service;

    /**
     * 查询 输入参数 
     */
    private AuditLogQueryIn queryIn; //= new AuditLogQueryIn();

    public void doInit() {
    }
    public String query() {
        AuditLogQueryIn in = queryIn;
        try {
            in.getInputHead().setPageNo(this.page);
            in.getInputHead().setPageSize(this.pagesize);
            AuditLogQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            this.rows = result.getLog();
            this.setTotal(head.getTotal());
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 查询
     */
    @JSON(serialize=false)
    public AuditLogQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询
     */
    public void setQueryIn(AuditLogQueryIn in) {
        this.queryIn = in;
    }

}
