package com.soule.base.media;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 持久层操作的接口 完成增删、条件查询、批量更新等操作, 以及面对高级操作的支持如获取<code>java.sql.Connection</code>,
 * <code>HibernateTemplate</code>,<code>JdbcTemplate</code>,<code>SqlMapClientTempalte</code>
 * <p/>
 */
public interface IMediaForIbatis {

    /**
     * 提供获取数据库连接的接口，以便更高灵活性的操作需求 该Connection将从注入的DataSource中获取
     * 
     * @return SQL Connection
     * @throws DbAccessException
     */
    public Connection getConnection() throws DbAccessException;

    /**
     * 插入数据，采用iBatis完成<br>
     * <p/>
     * 使用方法：<br>
     * 
     * <pre>
     * 		1:假设iBatis配置文件中的操作语句为：&lt;br&gt;
     * 		&lt;sqlMap namespace=&quot;Account&quot;&gt;		&lt;br&gt;
     * 			......&lt;br&gt;
     * 			&lt;insert id=&quot;insertAccount&quot; parameterMap=&quot;insertParam&quot;&gt;&lt;br&gt;
     * 				insert into ACCOUNT ( accountky, accountno,CREATEDTIME) values ( ?,?,?)&lt;br&gt;
     * 			&lt;/insert&gt;&lt;br&gt;
     * 			......&lt;br&gt;
     * 		&lt;/sqlMap &gt;&lt;br&gt;
     * 		则可见参数params将以parameterMap的形式传递&lt;br&gt;
     * 		2：调用save_iBatis方法&lt;br&gt;
     * 		String sqlmap_name=&quot;Account.insertAccount&quot;;&lt;br&gt;
     * 		AccountVO accountVO=new AccountVO();&lt;br&gt;
     * 		......&lt;br&gt;
     * 		defautMediator.save_iBatis(sqlmap_name, new Object[]{accountVO});&lt;br&gt;
     * </pre>
     * 
     * @param queryID
     *            在iBatis映射文件中配置的sql语句编号<br>
     * @param params
     *            参数列表，可为null
     * @return 操作所影响的记录数
     * @throws DbAccessException
     */
    public int save(final String queryID, final Object params) throws DbAccessException;

    /**
     * 删除操作，采用iBatis完成<br>
     * 
     * @param queryID
     *            在iBatis映射文件中配置的sql语句编号
     * @param params
     *            参数列表,可为null
     * @return 操作所影响的记录数
     * @throws DbAccessException
     */
    public int delete(final String queryID, Object param) throws DbAccessException;
 

        
    /**
     * 更新操作，采用iBatis完成<br>
     * 调用iBatis的update方法实现<br>
     * <p/>
     * <p/>
     * 使用方法<br>
     * 
     * <pre>
     * 		1:iBatis配置文件中
     * 		>update id=&quot;updateAccount&quot; parameterClass=&quot;java.util.Map&quot;>&lt;br&gt;
     * 			update ACCOUNT set accountno = #accountNo# where accountky =#objectId#&lt;br&gt;
     * 		&lt;/update&gt; &lt;br&gt;
     * 		可见参数是以自定义的Account的parameterClass形式进行传递的&lt;br&gt;
     * 		2：调用方法：&lt;br&gt;
     * 		String ibaits_update_query=&quot;Account.updateAccount&quot;;&lt;br&gt;
     * 		Map map = new HashMap();
     * 		map.put("menuId", menuId);
     * 		map.put("nodeId", nodeId);       
     * 		defautMediator.update_iBatis (ibaits_update_query,map);		&lt;br&gt;
     * </pre>
     * 
     * @param queryID
     *            在iBatis映射文件中配置的sql语句编号<br>
     * @param params
     *            参数列表,可为null
     * @return 操作所影响的记录数
     * @throws DbAccessException
     */
    public int update(final String queryID, final Object param) throws DbAccessException;

    
    /**
     * 根据Map查找对象，采用iBatis方式完成<br>
     * 调用iBatis的queryForObject方法实现<br>
     * 适用于根据复合主键查找对象或非主键组合属性查找某个对象
     * <p/>
     * 使用方法1<br>
     * iBatis的resultClass机制，在本例中，返回列的名称必须在resultClass中域的范围内<br>
     * 
     * <pre>
     * 		1：iBatis配置文件中&lt;br&gt;
     *  	&lt;select id=&quot;selectAccountById&quot; parameterClass=&quot;Long&quot; resultClass=&quot;Account&quot;&gt;&lt;br&gt;
     * 			select accountky as objectid, accountno as accountno from ACCOUNT where accountky = #objectId#&lt;br&gt;
     * 		&lt;/select&gt;&lt;br&gt;
     * 		2：调用方法&lt;br&gt;
     * 		String ibatis_findid_query=&quot;Account.selectAccountById&quot;;&lt;br&gt;
     * 		Map paramMap  = new HashMap();
     * 		paramMap.put("menuId","10000");
     * 		paramMap.put("nodeId","20000");
     * 		AccountVO vo=(AccountVO)defautMediator.findByID_iBatis (ibatis_findid_query, paramMap);&lt;br&gt;
     * </pre>
     * <p/>
     * 使用方法2<br>
     * iBatis的resultMap机制，在本例中，返回列的名称通过resultMap进行转换，以此和Class域匹配<br>
     * 
     * <pre>
     * 		1:iBatis的配置文件中&lt;br&gt;
     * 			&lt;resultMap id=&quot;AccountResult&quot; class=&quot;Account&quot;&gt;&lt;br&gt;
     * 				&lt;result property=&quot;objectId&quot; column=&quot;accountky&quot; /&gt;&lt;br&gt;
     * 				&lt;result property=&quot;accountNo&quot; column=&quot;accountNo&quot; /&gt;&lt;br&gt;
     * 				&lt;result property=&quot;createdTime&quot; column=&quot;CREATEDTIME&quot; /&gt;&lt;br&gt;
     * 			&lt;/resultMap&gt;&lt;br&gt;
     * 			......&lt;br&gt;
     * 			&lt;select id=&quot;selectAccountWithMap&quot; parameterClass=&quot;Long&quot;  resultMap=&quot;AccountResult&quot;&gt;&lt;br&gt;
     * 				select accountky  , accountno ,createdtime  from  ACCOUNT where accountky != #objectId#&lt;br&gt;
     * 			&lt;/select&gt;&lt;br&gt;
     * 		2:调用方法&lt;br&gt;
     * 		String ibatis_findid_query2=&quot;Account.selectAccountByIdWithMap&quot;;&lt;br&gt;
     * 		Map paramMap  = new HashMap();
     * 		paramMap.put("menuId","10000");
     * 		paramMap.put("nodeId","20000");
     * 		AccountVO vo=(AccountVO)defautMediator.findByID_iBatis (ibatis_findid_query2, paramMap);&lt;br&gt;
     * </pre>
     * 
     * @param queryId
     *            在iBatis映射文件中配置的sql语句编号
     * @param paramMap
     *            map对象，其中放一些键值对
     * @return 符合查询条件的某个对象
     * @throws DbAccessException
     */
    public Object findById(String queryId, Object param) throws DbAccessException;
 
    /**
     * 使用iBatis完成复杂的查询操作，支持分页<br>
     * 关于iBatis分页机制的说明<br>
     * <p/>
     * 1：数据库支持物理分页的，如MySQL,Oracle,SQL Server2005等，交给数据库本身处理
     * <p/>
     * 2：数据库不支持物理分页的，如informix,Sybase 12.5.3,SQLServer，交给JDBC驱动的ResultSet的absolute定位完成
     * <p/>
     * 3：以上都不支持的，通过ResultSet.next()方式逐条遍历
     * <p/>
     * <p/>
     * <p/>
     * 关于如何使用iBatis分页机制的操作说明<br>
     * 1：是否要用平台优化的分页功能，目前在实现类<code>DefaultMediatorImpl</code>
     * 中是规定必须要的，因此在本方法的实现中强制了setEnableCustomizedPaged(true); 2：在Spring的配置文件中，对于IMediator的实现类的配置如下
     * &lt;bean id="defaultMediator"
     * class="com.longtop.intelliplatform.base.dao.mediator.impl.DefaultMediatorImpl" <br>
     * init-method="initialize" &gt; <br>
     * &lt;!--以下配置Hibernate的连接session--&gt;<br>
     * &lt;property name="sessionFactory"&gt;<br>
     * &lt;ref bean="sessionFactory"/&gt;<br>
     * &lt;/property&gt;<br>
     * &lt;!--以下配置iBatis的连接session--&gt;<br>
     * &lt;property name="sqlMapClient"&gt;<br>
     * &lt;ref bean="sqlMapClient"/&gt;<br>
     * &lt;/property&gt;<br>
     * &lt;!--以下配置jdbc的连接datasource--&gt;<br>
     * &lt;property name="dataSource"&gt;<br>
     * &lt;ref bean="dataSource"/&gt;<br>
     * &lt;/property&gt;<br>
     * &lt;!--以下配置iBatis的分页扩展机制--&gt;<br>
     * &lt;property name="sqlExecutor"&gt;<br>
     * &lt;ref bean="sqlExecutor"/&gt;<br>
     * &lt;/property&gt;<br>
     * &lt;/bean&gt;<br>
     * 3：在persistence/ibatis.xml文件中，需要配置好各种数据库对应的分页机制实现类，同时在sqlExecutor中选择当前使用的数据库分页类 &lt;bean
     * id="sqlExecutor" class="com.longtop.intelliplatform.base.dao.orm.IBatisSqlExecutor"&gt; <br>
     * &lt;property name="dialect"&gt; <br>
     * &lt;ref bean="oracleDialect" /&gt; <br>
     * &lt;/property&gt; <br>
     * &lt;/bean&gt; <br>
     * <bean id="oracleDialect"
     * class="com.longtop.intelliplatform.base.dao.orm.impl.OracleDialect"/> <br>
     * <bean id="db2Dialect" class="com.longtop.intelliplatform.base.dao.orm.impl.DB2Dialect"/> <br>
     * <bean id="mssqlDialect"
     * class="com.longtop.intelliplatform.base.dao.orm.impl.SQLServerDialect"/> <br>
     * <bean id="mssq2005lDialect"
     * class="com.longtop.intelliplatform.base.dao.orm.impl.SQLServer2005Dialect"/> <br>
     * <bean id="sybaseDialect"
     * class="com.longtop.intelliplatform.base.dao.orm.impl.SybaseDialect"/> <br>
     * <bean id="informixDialect"
     * class="com.longtop.intelliplatform.base.dao.orm.impl.InformixDialect"/> <br>
     * <p/>
     * </p>
     * <p/>
     * 使用方法
     * 
     * <pre>
     * 		1：iBatis的配置文件中&lt;br&gt;
     * 			&lt;select id=&quot;selectAllAccounts&quot; resultMap=&quot;AccountResult&quot;&gt;&lt;br&gt;
     * 				select * from ACCOUNT&lt;br&gt;
     * 			&lt;/select&gt;&lt;br&gt;
     * 		2:分页调用方法&lt;br&gt;
     * 		注意从IntelliWeb传过来的OffSet的值是从0开始的&lt;br&gt;
     * 		假设为第一页查询，且pagesize=5，则对应的参数offset=0，size=5；&lt;br&gt;
     * 		String ibatis_query=&quot;Account.selectAllAccounts&quot;;&lt;br&gt;
     * 		List list=defautMediator.find_iBatis(ibatis_query, null, 0, 2); &lt;br&gt;
     * 		以此类推，如是第二页查询，则对应的参数offset=5,size=5&lt;br&gt;
     * 		3：不需分页&lt;br&gt;
     * 		List list=defautMediator.find_iBatis(ibatis_query, null, 0, -999999);&lt;br&gt;
     * &lt;p/&gt;
     * </pre>
     * 
     * @param queryID
     *            在iBatis映射文件中定义的语句编号
     * @param para
     *            参数列表，可为null
     * @param offset
     *            查询结果的起始行，从0开始。如果不需要分页，则设offset置为0，同时size设置为-999999。
     * @param size
     *            查询结果的最大行数。如果不需要分页，则size设置为-999999，同时设offset置为0
     * @return 返回值将是映射文件中的查询语句中定义的ResultMap或者ResultClass组成的List
     * @throws DbAccessException
     */
    public List find(final String queryID, final Object para, final int offset, final int size)
            throws DbAccessException;

    /**
     * 使用iBatis完成查询操作， 不支持分页<br>
     * 
     * @param queryName
     *            在iBatis映射文件中定义的语句编号
     * @param para
     *            参数列表，可为null
     * @return 返回值将是映射文件中的查询语句中定义的ResultMap或者ResultClass组成的List
     * @throws DbAccessException
     * @see com.longtop.intelliplatform.base.dao.mediator.IMediator#find(java.lang.String,
     *      java.lang.Object, int, int)
     */
    public List find(String queryName, Object para) throws DbAccessException;
 

    /**
     * 获取iBatis模板对象
     * 
     * @return SqlMapClientTemplate iBatis模板对象
     */
    public SqlMapClientTemplate getSqlMapClientTemplate() throws DbAccessException;
    
    /**
     * 获取记录总数
     * @return
     */
    public long getRecordTotal(String queryID,Object param) throws DbAccessException;
}
