package com.soule.base.media;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;

/**
 * 持久层操作的接口 完成增删、条件查询、批量更新等操作, 以及面对高级操作的支持如获取<code>java.sql.Connection</code>,
 * <code>HibernateTemplate</code>,<code>JdbcTemplate</code>,<code>SqlMapClientTempalte</code>
 * <p/>
 */
public interface IMediaForJdbc {

    /**
     * 提供获取数据库连接的接口，以便更高灵活性的操作需求 该Connection将从注入的DataSource中获取
     * 
     * @return SQL Connection
     * @throws MediatorException
     */
    public Connection getConnection() throws DbAccessException;
 
    /**
     * 插入数据，采用JDBC完成<br>
     * 使用方法<br>
     * 
     * <pre>
     * 		String jdbc_query=&quot;insert into account(accountky,accountno,createdtime) values(555555,'123','&quot;+某个变量值+&quot;')&quot;;&lt;br&gt;
     * 		defautMediator.save_JDBC (jdbc_query, null);&lt;br&gt;
     * </pre>
     * 
     * @param sql
     *            纯粹的sql格式的Insert语句
     * @param params
     *            参数列表，如下两种情况下可以为null<br>
     *            <p/>
     *            1：传入的SQL语句一般已经完成了拼装<br>
     *            <p/>
     *            2：传入的SQL语句没有参数<br>
     * @return 操作所影响的记录数
     * @throws MediatorException
     */
    public int save(final String sql, final Object[] params) throws DbAccessException;

 
      /**
     * 删除操作，采用JDBC完成<br>
     * <p/>
     * 使用方法<br>
     * 
     * <pre>
     * 		String jdcb_delete_sql=&quot;delete from account where accountky=555555&quot;;&lt;br&gt;
     * 		defautMediator.delete_JDBC (jdcb_delete_sql,null);&lt;br&gt;
     * </pre>
     * 
     * @param sql
     *            纯粹的sql格式delete语句
     * @param params
     *            参数列表，如下两种情况下可以为null<br>
     *            <p/>
     *            1：传入的SQL语句一般已经完成了拼装<br>
     *            <p/>
     *            2：传入的SQL语句没有参数<br>
     * @return 操作所影响的记录数
     * @throws MediatorException
     */
    public int delete(final String sql, final Object[] params) throws DbAccessException;

  
     
    /**
     * 更新操作，采用JDBC完成<br>
     * <p/>
     * 使用方法
     * 
     * <pre>
     * 		String jdbc_update_sql=&quot;update ACCOUNT set accountno =090 where accountky =555555&quot;;&lt;br&gt;
     * 		defautMediator.update_JDBC (jdbc_update_sql,null);&lt;br&gt;
     * </pre>
     * 
     * @param sql
     *            纯粹的sql格式的update语句<br>
     * @param params
     *            参数列表，鉴于如下两种情况，用jdbc的query是个完整的sql语句，params不起作用，可以为null<br>
     *            <p/>
     *            1：传入的SQL语句一般已经完成了拼装<br>
     *            <p/>
     *            2：传入的SQL语句没有参数<br>
     * @return 操作所影响的记录数
     * @throws MediatorException
     */
    public int update(final String sql, final Object[] params) throws DbAccessException;
 
  
 
    /**
     * 获取JdbcTemplate模板对象
     * 
     * @return JdbcTemplate jdbc模板对象
     */
    public JdbcTemplate getJdbcTemplate() throws DbAccessException;
 
}
