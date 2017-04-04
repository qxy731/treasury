package com.soule.base.dialect.impl;

import com.soule.base.dialect.IDialect;


/**
 * DB2数据库方言
 */
public class DB2Dialect implements IDialect {
	public String getPagedString(String sql, boolean hasOffset)
	{
		return null;
	}

	public String getPagedString(String sql, int offset, int limit)
	{
		int startOfSelect = sql.toLowerCase().indexOf("select");

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100).append(sql.substring(0, startOfSelect)) // add
			.append("select * from ( select ") // nest the main query in an  outer select
			.append(getRowNumber(sql)); // add the rownnumber bit into the outer query select list

		if (hasDistinct(sql)) {
			pagingSelect.append(" row_.* from ( ") // add another (inner) nested select
				.append(sql.substring(startOfSelect)) // add the main query
				.append(" ) as row_"); // close off the inner nested select
		} else {
			pagingSelect.append(sql.substring(startOfSelect + 6)); // add the main query
		}

		pagingSelect.append(" ) as temp_ where rownumber_ ");

		// add the restriction to the outer select
		if (offset > 0) {
			pagingSelect.append("between " + (offset + 1) + " and " + (offset + limit));
		} else {
			pagingSelect.append("<= " + limit);
		}

		return pagingSelect.toString();
	}

	public boolean supportsPaged()
	{
		return false;
	}

	/**
	 * Render the <tt>rownumber() over ( .... ) as rownumber_,</tt> bit, that
	 * goes in the select list
	 */
	private String getRowNumber(String sql)
	{
		StringBuffer rownumber = new StringBuffer(50).append("rownumber() over(");

		int orderByIndex = sql.toLowerCase().indexOf("order by");

		if (orderByIndex > 0 && !hasDistinct(sql)) {
			rownumber.append(sql.substring(orderByIndex));
		}

		rownumber.append(") as rownumber_,");

		return rownumber.toString();
	}

	private static boolean hasDistinct(String sql)
	{
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}

	public static void main(String[] args)
	{
		System.out.println(new DB2Dialect().getPagedString("/*foo*/ select * from foos", 1, 10));
		System.out.println(new DB2Dialect().getPagedString("/*foo*/ select distinct * from foos", 1, 10));
		System.out.println(new DB2Dialect().getPagedString("/*foo*/ select * from foos foo order by foo.bar, foo.baz", 1, 10));
		System.out.println(new DB2Dialect().getPagedString("/*foo*/ select distinct * from foos foo order by foo.bar, foo.baz", 1, 10));
	}
}
