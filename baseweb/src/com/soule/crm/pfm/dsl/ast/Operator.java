package com.soule.crm.pfm.dsl.ast;

public interface Operator {
	public Type getReturnedType();
	
	public Type[] getSupportedInputTypes();
	
	//+, -, *, / and etc
	public String getWord();
}
