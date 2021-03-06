package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Compiler;

public class PowerFunction implements Function {
	private static final String POWER = "POWER";
	private static final String name = "指数";
	private static final Type[] inputs = new Type[]{
		Compiler.NUMERIC,Compiler.NUMERIC
	};
	

	public boolean isAggFunction() {
		return false;
	}

	public String toSQL(String[] params) {
		return POWER + IDLLanguage.OPENED_BRACKET
		+ params[0] + IDLLanguage.COMMA + params[1] 
		+ IDLLanguage.CLOSED_BRACKET;
	}
 
	public String getName() {
		return name;
	}

	public Type getReturnedType() {
		return IDLLanguage.NUMERIC;
	}

	public Type[] getSupportedInputTypes() {
		return inputs;
	}

	public boolean validate(Object[] params) {
		return true;
	}
public boolean isDeriveFunction() {
		return false;
	}
}
