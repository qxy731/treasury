package com.soule.app.pfm.tm;

import java.io.Serializable;

public class BaseTar implements Serializable{
	private static final long serialVersionUID = -6217095323678434682L;
	/** 适用对象 **/
	public static String APPOBJ_ORGCODE = "10000000";
	public static String APPOBJ_ORGNAME = "机构";

	public static String APPOBJ_PERSONCODE = "01000000";
	public static String APPOBJ_PERSONNAME = "个人";

	public static String APPOBJ_ORGANDPERCODE = "11000000";
	public static String APPOBJ_ORGANDPERNAME = "机构和个人";
	//计量单位
	public static final String IND_UNIT_Y = "1";
	public static final String IND_UNIT_MY = "2";
	public static final String IND_UNIT_B = "3";
	public static final String IND_UNIT_C = "4";
	public static final String IND_UNIT_Z = "5";
	public static final String IND_UNIT_F = "6";
	
	public static final String IND_UNIT_Y_NAME = "元";
	public static final String IND_UNIT_MY_NAME = "美元";
	public static final String IND_UNIT_B_NAME = "笔";
	public static final String IND_UNIT_C_NAME = "次";
	public static final String IND_UNIT_Z_NAME = "张";
	public static final String IND_UNIT_F_NAME = "户";
	//计量精确度ind_accu
	public static final String IND_ACCU_INT = "1";
	public static final String IND_ACCU_FLOAT_TWO = "2";
	//指标属性
	public static final String IND_TYPE_HOURS_MONEY = "1";//时点余额
	public static final String IND_TYPE_DAY_MONEY = "2";//日均余额
	public static final String IND_TYPE_FUSHU = "3";//户数
	public static final String IND_TYPE_GET_MONEY = "4";//发生额
	public static final String IND_TYPE_USE_TAR = "5";//效益指标
	//日均范围(Code And KEY)
	public static final String AVER_SCOPE_Y = "Y";//年
	public static final String AVER_SCOPE_Q = "Q";//季
	public static final String AVER_SCOPE_M = "M";//月
	
	public static final String AVER_SCOPE_Y_K = "1";//年
	public static final String AVER_SCOPE_Q_K = "2";//季
	public static final String AVER_SCOPE_M_K = "3";//月
	//保存日期
	public static final String SAVE_TYPE_DAY = "DAY";//天
	public static final String SAVE_TYPE_HALFYEAR = "HALFYEAR";//半年
	public static final String SAVE_TYPE_MONTH = "MONTH";//月
	
	public static final String SAVE_TYPE_QUARTER = "QUARTER";//季
	public static final String SAVE_TYPE_TENDAYS = "TENDAYS";//旬
	public static final String SAVE_TYPE_YEAR = "YEAR";//年
	
	public static final String SAVE_TYPE_DAY_NAME = "天";//天
	public static final String SAVE_TYPE_HALFYEAR_NAME = "半年";//半年
	public static final String SAVE_TYPE_MONTH_NAME = "月";//月
	
	public static final String SAVE_TYPE_QUARTER_NAME = "季";//季
	public static final String SAVE_TYPE_TENDAYS_NAME = "旬";//旬
	public static final String SAVE_TYPE_YEAR_NAME = "年";//年
	//指标类型
	public static final String TAR_TYPE_BASE = "1";//基础指标
	public static final String TAR_TYPE_MIX = "2";//复合指标
	public static final String TAR_TYPE_HANDLE = "3";//手工指标
	
	public static final String PROC_DATE_OTHER="OT";//处理日期为其它
	
	//计算类型
	public static final String CALCTYPE_COUNT="1";//计数
	public static final String CALCTYPE_SUM="2";//总和
	
	public static final String PFM_BUSINESS_LINE="business_line";//业务条线
	
	//是否生效
	public static final String YES="1";//是
	public static final String NO="0";//否
	public static String getPointAverScopeKey(String code){
		if(AVER_SCOPE_Y.equals(code)){
			return AVER_SCOPE_Y_K;
		}else if(AVER_SCOPE_Q.equals(code)){
			return AVER_SCOPE_Q_K;
		}else if(AVER_SCOPE_M.equals(code)){
			return AVER_SCOPE_M_K;
		}
		return "";
	}
	
}
