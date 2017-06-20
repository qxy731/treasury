package com.soule.crm.pfm.idl.translator;

import java.util.Hashtable;

public class GovmentHelper {
	private GovmentHelper(){}
	private static GovmentHelper instance;
	public static GovmentHelper getInstance(){
		if(instance == null){
			instance = new GovmentHelper();
		}
		return instance;
	}
	
	public boolean joinObjectViewIsRequired(String govID, Hashtable indRef){
		Object[] keys = indRef.keySet().toArray();
		for(int i=0; i< keys.length; i++){
			if(govID.equals(keys[i]))return false;
		}
		return true;
	}

}