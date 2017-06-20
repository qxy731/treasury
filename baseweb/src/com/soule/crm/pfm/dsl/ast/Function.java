package com.soule.crm.pfm.dsl.ast;



public interface Function {
	public Type getReturnedType();
	
	public Type[] getSupportedInputTypes();
	
	public String getName();
	
	/**
	 * 
	 * @param params 
	 * @return
	 */	
	public boolean validate(Object params[]);
	
	public boolean isAggFunction();
}
