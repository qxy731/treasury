package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Compiler;
import com.soule.crm.pfm.dsl.compiler.Unit;
import com.soule.crm.pfm.idl.ast.IDLLanguage;

// 用法： 取指标累计(指标,周期)
// 指标(indicator): #指标名称
// 周期(period_type): 3: 月， 4: 季度， 6：年
// 对应oracle函数 IDL_SUM_TARGETS_IN_PERIOD
public class GetSumTargetsInPeriodFunction implements Function {

	public static final int MONTH_PERIOD = 3;
	public static final int QUARTER_PERIOD = 4;
	public static final int YEAR_PERIOD = 6;

	private static final String IDL_SUM_TARGETS_IN_PERIOD = "IDL_SUM_TARGETS_IN_PERIOD";
	public static final String name = "取指标累计";
	
	// 参数1: 指标
	// 参数2: 3-月，4-季，6-年
	private static final Type[] inputs = new Type[] { 
		IDLLanguage.INDICATOR_TYPE, Compiler.NUMERIC };

	public boolean isAggFunction() {

		return false;
	}

	// create or replace function IDL_SUM_TARGETS_IN_PERIOD(P_CUSM_ID in varchar, 
    //	P_TARGET_CODE in varchar, P_TARGET_DATE in varchar, P_PERIOD_TYPE in int)
    public String toSQL(String[] params) {
    	String targetCode = params[0];
    	String currentDate = params[1];
		String periodType = params[2];

		String sql =
			IDL_SUM_TARGETS_IN_PERIOD 
				+ IDLLanguage.OPENED_BRACKET
			 		+ IDLLanguage.CUSM_ID + IDLLanguage.COMMA
			 		// targetCode似乎是一个伪字段 IndicatorLibMetaData.IND_DATA_LIB_IND_ID
			 		+ targetCode + IDLLanguage.COMMA
			 		// currentDate似乎是一个伪字段: IndicatorDataTableMetaData.PERIOD_NO
			 		+ currentDate + IDLLanguage.COMMA
			 		+ periodType
			 	+ IDLLanguage.CLOSED_BRACKET;
		return sql;
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
		if (params == null || params.length != 2) {
			return false;
		}

		// 已经转为indicator编码了,故不需要怎么判断
		Unit p = (Unit) params[0];
		String indicator = (String) p.oprands.get(0);
		if (indicator == null) {
			return false;
		}
		
		Unit p2 = (Unit) params[1];
		String pn2 = (String) p2.oprands.get(0);
		int n2 = 0;
		try {
			n2 = Integer.parseInt(pn2);
		} catch (Exception e) {
			return false;
		}

		if (n2 != MONTH_PERIOD && n2 != QUARTER_PERIOD && n2 != YEAR_PERIOD) {
			return false;
		}

		return true;
	}

	public boolean isDeriveFunction() {

		return false;
	}
}