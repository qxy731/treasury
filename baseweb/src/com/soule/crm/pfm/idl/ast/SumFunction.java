package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.datahelper.IndicatorLibMetaData;
import com.soule.crm.pfm.dsl.ast.Type;


public class SumFunction implements Function {
	public static final String IDL_SUM = "IDL_SUM";
	
	private static final String name = "总和";
	
	private static final Type[] inputs = new Type[]{
		IDLLanguage.INDICATOR_TYPE, IDLLanguage.RANGE_TYPE
	};

	public boolean isAggFunction() {
	
		return true;
	}

	public String toSQL(String[] params) {
		
		String indicatorID = (params[0]);
		
		String range = (params[1]);
	
		return IDL_SUM + IDLLanguage.OPENED_BRACKET 
		+ IndicatorLibMetaData.IND_DATA_LIB_TABLE  
		+ IDLLanguage.COMMA + IndicatorLibMetaData.PERIOD_NO
		+ IDLLanguage.COMMA + IndicatorLibMetaData.IND_VALUE_FROM_IND_LIB
		+ IDLLanguage.COMMA + range
		+ IDLLanguage.COMMA + indicatorID 
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
