package com.soule.app.sys.auditlog;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
/**
 * 审计日志业务操作
 */
@Service("AuditLogService")
public class AuditLogServiceImpl implements IAuditLogService {

    private final static Log logger = LogFactory.getLog(AuditLogServiceImpl.class);
    // 数据库通用操作
    @Autowired
    private IDefaultService sDefault;

    /**
     * 根据条件查询
     */
    public AuditLogQueryOut query(AuditLogQueryIn in)
        throws ServiceException {
        AuditLogQueryOut out = new AuditLogQueryOut();
        try {
            long x = sDefault.getIbatisMediator().getRecordTotal("auditlog.getSysLogAudit", in.getLog());
            int pageOffset = (in.getInputHead().getPageNo()-1)*in.getInputHead().getPageSize();
            if (pageOffset<x) {
                List ret = sDefault.getIbatisMediator().find("auditlog.getSysLogAudit", in.getLog(),pageOffset,in.getInputHead().getPageSize());
                out.setLog(ret);
                out.getResultHead().setTotal(x);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }
    /**
     * 新增日志
     */
    public AuditLogInsertOut insert(AuditLogInsertIn in)
        throws ServiceException {
        AuditLogInsertOut out = new AuditLogInsertOut();
        AuditLogLogPo log = in.getLog();
        if (log == null) {
            AppUtils.setResult(out, MsgConstants.E0001,"日志");
            return out;
        }
        try {
            log.setExecTime(new Date());
            sDefault.getIbatisMediator().save("auditlog.saveSysLogAudit", log);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB",e);
            AppUtils.setResult(out, MsgConstants.E0002,e.getMessage());
        }
        return out;
    }

}
