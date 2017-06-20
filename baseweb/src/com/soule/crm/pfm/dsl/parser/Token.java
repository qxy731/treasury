package com.soule.crm.pfm.dsl.parser;

public class Token {
    //":后面是类型"
	//[-5:2, +:3, 6:2, *:3, (4 >= 7 * (3+7)):-1, >=:3, 6:2, +:3, "5 + 6 * (1 - 3)":2]
	public static final int FUNCTION = 1;
	//以#.@.$,(,",开头 或者为double
	public static final int OPERAND = 2;
	//'+', '-', '*', '/',',', '>', '=', '<', '!'
	public static final int OPERATOR = 3;
	
	public static final int EXPRESSION = -1;
	
	//operand
	private int type;
	
	public void setType(int type) {
		this.type = type;
	}

	public Token(int type, String exp){
		this.exp = exp;
		this.type = type;
		
	}
	
	public int getType(){
		return type;
	}
	

	private String exp;
	public String getExp(){
		return exp;
	}
	
	public String toString(){
		return exp + ":" + type;
	}
}