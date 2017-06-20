package com.soule.crm.pfm.dsl.ast.base;

import com.soule.crm.pfm.dsl.ast.Operator;
import com.soule.crm.pfm.dsl.ast.Type;

public class LargeThanOperator implements Operator {
	private static final Type[] inputTypes = new Type[]{
		BaseLanguage.NUMERIC,
		BaseLanguage.NUMERIC
		};

	public static final String LARGE_THAN = ">";
	
	public Type getReturnedType() {
		
		return BaseLanguage.BOOLEAN;
	}

	public Type[] getSupportedInputTypes() {
		
		return inputTypes;
	}

	public String getWord() {
		
		return LARGE_THAN;
	}

}
