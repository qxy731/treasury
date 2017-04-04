package com.soule.base.dialect.impl;

import com.soule.base.dialect.IDialect;


/**
 * MySQL数据库方言
 */
public class MySQLDialect implements IDialect {
	protected static final String SQL_END_DELIMITER = ";";

	public String getPagedString(String sql, boolean hasOffset)
	{
		return new StringBuffer(sql.length() + 20).append(trim(sql)).append(hasOffset ? " limit ?,?" : " limit ?").append(SQL_END_DELIMITER)
			.toString();
	}

	public String getPagedString(String sql, int offset, int limit)
	{
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 20);
		sb.append(sql);
		if (offset > 0) {
			sb.append(" limit ").append(offset).append(',').append(limit).append(SQL_END_DELIMITER);
		} else {
			sb.append(" limit ").append(limit).append(SQL_END_DELIMITER);
		}
		return sb.toString();
	}

	public boolean supportsPaged()
	{
		return true;
	}

	private String trim(String sql)
	{
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER)) {
			sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
		}
		return sql;
	}

	public static void main(String[] args)
	{
		MySQLDialect mysql = new MySQLDialect();
		String sql = "select a,b,c from table";
		String rs = mysql.getPagedString(sql, 10, 20);
		String rs2 = mysql.getPagedString(sql, true);
		System.out.println("+++" + rs);
		System.out.println("+++" + rs2);
	}
}
