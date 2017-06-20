package com.soule.crm.pfm.param.ast;

import com.soule.crm.pfm.dsl.ast.base.Numeric;
/**
 * ϵͳ��������
 * @author MXB
 *
 */
public class Parameter extends Numeric {
	
	private static final String PARAMETER = "PARAMETER"; 
	/**
	 * �Ƿ�������
	 */
	public boolean isVector() {
		return false;
	}
	/**
	 * �������� 
	 */
	public String name() {
		return PARAMETER;
	}

}
