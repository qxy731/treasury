package com.soule.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.soule.crm.license.LicenseMgr;
import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.media.IMediaForIbatis;
import com.soule.base.media.IMediaForJdbc;
import com.soule.comm.tools.AppUtils;

@Aspect
public class DefaultService implements IDefaultService {
    private static final Log logger = LogFactory.getLog(DefaultService.class);
    private IMediaForIbatis ibatisMediator;
    private IMediaForJdbc jdbcMediator;
    private String TABLE_NAMESPACE = "single.";
    private String SAVE = TABLE_NAMESPACE + "save";
    private String UPDATE = TABLE_NAMESPACE + "upd";
    private String DELETE = TABLE_NAMESPACE + "del";
    private String GET = TABLE_NAMESPACE + "get";

    public IMediaForIbatis getIbatisMediator() {
        return ibatisMediator;
    }

    public void setIbatisMediator(IMediaForIbatis ibatisMediator) {
        this.ibatisMediator = ibatisMediator;
    }

    public IMediaForJdbc getJdbcMediator() {
        return jdbcMediator;
    }

    public void setJdbcMediator(IMediaForJdbc jdbcMediator) {
        this.jdbcMediator = jdbcMediator;
    }

    public final Object getRecordByKey(String tableName, Serializable pk) {
        try {
            String name = new StringBuilder(GET).append(tableName).append("ByKey").toString();
            return this.ibatisMediator.findById(name, pk);
        } catch (DbAccessException e) {
            logger.debug("", e);
        }
        return null;
    }

    public final Boolean saveRecord(String tableName, Serializable record) {
        try {
            this.ibatisMediator.save(SAVE + tableName, record);
            return Boolean.TRUE;
        } catch (DbAccessException e) {
            logger.debug("", e);
        }
        return Boolean.FALSE;
    }

    public Boolean updateRecord(String tableName, Serializable record) {
        try {
            this.ibatisMediator.update(UPDATE + tableName, record );
            return Boolean.TRUE;
        } catch (DbAccessException e) {
            logger.debug("", e);
        }
        return Boolean.FALSE;
    }

    public final Boolean deleteReocrd(String tableName, Serializable pk) {
        try {
            this.ibatisMediator.delete(DELETE + tableName, pk );
            return Boolean.TRUE;
        } catch (DbAccessException e) {
            logger.debug("", e);
        }
        return Boolean.FALSE;
    }

    public List getRecordByMap(String tableName, Map condition) {
        try {
            String name = new StringBuilder(GET).append(tableName).toString();
            return this.ibatisMediator.find(name, condition);
        } catch (DbAccessException e) {
            logger.debug("", e);
        }
        return null;
    }

    //@Before("execution(* com.soule..query*(..)) || execution(* com.soule..insert*(..))")
    public void check(JoinPoint jp) throws ServiceException{
        try {
        	Object[] args = jp.getArgs();
            if ( !LicenseMgr.getInstance().isValid()) {
                logger.info(LicenseMgr.getInstance().getErrorInfo());
                throw new ServiceException(MsgConstants.E0016,AppUtils.getMessage(MsgConstants.E0016));
            }
        } catch (ServiceException e1) {
            logger.error("" + e1.getErrorMsg());
            throw e1;
        } catch (Exception e) {
            logger.error("" +e.getMessage());
            throw new ServiceException(MsgConstants.E0016,e.getMessage());
        }
    }

    /**
     * 所有服务实现类方法运行前
     * @throws ServiceException
     */
    //@Before("execution(* com.soule..*ServiceImpl.*(..))")
    public void _copyInParams() throws ServiceException{
        try {
            if ( !LicenseMgr.getInstance().isValid()) {
                logger.info(LicenseMgr.getInstance().getErrorInfo());
                throw new ServiceException(MsgConstants.E0016,AppUtils.getMessage(MsgConstants.E0016));
            }
        } catch (ServiceException e1) {
            logger.error("" + e1.getErrorMsg());
            throw e1;
        } catch (Exception e) {
            logger.error("" +e.getMessage());
            throw new ServiceException(MsgConstants.E0016,e.getMessage());
        }
    }
    /**
     * 所有服务实现类方法运行后
     * @throws ServiceException
     */
    //@After("execution(* com.soule..*ServiceImpl.*(..))")
    public void _copyOutParams() throws ServiceException{
        try {
            if ( !LicenseMgr.getInstance().isValid()) {
                logger.info(LicenseMgr.getInstance().getErrorInfo());
                throw new ServiceException(MsgConstants.E0016,AppUtils.getMessage(MsgConstants.E0016));
            }
        } catch (ServiceException e1) {
            logger.error("" + e1.getErrorMsg());
            throw e1;
        } catch (Exception e) {
            logger.error("" +e.getMessage());
            throw new ServiceException(MsgConstants.E0016,e.getMessage());
        }
    }
}
