package com.soule.crm.monitor.daily.db;

import java.util.ArrayList;
import java.util.HashMap;
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
 * 数据库监控业务操作
 */
@Service
public class DbServiceImpl implements IDbService {

    private final static Log logger = LogFactory.getLog(DbServiceImpl.class);
    // 数据库通用操作
    @Autowired
    private IDefaultService sDefault;
    /**
     * 数据库连接状态查询
     */
    public DbInfoOut info(DbInfoIn in) throws ServiceException {
        DbInfoOut out = new DbInfoOut();
        DbDbPo po = new DbDbPo();
        po.setDbId("dd");
        try {
            sDefault.getIbatisMediator().getRecordTotal("role.getSysRoleCount",new HashMap());
            po.setRunFlag("true");
        } catch (DbAccessException e) {
            po.setRunFlag("false");
            logger.error("DB",e);
        }
        out.getResultHead().setTotal(1l);
        ArrayList<DbDbPo> output = new ArrayList<DbDbPo>();
        output.add(po);
        out.setDb(output);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    /**
     * 表空间信息查询
     */
    public DbTablespaceOut tablespace(DbTablespaceIn in) throws ServiceException {
        DbTablespaceOut out = new DbTablespaceOut();
        ArrayList<DbTsPo> output = new ArrayList<DbTsPo>();
        
        try {
            output = (ArrayList<DbTsPo>) sDefault.getIbatisMediator().find("monitor.tablespace", in);
        } catch (DbAccessException e) {
            logger.error("DB",e);
        }
        for (int i = 0 ; i < output.size() ; i++) {
            DbTsPo dtp = output.get(i);
            double size = Double.parseDouble( dtp.getSize() );
            double free = Double.parseDouble( dtp.getFree() );
            dtp.setUseRate( (size-free)/size );
        }
        out.setTs(output);
        out.getResultHead().setTotal(output.size());
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }
    /**
     * 表占用空间信息查询
     */
    public DbTableOut table(DbTableIn in) throws ServiceException {
        DbTableOut out = new DbTableOut();
        try {
            List output = sDefault.getIbatisMediator().find("monitor.tables", in);
            out.setTable(output);
            out.getResultHead().setTotal(output.size());
        } catch (DbAccessException e) {
            logger.error("DB",e);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

}
