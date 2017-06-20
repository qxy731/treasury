package com.soule.crm.pfm.idl.translator;

import java.util.Hashtable;

import com.soule.crm.pfm.dsl.ast.Function;

public class FunctionContainer {
	private Hashtable usedFunctions = new Hashtable();
	
	public void addFunction(String name, Function func){
		
		usedFunctions.put(name, func);
		
	}
	
	public Hashtable getUsedTakeHolders(){
		return usedFunctions;
	}

}
