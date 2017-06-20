package com.soule.crm.pfm.dsl.ast.base;

import com.soule.crm.pfm.dsl.ast.Type;

public class Boolean implements Type {

	public String name() {
		
		return "BOOLEAN";
	}

	public boolean isVector() {
		
		return false;
	}

	

}
