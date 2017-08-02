package com.soule.app.pfm.tm;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.soule.app.pfm.tm.qtydef.QtyDefPo;

public class BaseTar implements Serializable{
	private static final long serialVersionUID = -6217095323678434682L;
	/** 适用对象 **/
	public static String APPOBJ_ORGCODE = "10000000";
	public static String APPOBJ_ORGNAME = "国库";
	public static String APPOBJ_PERSONCODE = "01000000";
	public static String APPOBJ_PERSONNAME = "金融机构";
	public static String APPOBJ_ORGANDPERCODE = "11000000";
	public static String APPOBJ_ORGANDPERNAME = "国库和金融机构";
	
	//计量单位ind_unit
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
	
	//数据类型ind_accu
	public static final String IND_ACCU_INT = "1";//整型
	public static final String IND_ACCU_FLOAT_TWO = "2";//两位小数
	public static final String IND_ACCU_FLOAT_FOUR = "3";//四位小数
	
	//指标类型tar_type
	public static final String TAR_TYPE_BASE = "E";//基础指标
	public static final String TAR_TYPE_MIX = "C";//衍生指标
	public static final String TAR_TYPE_BASE_NAME = "基础指标";
	public static final String TAR_TYPE_MIX_NAME = "衍生指标";
	
	//指标分类tar_sort  F/S/P/A/O-国库资金流动指标/国库资金安全性指标/国库资金收益性指标/国库会计核算效率指标/其他
	public static final String TAR_SORT_FLOW = "FLOW";//国库资金流动性指标
	public static final String TAR_SORT_SAFE = "SAFE";//国库资金安全性指标
	public static final String TAR_SORT_GAIN = "GAIN";//国库资金收益性指标
	public static final String TAR_SORT_ACCOUNT = "ACCOUNT";//国库会计核算效率指标
	public static final String TAR_SORT_OTHER = "O";//其他
	//保存日期
	public static final String SAVE_TYPE_DAY = "DAY";//天
	public static final String SAVE_TYPE_TENDAYS = "TENDAYS";//旬
	public static final String SAVE_TYPE_MONTH = "MONTH";//月	
	public static final String SAVE_TYPE_QUARTER = "QUARTER";//季
	public static final String SAVE_TYPE_HALFYEAR = "HALFYEAR";//半年	
	public static final String SAVE_TYPE_YEAR = "YEAR";//年	
	/*public static final String SAVE_TYPE_DAY_NAME = "天";//天
	public static final String SAVE_TYPE_TENDAYS_NAME = "旬";//旬
	public static final String SAVE_TYPE_MONTH_NAME = "月";//月
	public static final String SAVE_TYPE_QUARTER_NAME = "季";//季
	public static final String SAVE_TYPE_HALFYEAR_NAME = "半年";//半年
	public static final String SAVE_TYPE_YEAR_NAME = "年";//年
*/	
	//处理日期
	public static final String PROC_TYPE_DE = "DE";//天
	public static final String PROC_TYPE_TDE = "TDE";//旬
	public static final String PROC_TYPE_ME = "ME";//月	
	public static final String PROC_TYPE_QE= "QE";//季
	public static final String PROC_TYPE_HYE = "HYE";//半年	
	public static final String PROC_TYPE_YE = "YE";//年	
	/*public static final String PROC_TYPE_DE_NAME = "天";//天
	public static final String PROC_TYPE_TDE_NAME = "旬";//旬
	public static final String PROC_TYPE_ME_NAME = "月";//月
	public static final String PROC_TYPE_QE_NAME = "季";//季
	public static final String PROC_TYPE_HYE_NAME = "半年";//半年
	public static final String PROC_TYPE_YE_NAME = "年";//年
*/	
	//数据来源data_from
	public static final String DATA_FROM_ONE="1";//系统
	public static final String DATA_FROM_TWO="2";//手工
	
	//指标类型
	public static String getEcmByTartype(QtyDefPo qty){
		if(StringUtils.isNotEmpty(qty.getTarType())){
			return qty.getTarType();
		}
		return BaseTar.TAR_TYPE_MIX;
	 }
	 
}