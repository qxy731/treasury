package com.soule.base.media.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.soule.base.media.DbAccessException;

public class AbstractMediator {
    // ===============================初始化环境设置=============================
    /**
     * 以私有成员变量保存SessionFactory
     */
	//FIXME
    //SessionFactory sessionFactory;

    /**
     * 以私有成员变量保存DataSource
     */
    DataSource dataSource;

    /**
     * 设置注入SessionFactory必须的setter方法(系统自动调用)
     * 
     * @param sessionFactory
     *            会话工厂，配置数据源及事务
     */
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    /**
     * 设置注入DataSource必须的setter方法(系统自动调用)
     * 
     * @param ds
     *            配置数据源
     */
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    public Connection getConnection() throws DbAccessException {
        Connection rs = null;
        try {
            rs = dataSource.getConnection();
        } catch (SQLException se) {
            throw new DbAccessException(se);
        }
        return rs;
    }

}
