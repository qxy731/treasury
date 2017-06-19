package com.soule.crm.pfm.param.processor;


import com.soule.crm.pfm.dsl.compiler.Unit;
import com.soule.crm.pfm.param.ast.ParameterLibMetaData;
import com.soule.crm.pfm.param.ast.ParameterTakeholder;

public class ParameterHelper {

	/**
	 * ƴ����
	 * @param paramMap
	 * @return
	 */
	public static String parameterSqlFunc(String paramterId) {
		//�޸�ȡ�������֣�ֻ��һ������������
		StringBuffer dslSql = new StringBuffer();
		String itemCode1 = "' '";
		String itemCode2 = "' '";
		String itemCode3 = "' '";
		//�����";"������1��¼�����룬2λ��¼����Ӧ������
		String paramGroupCode = paramterId.substring(0, paramterId.indexOf("."));
		String[] paramGroups = paramGroupCode.split(ParameterLibMetaData.PARAMETER_SPLIT);
		//�����������
		String funcName = getFunctionName(paramGroups);
		String paramCode = paramterId.substring(paramterId.indexOf(".") + 1);
		String[] paraSql = paramCode.split(ParameterLibMetaData.PARAMETER_SPLIT);
		//����һ,������
		if (paraSql.length > 0) {
			itemCode1 = paraSql[0];
		}
		if (paraSql.length > 1) {
			itemCode2 = "'" + paraSql[1] + "'";
		}
		if (paraSql.length > 2) {
			itemCode3 = "'" + paraSql[2] + "'";
		}
		dslSql.append("FNC_GET_PARAM").append("(")
			.append("'").append(paramGroups[0]).append("',")
			.append(itemCode1).append(",")
			.append(ParameterLibMetaData.PERIOD_NO)
			.append(")");
		/*
		dslSql.append(funcName).append("(")
			.append(ParameterLibMetaData.IDL_GET_PARAM).append("(")
			.append(ParameterLibMetaData.PERIOD_NO).append(",")
			.append("'").append(paramGroups[0]).append("',")
			.append(itemCode1).append(",")
			.append(itemCode2).append(",")
			.append(itemCode3)
			//Ԥ����ͬ����
			.append(",").append("' '")
			.append(")")
			.append(",6)");
		*/
		return dslSql.toString();
	}
	/**
	 * �޸Ĳ���EXP,ϵͳ����������ʱ��dsl_exp1;dsl_exp2:dsl_exp3 �Զ������Ϊcode1;code2;code3
	 * ϵͳ������Ҫ��ÿһ��dsl_exp����dslExp,�����루����
	 * �Զ��������Ҫ��code����''��
	 * @param parameter ����������.�����������
	 * @return��������.(dsl_exp1);(dsl_exp2);(dsl_exp3)���������.'code1';'code2';'code3'
	 */
	public static String resetParamExp(String parameter, Unit ast, String dslExp) {
		ParameterTakeholder takeHolder = (ParameterTakeholder)ast.ref;
		StringBuffer sb = new StringBuffer();
		if (takeHolder.isParamDefine(parameter)) {
			//�Զ������
			String paramGroupCode = parameter.substring(0, parameter.indexOf(".") + 1);
			String paramCode = parameter.substring(parameter.indexOf(".") + 1);
			String[] params = paramCode.split(ParameterLibMetaData.PARAMETER_SPLIT);
			sb.append(paramGroupCode);
			for (int i = 0; i < params.length; i++) {
				sb.append("'" + params[i] + "'");
				if (i != params.length - 1) {
					sb.append(ParameterLibMetaData.PARAMETER_SPLIT);
				}
			}
		} else {
			sb.append(parameter + "." + dslExp);
			/*
			for (int i = 0; i < params.length; i++) {
				if (params[i].indexOf(dslExp) == -1) {
					//�Ĺ�û��dsl_exp,ֱ��дDSL.OBJECT_ID_FROM_IND_LIB
					if (params[i].trim().equals("")) {
						sb.append(dslExp);
					} else {
						sb.append("(" + params[i] + dslExp + ")");
					}
				}
				if (i != params.length - 1) {
					sb.append(ParameterLibMetaData.PARAMETER_SPLIT);
				}
			}
			*/
		}
		return sb.toString();
	}
	/**
	 * ��������Ӧ����������
	 * @param paramGroups
	 * @return ����ת����function
	 */
	private static String getFunctionName(String[] paramGroups) {
		return ParameterLibMetaData.IDL_STR_DECIMAL;
	}
	
	/**
	 * ƴ����
	 * @param paramMap
	 * @return
	 */
	public static String parameterSql(String paramterId) {

		StringBuffer dslSql = new StringBuffer();
		
		return dslSql.toString();
	}
}
