package com.soule.crm.pfm.dsl.ast.base;
import com.soule.crm.pfm.dsl.ast.Operator;
import com.soule.crm.pfm.dsl.ast.Type;
public class AddOperator implements Operator {
	private static final Type[] inputTypes = new Type[]{
		BaseLanguage.NUMERIC,
		BaseLanguage.NUMERIC
		};

	public static final String ADD = "+";
	
	public Type getReturnedType() {
		
		return BaseLanguage.NUMERIC;
	}

	public Type[] getSupportedInputTypes() {
		
		return inputTypes;
	}

	public String getWord() {
		
		return ADD;
	}

}
