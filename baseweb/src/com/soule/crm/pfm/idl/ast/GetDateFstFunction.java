package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.compiler.Compiler;
import com.soule.crm.pfm.dsl.compiler.Unit;

public class GetDateFstFunction implements Function {
	private static final String IDL_GET_DAT_FST = "IDL_GET_DAT_FST";
	public static final String name = "取期初";

	private static final Type[] inputs = new Type[] { Compiler.NUMERIC };

	public boolean isAggFunction() {

		return false;
	}

	public String toSQL(String[] params) {
		return IDL_GET_DAT_FST + IDLLanguage.OPENED_BRACKET + params[0] + IDLLanguage.COMMA + params[1] + IDLLanguage.CLOSED_BRACKET;
	}

	public String getName() {

		return name;
	}

	public Type getReturnedType() {

		return IDLLanguage.PERIOD_TYPE;
	}

	public Type[] getSupportedInputTypes() {

		return inputs;
	}

	public boolean validate(Object[] params) {
		Unit p = (Unit) params[0];
		String pn = (String) p.oprands.get(0);
		int n = 0;
		try {
			n = Integer.parseInt(pn);
		} catch (Exception e) {
			return false;
		}
		//��������ǰ��n[-11,0)�����ڵ�λ
		if ((n >= -11 && n < 0) || (n > 0 && n <=6)) {
			return true;
		}

		return false;
	}

	public boolean isDeriveFunction() {

		return false;
	}
}
