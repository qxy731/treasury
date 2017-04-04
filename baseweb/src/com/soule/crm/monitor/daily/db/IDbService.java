package com.soule.crm.monitor.daily.db;

import com.soule.base.service.ServiceException;

/**
 * 数据库监控业务操作
 */
public interface IDbService {

    /**
     * 数据库连接状态查询
     */
    public DbInfoOut info(DbInfoIn in) throws ServiceException;
    /**
     * 表空间信息查询
     */
    public DbTablespaceOut tablespace(DbTablespaceIn in) throws ServiceException;
    /**
     * 表占用空间信息查询
     */
    public DbTableOut table(DbTableIn in) throws ServiceException;


}
