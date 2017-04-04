package com.soule.base.media.impl;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.soule.base.media.DbAccessException;
import com.soule.base.media.IMediaForJdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * mediator实现，绑定 JDBC 数据访问技术
 */
public class DefaultMediaForJdbc extends AbstractMediator implements IMediaForJdbc {
	protected final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 以私有成员变量保存Jdbc模板JdbcTemplate
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取JdbcTemplate模板对象
	 *
	 * @return JdbcTemplate jdbc模板对象
	 */
	public JdbcTemplate getJdbcTemplate() throws DbAccessException
	{
		// 首先检查原来的JdbcTemplate实例是否存在
		if (this.jdbcTemplate == null) {
			// 如果不存在，则创建一个HibernateTemplate实例
			this.jdbcTemplate = new JdbcTemplate(this.dataSource);
		}
		return this.jdbcTemplate;
	}
	/*
		 * (non-Javadoc)
		 * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#getConnection()
		 */
	public Connection getConnection() throws DbAccessException
	{
		Connection rs = null;
		try {
			rs = dataSource.getConnection();
		} catch (SQLException se) {
			throw new DbAccessException(se);
		}
		return rs;
	}

	// ==============================iBatis分页干预机制实现===================================
	private SqlExecutor sqlExecutor;

	public SqlExecutor getSqlExecutor()
	{
		return sqlExecutor;
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor)
	{
		this.sqlExecutor = sqlExecutor;
	}

	/*
	 * (non-Javadoc)
	 * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#delete_JDBC(java.lang.String, java.lang.Object[])
	 */
	public int delete(String sql, Object[] params) throws DbAccessException
	{
		//用jdbc完成,此时的query是个完整的sql语句，params不起作用
		{
			int rs = 0;
			getJdbcTemplate().execute(sql);
			rs = 1;
			return rs;
		}
	}
 
	/*
	 * (non-Javadoc)
	 * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#findAll_JDBC(java.lang.String)
	 */
	public List findAll(String sql) throws DbAccessException
	{
		//用jdbc的queryForList完成,返回值是由Map组成的List，Map中每个key就是返回值中的列名
		//如query="SELECT NAME,SEX,AGE FROM EMPLOYEE",则每个item就是map，而map.get(NAME)就能却道name独赢的值。
		return getJdbcTemplate().queryForList(sql);
	}

	/*
	 * (non-Javadoc)
	 * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#findByID_Hibernate_ComposedHQL(java.lang.String, java.io.Serializable)
	 */
 
	/*
	 * (non-Javadoc)
	 * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#save_JDBC(java.lang.String, java.lang.Object[])
	 */
	public int save(String sql, Object[] params) throws DbAccessException
	{
		//用jdbc完成,此时的query是个完整的sql语句，params不起作用
		{
			int rs = 0;
			getJdbcTemplate().execute(sql);
			rs = 1;
			return rs;
		}
	}
  
	/*
	 * (non-Javadoc)
	 * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#update_JDBC(java.lang.String, java.lang.Object[])
	 */
	public int update(String sql, Object[] params) throws DbAccessException
	{
		//用jdbc完成,此时的query是个完整的sql语句，params不起作用
		{
			int rs = 0;
			getJdbcTemplate().execute(sql);
			rs = 1;
			return rs;
		}
	}


//	====================================遗留接口实现,可逐步被Deprecated=================================


}
