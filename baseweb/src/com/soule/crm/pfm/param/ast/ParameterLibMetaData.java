package com.soule.crm.pfm.param.ast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ParameterLibMetaData {
	public static final String PARAM_VALUE_TABLE = "SYS_PARAM_VALUE";
	public static final String FIELD_VALUE = "PARAM_ITEM_VALUE";
	public static final String PARAMETER_GROUP_CODE = "PARAM_GROUP_CODE";
	
	public static final String FIELD_DEFAULT_VALUE = "PARAM_DEFAULT_VALUE";
	public static final String PARAM_GROUP_TABLE = "SYS_PARAM_GROUP";
	public static String DEFAULT_DATE = "";
	
	public static final String PARAMETER_ALIAS = "PARAM";
	public static final String PERIOD_NO = "PERIOD_NO";
	
	public static final Integer PARAMETER_KEY = new Integer(99);
	public static final String PARAMETER_SPLIT = ";";
	
	public static final String IDL_STR_DECIMAL = "IDL_STR_DECIMAL";
	public static final String IDL_GET_PARAM = "IDL_GET_PARAM";
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		DEFAULT_DATE = "'" + format.format(calendar.getTime()) + "'";
	}
}
