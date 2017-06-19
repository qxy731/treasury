package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Compiler;
import com.soule.crm.pfm.dsl.compiler.Unit;

// 取周期内天数
// 用法： 取周期内天数(a,b)
// a: 3: 月， 4: 季度， 6：年
// b: 0: 期初到指定日期， 1：期初到期末
// 对应oracle函数 IDL_GET_DAYS_IN_PERIOD
public class GetDaysInPeriodFunction implements Function {

	public static final int MONTH_PERIOD = 3;
	public static final int QUARTER_PERIOD = 4;
	public static final int YEAR_PERIOD = 6;

	public static final int DAYS_TO_CURRENT = 0;
	public static final int DAYS_TO_PERIOD_END = 1;
	
	
	private static final String IDL_GET_DAYS_IN_PERIOD = "IDL_GET_DAYS_IN_PERIOD";
	public static final String name = "取周期内天数";
	
	// 参数1：3-月初，4-季初，6-年初
	// 参数2：0-周期已发生天数  1-周期实际发生天数 
	private static final Type[] inputs = new Type[] { Compiler.NUMERIC, Compiler.NUMERIC };

	public boolean isAggFunction() {

		return false;
	}

	public String toSQL(String[] params) {
		String currentDate = params[0];
		String periodType = params[1];
		String funcType = params[2];

		return IDL_GET_DAYS_IN_PERIOD + IDLLanguage.OPENED_BRACKET + currentDate + IDLLanguage.COMMA +
			periodType + IDLLanguage.COMMA + funcType + IDLLanguage.CLOSED_BRACKET;
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

		Unit p = (Unit) params[0];
		String pn = (String) p.oprands.get(0);
		int n = 0;
		try {
			n = Integer.parseInt(pn);
		} catch (Exception e) {
			return false;
		}

		if (n != MONTH_PERIOD && n != QUARTER_PERIOD && n != YEAR_PERIOD) {
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

		if (n2 != DAYS_TO_CURRENT && n2 != DAYS_TO_PERIOD_END) {
			return false;
		}

		return true;
	}

	public boolean isDeriveFunction() {

		return false;
	}
}
