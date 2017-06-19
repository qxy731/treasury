package com.soule.crm.pfm.idl.translator;

import java.util.Set;

import com.soule.crm.pfm.idl.ast.IDLLanguage;

public class RangeHelper {
	private RangeHelper(){}
	private static RangeHelper instance;
	public static RangeHelper getInstance(){
		if(instance == null){
			instance = new RangeHelper();
		}
		return instance;
	}
	
	public boolean isSingleValue(Set usedRanges){
		Object[] rs = usedRanges.toArray();
		for(int i=0; i<rs.length; i++){
			if(IDLLanguage.OF_RANGE_CN.equals(rs[i])
					|| IDLLanguage.THIS_RANGE_CN.equals(rs[i])){
				return false;
			}
		}
		return true;
	}

}