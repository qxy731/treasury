package com.soule.base.dialect.impl;

import com.soule.base.dialect.IDialect;


/**
 * ORACLE数据库方言
 */
public class OracleDialect implements IDialect {
	private String ORACLE_PAGEDSQL_FORMATTER = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (@_z_#) A WHERE ROWNUM <= @_x_#) WHERE RN >= @_y_#";

	// private String ORACLE_PAGEDSQL_FORMATTER=select * from ( select a.*,
	// rownum rn from (%z%) a ) where rn between %x% and %y%

	public String getPagedString(String sql, boolean hasOffset)
	{
		return null;
	}

	//offset在当前框架中是从intelliweb传过来的，从0开始
	public String getPagedString(String sql, int offset, int limit)
	{
		// 如果穿进的是pageindex和pagesize,则算法如下
		/*
		 * int m = pageIndex * pageSize; int n = m + pageSize; return "select *
		 * from ( select row_.*, rownum rownum_ from ( " + sql + " ) row_ where
		 * rownum <= " + n + ") where rownum_ > " + m;
		 */
		/*该代码取消
		if((offset==0) && (limit >0))//排除offset=0 并且 limit=-999999等情况,如果条件符合，则对于oracle数据库由于rownum必须从1开始，则我们需要在后台自动把offset加1
		{
			offset=offset+1;
		}		 
		int endset = offset + limit - 1;
		String rs = ORACLE_PAGEDSQL_FORMATTER.replaceFirst("%z%", sql).replaceFirst("%y%", offset + "").replaceFirst("%x%", endset + "");
		*/
		offset = offset + 1;
		int endset = offset + limit - 1;
		String rs = ORACLE_PAGEDSQL_FORMATTER.replaceFirst("@_y_#", offset + "").replaceFirst("@_x_#", endset + "").replaceFirst("@_z_#", sql);

		return rs;
	}

	public boolean supportsPaged()
	{
		return true;
	}

	public static void main(String[] args)
	{
		OracleDialect oracle = new OracleDialect();
		String sql = "select a,b from table";
		String rs = oracle.getPagedString(sql, 0, 10);
		System.out.println("+++" + rs);
	}
}
