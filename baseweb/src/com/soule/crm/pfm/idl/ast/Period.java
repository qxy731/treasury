package com.soule.crm.pfm.idl.ast;

import com.soule.crm.pfm.dsl.ast.Type;

public class Period implements Type {
	private static final String PERIOD = "PERIOD";

	public String name() {
		return PERIOD;
	}

	public boolean isVector() {
		return false;
	}

}
