package com.soule.crm.pfm.engine.service;

import com.soule.crm.pfm.engine.model.ParameterModel;
public interface IParameterService {
	
	public static final String REFER_ID = "dsl.IParameterService";
	/**
	 * 根据参数代码，参数对象代码取参数
	 * @param paramGroupCode 参数类别代码
	 * @param paramCode 参数对象代码
	 * @return
	 */
	public ParameterModel findByCode(String paramGroupCode, String paramCode);
	/**
	 * 根据参数名称取参数
	 * @param paramGroupName 参数类别名称
	 * @param paramName 参数对象名称
	 * @param parame
	 * @return
	 */
	public ParameterModel findByName(String paramGroupName, String paramName);
	/**
	 * 判断参数代码是否存在
	 * @param paramGroupCode 参数类别代码
	 * @param paramCode 参数对象代码
	 * @return
	 * @throws ServiceException
	 */
	//public boolean isExistId(String paramGroupCode, String paramCode);
	/**
	 * 判断参数名称是否存在
	 * @param paramGroupName 参数类别名称
	 * @param paramName 参数对象名称
	 * @return
	 * @throws ServiceException
	 */
	//public boolean isExistName(String paramGroupName, String paramName);

}