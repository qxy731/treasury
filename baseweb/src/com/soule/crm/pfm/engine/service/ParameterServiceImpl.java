package com.soule.crm.pfm.engine.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.soule.crm.pfm.engine.model.ParameterModel;
/**
 * 自定义参数
 * @author meng
 *
 */
@Service("parameterService")
public class ParameterServiceImpl implements IParameterService {

	public ParameterModel findByCode(String paramGroupCode, String paramCode){
		HashMap<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("paramGroupCode", paramGroupCode);
        if (!paramCode.equals("")) {
        	paramMap.put("paramCode", paramCode);
        }
        ParameterModel p=new ParameterModel();
        p.setParamCode("1111");
        p.setParamName("11111");
        return p;
	}

	public ParameterModel findByName(String paramGroupName, String paramName){
		HashMap<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("paramGroupName", paramGroupName);
        if (!paramName.equals("")) {
        	paramMap.put("paramName", paramName);
        }
        //return (ParameterModel)super.findById("dsl.parameter.findParameter", paramMap);
	    return new ParameterModel();
	}

	/*public boolean isExistId(String paramGroupCode, String paramCode){
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isExistName(String paramGroupName, String paramName){
		// TODO Auto-generated method stub
		return false;
	}*/


}
