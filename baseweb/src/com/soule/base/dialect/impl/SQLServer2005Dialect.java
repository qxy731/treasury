package com.soule.base.dialect.impl;

import com.soule.base.dialect.IDialect;


/**
 * SQLSERVER2005的数据库方言类<BR>
 * 主要用于在iBatis分页机制中获取当前数据库自身的分页语句<BR>
 * 传入的sql语句中，要保证有order by参数，最好是id或者其它绝对不会重复的字段<BR>
 */
public class SQLServer2005Dialect implements IDialect {
	public String getPagedString(String sql, boolean hasOffset)
	{
		return null;
	}

	public String getPagedString(String sql, int offset, int limit)
	{
		if (offset == 0) { // no offset , use top , 这时不能支持limit参数绑定
			return new StringBuffer(sql.length() + 8).append(sql).insert(getSqlAfterSelectInsertPoint(sql), " top " + limit).toString();
		} else {
			int orderByIndex = sql.toLowerCase().lastIndexOf("order by");

			if (orderByIndex <= 0) {
				throw new UnsupportedOperationException("must specify 'order by' statement to support limit operation with offset in sql server 2005");
			}

			String sqlOrderBy = sql.substring(orderByIndex + 8);
			String sqlRemoveOrderBy = sql.substring(0, orderByIndex);
			int insertPoint = getSqlAfterSelectInsertPoint(sql);
			return new StringBuffer(sql.length() + 100).append("with tempPagination as(").append(sqlRemoveOrderBy).insert(insertPoint + 23,
				" ROW_NUMBER() OVER(ORDER BY " + sqlOrderBy + ") as RowNumber,").append(
				") select * from tempPagination where RowNumber between " + (offset + 1) + " and " + (offset + limit)).toString();
		}

	}

	/**
	 * 获取sql中select子句位置
	 */
	protected static int getSqlAfterSelectInsertPoint(String sql)
	{
		int selectIndex = sql.toLowerCase().indexOf("select");
		final int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
	}

	public boolean supportsPaged()
	{
		return true;
	}

	public static void main(String[] args)
	{
		SQLServer2005Dialect dialect = new SQLServer2005Dialect();
		String sql = "select table1.a,table2.b from table1,table2 where table1.d=table2.e order by c";
		String rs = dialect.getPagedString(sql, 1, 10);
		System.out.println("============" + rs);
	}
}
