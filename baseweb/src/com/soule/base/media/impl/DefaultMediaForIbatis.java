package com.soule.base.media.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.soule.base.dialect.IBatisSqlExecutor;
import com.soule.base.media.DbAccessException;
import com.soule.base.media.IMediaForIbatis;
import com.soule.comm.ParamConstants;
import com.soule.comm.tools.ReflectUtil;

/**
 * mediator实现，绑定iBatis数据访问技术
 */
public class DefaultMediaForIbatis extends AbstractMediator implements IMediaForIbatis {
    protected final Log logger = LogFactory.getLog(this.getClass());

    public DefaultMediaForIbatis() {
        super();
    }

    /**
     * 以私有成员变量保存SqlMapClient
     */
    private SqlMapClient sqlMapClient;

    /**
     * 以私有成员变量保存iBatis模板SqlMapClientTemplate
     */
    private SqlMapClientTemplate sqlMapClientTemplate;

    /**
     * 设置注入SqlMapClient必须的setter方法(系统自动调用)
     * 
     * @param sqlMapClient
     *            会话工厂，配置数据源及事务
     */
    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * 获取iBatis模板对象
     * 
     * @return SqlMapClientTemplate iBatis模板对象
     */
    public SqlMapClientTemplate getSqlMapClientTemplate() throws DbAccessException {
        // 首先检查原来的SqlMapClientTemplate实例是否存在
        if (this.sqlMapClientTemplate == null) {
            // 如果不存在，则创建一个SqlMapClientTemplate实例
            this.sqlMapClientTemplate = new SqlMapClientTemplate(this.sqlMapClient);
        }
        return this.sqlMapClientTemplate;
    }

    // ==============================iBatis分页干预机制实现===================================
    private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    /*
     * 是否使用iBatis提供的原始分页机制 供本类中的find_iBatis方法调用并赋值
     */
    public void setEnableCustomizedPaged(boolean enableCustomizedPaged) {
        if (sqlExecutor instanceof IBatisSqlExecutor) {
            ((IBatisSqlExecutor) sqlExecutor).setEnableCustomizedPaged(enableCustomizedPaged);
        }
    }

    /**
     * 其中的initialize方法执行注入，此方法在spring Beans 配置中指定为init-method。 由于sqlExecutor是
     * com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient的私有成员， 且没有公开的set方法，所以此处通过反射绕过java的访问控制
     * 
     * @throws Exception
     */
    public void initialize() throws Exception {
        if (sqlExecutor != null) {
            SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof ExtendedSqlMapClient) {
                ReflectUtil.setFieldValue(((ExtendedSqlMapClient) sqlMapClient).getDelegate(), "sqlExecutor",
                        SqlExecutor.class, sqlExecutor);
                /*SqlMapExecutorDelegate dd = ((ExtendedSqlMapClient) sqlMapClient).getDelegate();*/
            }
        }
    }

    public int delete(String queryID, Object param) throws DbAccessException {
        return getSqlMapClientTemplate().delete(queryID, param);
    }

    public List find(String queryName, Object para, int offset, int size) throws DbAccessException {
        // 希望使用平台优化过的分页机制。但启用平台对iBtatis分页的优化机制并不等于就能够用，还要看当前数据库本身是否支持。
        setEnableCustomizedPaged(true);
        return getSqlMapClientTemplate().queryForList(queryName, para, offset, size);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#find_iBatis(java.lang.String,
     * java.lang.Object)
     */
    public List find(String queryName, Object para) throws DbAccessException {
        return find(queryName, para, 0, ParamConstants.MAX_FETCH_SIZE);
    }

    public Object findById(String queryID, Object id) throws DbAccessException {
        try {
            return (getSqlMapClientTemplate().queryForObject(queryID, id));
        } catch (Throwable e) {
            throw new DbAccessException(e);
        }
    }

    public int save(String queryID, Object params) throws DbAccessException {
        int rs = 0;
        try {
            getSqlMapClientTemplate().insert(queryID, params);
            rs = 1;
        } catch (Throwable e) {
            throw new DbAccessException(e);
        }
        return rs;
    }

    public int update(String queryID, Object params) throws DbAccessException {
        try {
            return getSqlMapClientTemplate().update(queryID, params);
        } catch (Throwable e) {
            throw new DbAccessException(e);
        }
    }

    public long getRecordTotal(String queryID, Object param) throws DbAccessException {
        prepareCountQuery(queryID);
        Long l = (Long) getSqlMapClientTemplate().queryForObject(CountStatementUtil.getCountStatementId(queryID), param);
        if (l == null) {
            return -1;
        }
        return l.longValue();
    }

    protected void prepareCountQuery(String queryID) throws DbAccessException {
        String countQuery = CountStatementUtil.getCountStatementId(queryID);
        if (logger.isDebugEnabled()) {
            logger.debug("Convert " + queryID + " to " + countQuery);
        }
        SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
        if (sqlMapClient instanceof ExtendedSqlMapClient) {
            SqlMapExecutorDelegate delegate = ((ExtendedSqlMapClient) sqlMapClient).getDelegate();
            try {
                delegate.getMappedStatement(countQuery);
            } catch (SqlMapException e) {
                delegate.addMappedStatement(CountStatementUtil.createCountStatement(delegate
                        .getMappedStatement(queryID)));
            }

        }
    }

}
