package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.datahelper.IndicatorLibMetaData;
import com.soule.crm.pfm.dsl.ast.Type;


public class IndicatorValueFunction implements Function {
	public static final String IND_VAL = "IDL_IND_VAL";
	
	public static final String name = "取指标值";
	
	private static final Type[] inputs = new Type[]{
		IDLLanguage.INDICATOR_TYPE, IDLLanguage.PERIOD_TYPE
	};

	public boolean isAggFunction() {
	
		return false;
	}

	public String toSQL(String[] params) {
		
		String indicatorID = (params[0]);
	
		return IndicatorValueFunction.IND_VAL + IDLLanguage.OPENED_BRACKET 
		+ IndicatorLibMetaData.IND_DATA_LIB_TABLE  + IDLLanguage.COMMA + params[1]
		+ IDLLanguage.COMMA +                                                        
		IndicatorLibMetaData.IND_VALUE_FROM_IND_LIB
		+ IDLLanguage.COMMA + indicatorID + IDLLanguage.CLOSED_BRACKET;
	}

	public String getName() {
	
		return name;
	}

	public Type getReturnedType() {
	
		return IDLLanguage.INDICATOR_TYPE;
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
