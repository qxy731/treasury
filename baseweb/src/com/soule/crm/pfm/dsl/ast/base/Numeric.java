package com.soule.crm.pfm.dsl.ast.base;

import com.soule.crm.pfm.dsl.ast.Type;

public class Numeric implements Type {

	public String name() {
		
		return "NUMERIC";
	}

	public boolean isVector() {
		
		return false;
	}

}
