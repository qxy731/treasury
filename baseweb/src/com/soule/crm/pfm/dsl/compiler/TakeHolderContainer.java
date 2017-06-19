package com.soule.crm.pfm.dsl.compiler;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class TakeHolderContainer {
	private Hashtable<String, Set<String>> usedTakeHolders = new Hashtable<String, Set<String>>();
	
	public void addTakeHolder(String start, String name){
		Set<String> holders = usedTakeHolders.get(start);
		if(holders == null){
			holders = new HashSet<String>();
			usedTakeHolders.put(start, holders);
		}
		holders.add(name);
	}
	
	public Set<String> getUsedTakeHolders(String start){
		return usedTakeHolders.get(start);
	}

}
