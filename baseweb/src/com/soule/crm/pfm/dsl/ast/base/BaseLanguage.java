package com.soule.crm.pfm.dsl.ast.base;

import java.util.ArrayList;
import java.util.List;

import com.soule.crm.pfm.dsl.ast.Language;
import com.soule.crm.pfm.dsl.ast.Operator;
import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;

public class BaseLanguage implements Language {
	
	public static final Type NUMERIC = new  Numeric();
	
	public static final Type BOOLEAN = new  Boolean();
	
	private static final List types = new ArrayList(); 
	
	private static final Operator ADD = new AddOperator();
	private static final Operator SUB = new SubOperator();
	private static final Operator MULT = new MultOperator();
	private static final Operator DIV = new DivOperator();
	
	private static final Operator LARGE_THAN = new LargeThanOperator();
	private static final Operator LESS_THAN = new LessThanOperator();
	private static final Operator EQUAL = new EqualOperator();
	private static final Operator NOT_EQUAL = new NotEqualOperator();
	private static final Operator LARGE_THAN_OR_EQUAL = new LargeThanOrEqualOperator();
	private static final Operator LESS_THAN_OR_EQUAL = new LessThanOrEqualOperator();
	
	private static final Operator AND = new AndOperator();
	private static final Operator OR = new OrOperator();
	private static final Operator NOT = new NotOperator();
	
	private static final List operatos = new ArrayList(); 
	
	private static final TakeHolder CONSTANT = new ConstantTakeHolder();
	
	private static final List takeHolders = new ArrayList(); 
	
	private static final List reservedWords = new ArrayList(); 
	
	private static final List functions = new ArrayList(); 
	
	public BaseLanguage(){
		types.add(NUMERIC);
		types.add(BOOLEAN);
		
		operatos.add(ADD);
		operatos.add(SUB);
		operatos.add(MULT);
		operatos.add(DIV);
		
		operatos.add(LARGE_THAN);
		operatos.add(EQUAL);
		operatos.add(LESS_THAN);
		operatos.add(NOT_EQUAL);
		operatos.add(LARGE_THAN_OR_EQUAL);
		operatos.add(LESS_THAN_OR_EQUAL);
		
		
		
		operatos.add(AND);
		operatos.add(OR);
		operatos.add(NOT);
		
		takeHolders.add(CONSTANT);
		
		reservedWords.add("`");
		reservedWords.add("~");
		reservedWords.add("%");
		reservedWords.add("^");
		reservedWords.add(";");
		reservedWords.add(":");
		reservedWords.add("|");
		reservedWords.add("\\");
		reservedWords.add("'");
		reservedWords.add("\"");
		reservedWords.add("?");
		
	}

	public List getSupportedOperators() {
		
		return operatos;
	}

	public List getSupportedTakeHolders() {
		
		return takeHolders;
	}

	public List getSupportedTypes(){
		return types;
	}

	public List getReservedWords() {
		
		return reservedWords;
	}

	public List getSupportedFunctions() {
		
		return functions;
	}
}
