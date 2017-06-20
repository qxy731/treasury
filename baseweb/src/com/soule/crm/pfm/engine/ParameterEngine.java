package com.soule.crm.pfm.engine;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;

import com.soule.comm.tools.ContextUtil;
import com.soule.crm.pfm.engine.model.ParameterModel;
import com.soule.crm.pfm.engine.service.IParameterService;
public class ParameterEngine {
	
	private static ParameterEngine engine = new ParameterEngine();
	private Hashtable<String, ParameterModel> cache = new Hashtable<String, ParameterModel>();
	
	@Autowired
	private IParameterService parameterService;
	private static final String DEFINE_FLAG = "0";
	
	public static ParameterEngine getInstance() {
		return engine;
	}
	private ParameterEngine() {
		parameterService = (IParameterService)ContextUtil.getBean(IParameterService.REFER_ID);
	}
	/**
	 * 取参数名称是否有效
	 * @param parameterName 参数名称 格式 系统类别名称.参数对象名称
	 * @return
	 */
	public boolean isValid(String parameterName) {
		return false;
	}
	/**
	 * 取参数代码 需判断是自定义参数还是系统参数
	 * @param parameterName 参数名称 格式 系统类别.参数对象
	 * @return 参数类别代码.参数对象代码
	 */
	public String getParameterId(String parameterName) {
		ParameterModel model = getModelByName(parameterName);
	    if (model != null){
	    	String paramCode = " ";
	    	if (model.getParamCode() != null && !model.getParamCode().equals("")) {
	    		//paramCode = model.getParamCode();
	    		return model.getParamGroupCode() + "." + model.getParamCode();
	    	}
	        return model.getParamGroupCode();
	    }else{
	        return null;
	    }
	}
	/**
	 * 判断是否为自定义参数
	 * @param parameterId 类别代码.参数对象代码
	 * @return 自定义参数 true, 系统参数 false
	 */
	public boolean isParamDefine(String parameterId) {
		ParameterModel model = getModelByCode(parameterId);
		if (model.getIsDefineFlag().equals(DEFINE_FLAG)) {
			return true;
		} 
		return false;
	}
	/**
	 * 取参数信息
	 * @param parameterId 自定义参数：类别代码.参数对象代码;系统参数:类别代码
	 * @return
	 */
	public ParameterModel getModelByCode(String parameterId) {
		if (parameterId == null || parameterId.equals("")
				|| parameterId.equals(".") || parameterId.startsWith(".")) {
			return null;
		}
		ParameterModel model = cache.get(parameterId);
		if (model != null) {
			return model;
		}
		int index = parameterId.indexOf(".");
		String paramGroupCode = "";
		String paramCode = "";
		if (index > 0) {
			//自定义参数
			paramGroupCode = parameterId.substring(0, index);
			paramCode = parameterId.substring(index + 1);
		} else {
			//系统参数
			paramGroupCode = parameterId;
		}
		try {
			model = parameterService.findByCode(paramGroupCode, paramCode);
			cache.put(parameterId, model);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return model;
	}
	/**
	 * 取参数信息
	 * @param parameterName 参数名称 格式 系统参数：类别名称；自定义参数:系统类别.参数对象
	 * @return 
	 */
	public ParameterModel getModelByName(String parameterName) {
		if (parameterName == null || parameterName.equals("")
				|| parameterName.endsWith(".") || parameterName.startsWith(".")) {
			return null;
		}
		ParameterModel model = cache.get(parameterName);
		if (model == null) {
			int index = parameterName.indexOf(".");
			String paramGroupName = "";
			String paramName = "";
			if (index > 0) {
				//自定义参数
				paramGroupName = parameterName.substring(0, index);
				paramName = parameterName.substring(index + 1);
			} else {
				paramGroupName = parameterName;
			}
			try {
				model = parameterService.findByName(paramGroupName, paramName);
				cache.put(parameterName, model);
				return model;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		} else {
			return model;
		}
		return null;
	}
}
