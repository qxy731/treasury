package com.soule.crm.pfm.idl.ast;

import java.util.Hashtable;

import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;

public class RangeTakeHolder implements TakeHolder {
	public static final String DOLLER = "$";
	public static final int RANGE = 14;
	
	private Hashtable ranges = new Hashtable();

	public Type getReturnedType() {
		
		return IDLLanguage.RANGE_TYPE;
	}

	public String getStart() {
		
		return DOLLER;
	}

	public boolean isValid(String token) {
		
		return ranges.containsKey(token);
	}

	public void putRange(String key, String value){
		ranges.put(key, value);
	}
	
	public String translate(String token) {
		return (String)ranges.get(token);
		
	}

	public int getType() {
		
		return RANGE;
	}

}
