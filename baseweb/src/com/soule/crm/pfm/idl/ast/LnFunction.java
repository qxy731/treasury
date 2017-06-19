package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Compiler;

public class LnFunction implements Function {
	private static final String LN = "LN";
	private static final String name = "自然对数";
	
	private static final Type[] inputs = new Type[]{
		Compiler.NUMERIC
	};
	

	public boolean isAggFunction() {
		
		return false;
	}

	public String toSQL(String[] params) {
		
		return LN + IDLLanguage.OPENED_BRACKET
		+ params[0] + IDLLanguage.CLOSED_BRACKET;
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
