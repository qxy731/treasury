package com.soule.crm.pfm.idl.ast;
import java.util.List;

import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.ast.base.BaseLanguage;
import com.soule.crm.pfm.param.ast.Parameter;
import com.soule.crm.pfm.param.ast.ParameterTakeholder;

public class IDLLanguage extends BaseLanguage {
	
	
	public static final String CUSM_ID = "CUSM_ID";
	//public static final String CURRENT_PERIOD = "CURRENT_PERIOD";
	
	public static final char SINGLE_QUOTE = '\'';
	
	public static final char DOT = '.';
	
	public static final char EQUAL = '=';
	
	public static final Type INDICATOR_TYPE = new Indicator();
	public static final Type RANGE_TYPE = new Range();
	
	public static final Type PERIOD_TYPE = new Period();
	//系统参数类型
	public static final Type PARAEMTER_TYPE = new Parameter();
	
	public static final Function IDL_IF_FUNCTION = new IfFunction();
	
	public static final Function IDL_IND_VAL = new IndicatorValueFunction();	
	public static final Function  IDL_DAYS_OF_PRD = new GetDaysFunction();
	public static final Function  IDL_GET_PERIOD = new GetPeriodFunction();
	public static final Function  IDL_GET_DAT_FST = new GetDateFstFunction();
	
	// 计算周期内天数
	public static final Function IDL_GET_DAYS_IN_PERIOD = new GetDaysInPeriodFunction();

	// 取指标累计
	public static final Function IDL_SUM_TARGETS_IN_PERIOD = new GetSumTargetsInPeriodFunction();
	


	public static final Function  ABS = new AbsFunction();
	public static final Function  LN = new LnFunction();
	public static final Function  POWER = new PowerFunction();
	public static final Function  NVL = new NVLFunction();
	
	public static final Function  Round = new RoundFunction();
	
	public static final Function  IDL_CWS = new CwsFunction();
	public static final Function  IDL_CWA = new CwaFunction();
	public static final Function  IDL_CRS = new CrsFunction();
	public static final Function  IDL_CRA = new CraFunction();
	public static final Function  IDL_CWFQ = new CwFQFunction();
	public static final Function  IDL_CWFY = new CwFYFunction();
	
	public static final TakeHolder INDICATOR_TAKE_HOLDER = new IndicatorTakeHolder();
	private static final RangeTakeHolder RANGE_TAKE_HOLDER = new RangeTakeHolder();
	private static final ParameterTakeholder PARAMETER_TAKE_HOLDER = new ParameterTakeholder();
	
	public static final String ALL_RANGE_CN = "全体";
	private static final String ALL_RANGE = "0";
	
	public static final String OF_RANGE_CN = "当前机构及下级机构";
	private static final String OF_RANGE = "1";
	
	public static final String THIS_RANGE_CN = "当前机构";
	private static final String THIS_RANGE = "2";
	
	public static final String UNDER_1_RANGE_CN = "直辖下级行";
	private static final String UNDER_1_RANGE = "3";
	
	public static final String CREATOR_CN = "定义机构";
	private static final String CREATOR_RANGE = "9";
	

	public static final Function  IDL_SUM = new SumFunction();
	public static final Function  IDL_AVG = new AvgFunction();
	public static final Function  IDL_MAX = new MaxFunction();
	public static final Function  IDL_MIN = new MinFunction();
	public static final Function  IDL_STD = new StdFunction();
	
	public IDLLanguage(){
		super(); 
		
		
		RANGE_TAKE_HOLDER.putRange(ALL_RANGE_CN, ALL_RANGE);
		RANGE_TAKE_HOLDER.putRange(OF_RANGE_CN, OF_RANGE);
		RANGE_TAKE_HOLDER.putRange(THIS_RANGE_CN, THIS_RANGE);
		RANGE_TAKE_HOLDER.putRange(UNDER_1_RANGE_CN, UNDER_1_RANGE);
		RANGE_TAKE_HOLDER.putRange(CREATOR_CN, CREATOR_RANGE);
		
		
		List takeHolders = super.getSupportedTakeHolders();
		takeHolders.add(INDICATOR_TAKE_HOLDER);
		takeHolders.add(RANGE_TAKE_HOLDER);
		takeHolders.add(PARAMETER_TAKE_HOLDER);
		
		
		List funcs = super.getSupportedFunctions();
		funcs.add(IDL_IF_FUNCTION);
		
		funcs.add(IDL_IND_VAL);
		funcs.add(IDL_GET_PERIOD);
		funcs.add(IDL_DAYS_OF_PRD);
		funcs.add(IDL_GET_DAT_FST);
		funcs.add(IDL_GET_DAYS_IN_PERIOD);
		funcs.add(IDL_SUM_TARGETS_IN_PERIOD);

		funcs.add(ABS);
		funcs.add(LN);
		funcs.add(POWER);
		funcs.add(NVL);
		funcs.add(Round);
		
		funcs.add(IDL_CWS);
		funcs.add(IDL_CWA);
		funcs.add(IDL_CRS);
		funcs.add(IDL_CRA);
		funcs.add(IDL_CWFQ);
		funcs.add(IDL_CWFY);
		
		funcs.add(IDL_SUM);
		funcs.add(IDL_AVG);
		funcs.add(IDL_MAX);
		funcs.add(IDL_MIN);
		funcs.add(IDL_STD);
	}

}