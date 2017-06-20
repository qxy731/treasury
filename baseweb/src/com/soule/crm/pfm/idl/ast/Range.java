package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;

public class Range implements Type {
	private static final String RANGE = "RANGE";

	public String name() {
		
		return RANGE;
	}

	public boolean isVector() {
		
		return false;
	}

}
