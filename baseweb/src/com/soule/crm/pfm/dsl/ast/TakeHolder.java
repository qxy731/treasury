package com.soule.crm.pfm.dsl.ast;

public interface TakeHolder extends Operand{
	
	public Type getReturnedType();
	
	//前缀, #, &,$, @ and etc
	public String getStart();
	
	/**
	 * 
	 * @param token 除去前缀后的部分
	 * @return
	 */
	public boolean isValid(String token);
	
	/**
	 * 
	 * @param token 除去前缀后的部分
	 * @return 通常原样返回. #指标名===>#指标ID,这时需要这种转换.
	 */
	public String translate(String token);
	
	

}