package com.soule.crm.pfm.datahelper;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.pfm.tm.qtydef.QtyDefPo;
import com.soule.crm.pfm.base.common.PerformTools;
import com.soule.crm.pfm.datahelper.service.IDSLService;
import com.soule.crm.pfm.engine.IndicatorEngine;
import com.soule.crm.pfm.engine.ParameterEngine;


public class DataHelper { 
	public static final int REPORT_TYPE_4_IND_DATA = 1;
	public static final int REPORT_TYPE_4_PERFORM_DATA = 2;
	
	private IndicatorEngine engine;
	private ParameterEngine paramEngine;
	@Autowired
	private IDSLService dslService;
	
	private DataHelper(){
	    engine = IndicatorEngine.getInstance();
	    paramEngine = ParameterEngine.getInstance();
	}
	
	private static final DataHelper instance = new DataHelper();
	
	public static DataHelper getInstance(){return instance;}
	
	
	public BitSet getIndicatorType(String indicatorName){
	    BitSet bits = new BitSet(16);
	    QtyDefPo model = engine.getModel(indicatorName);
	    if (model != null){
	        String applyScope = model.getTarScope();
	        bits = PerformTools.buildBitSet(applyScope);
	    }
		return bits;
	}
	/**
	 * 
	 * @param indicatorName
	 * @return if not found, return null
	 */
	public String getIndicatorID(String indicatorName){
	    QtyDefPo model = engine.getModel(indicatorName);
	    if (model != null){
	        return "'"+model.getTarCode()+"'";
	    }else{
	        return null;
	    }
	}
	
	public  int getPeriodNumPerYear(String periodType){
		Map params=new HashMap();
		params.put("code_info_value", periodType);
		params.put("code_info_code", periodType);
		return dslService.getPeriodsOfYear(params);
	}
	
	public String computeAggValueByPeriodCode(
			String funcName, String indicatorID, 
			String where, String range
			,String periodCode, String currentGov, String defGov, int objectType){
		return "AGG_VALUE_X";
	}
	
	public String getPeriodCode(String current, String shift){
	   /* ITaskCycleService taskService = (ITaskCycleService)
	            ContextUtil.getBean(ITaskCycleService.REFER_ID);
	    try {
            PlanCyc cycle = taskService.findOffsetPlanCycle(current, Integer.parseInt(shift));
            if (cycle != null){
                return cycle.getCycCode();
            }
        } catch (ServiceException e) {
            // e.printStackTrace();
        } catch (NumberFormatException e) {
            // e.printStackTrace();
        }*/
		return null;
	}


	public String computeAggValueBySchema(String funcName, String indicatorID,
			String where, String range, String schemaCode, String phaseCode,
			String currentGov, String defGov, int type) {
		return null;
	}
	/**
	 * 获得参数ID
	 * @param parameterName 参数名称
	 * @return
	 */
	public String getParameterId(String parameterName) {
		return paramEngine.getParameterId(parameterName);
	}
	/**
	 * 判断是否为自定义参数
	 * @param paramGroupCode
	 * @return
	 */
	public boolean isParamDefine(String parameterId) {
		return paramEngine.isParamDefine(parameterId);
	}
}
