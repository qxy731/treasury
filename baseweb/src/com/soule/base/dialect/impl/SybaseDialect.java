package com.soule.base.dialect.impl;

import com.soule.base.dialect.IDialect;


/**
 * Sybase的数据库方言类
 */
public class SybaseDialect implements IDialect {
	public String getPagedString(String sql, boolean hasOffset)
	{
		return null;
	}

	public String getPagedString(String sql, int offset, int limit)
	{

		return null;
	}

	public boolean supportsPaged()
	{
		return false;
	}
}
