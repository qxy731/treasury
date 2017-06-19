package com.soule.crm.pfm.idl.ast;


import com.soule.crm.pfm.dsl.ast.base.Numeric;

public class Indicator extends Numeric {

	public boolean isVector() {
		
		return true;
	}

	public String name() {
		
		return "INDICATOR";
	}

}
