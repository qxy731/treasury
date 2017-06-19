package com.soule.crm.pfm.dsl.ast;

import java.util.List;

public interface Language {
	public static final char OPENED_BRACKET = '(';
	public static final char CLOSED_BRACKET = ')';
	public static final String COMMA = ", ";
	public static final char SPACE = ' ';
	
	public List getReservedWords();
	
	public List getSupportedFunctions();
	
	public List getSupportedOperators();
	
	public List getSupportedTakeHolders();
	
	public List getSupportedTypes();
	
	

}
