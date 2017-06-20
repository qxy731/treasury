package com.soule.crm.pfm.idl.ast;

public interface Function extends com.soule.crm.pfm.dsl.ast.Function {

	public String toSQL(String[] params);
	
	public boolean isDeriveFunction();
	
	
}
