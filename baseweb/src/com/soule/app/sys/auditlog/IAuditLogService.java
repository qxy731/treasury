package com.soule.app.sys.auditlog;

import com.soule.base.service.ServiceException;

/**
 * 审计日志业务操作
 */
public interface IAuditLogService {

    /**
     * 根据条件查询
     */
    public AuditLogQueryOut query(AuditLogQueryIn in) throws ServiceException;
    /**
     * 新增日志
     */
    public AuditLogInsertOut insert(AuditLogInsertIn in) throws ServiceException;


}
