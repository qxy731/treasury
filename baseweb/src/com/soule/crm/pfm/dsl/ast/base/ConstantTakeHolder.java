package com.soule.crm.pfm.dsl.ast.base;

import java.util.HashSet;
import java.util.Set;

import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Unit;

public  class ConstantTakeHolder implements TakeHolder{
	private static final String AT = "@";
	private Set constants = new HashSet();

	public Type getReturnedType() {
		
		return BaseLanguage.NUMERIC;
	}

	public String getStart() {
		
		return AT;
	}
	
	public void addConstants(String constant){
		constants.add(constant);
	}
	
	public boolean isValid(String token){
		return constants.contains(token);
	}

	public int getType() {
		
		return Unit.CONSTANT;
	}

	public String translate(String token) {
		
		return token;
	}
}
