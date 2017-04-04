package com.soule.base.dialect.impl;

import com.soule.base.dialect.IDialect;


/**
 * SQLSERVER的数据库方言类
 */
public class SQLServerDialect implements IDialect {
	public String getPagedString(String sql, boolean hasOffset)
	{
		return null;
	}

	public String getPagedString(String sql, int offset, int limit)
	{
		if (offset > 0) {
			throw new UnsupportedOperationException("sql server has no offset");
		}
		return new StringBuffer(sql.length() + 8).append(sql).insert(getAfterSelectInsertPoint(sql), " top " + limit).toString();
	}

	static int getAfterSelectInsertPoint(String sql)
	{
		int selectIndex = sql.toLowerCase().indexOf("select");
		final int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
	}

	public boolean supportsPaged()
	{
		return false;
	}

	public static void main(String[] args)
	{
		SQLServerDialect dialect = new SQLServerDialect();
		String sql = "select a,b from table";
		String rs = dialect.getPagedString(sql, 0, 2);
		System.out.println("=============" + rs);
	}

}
