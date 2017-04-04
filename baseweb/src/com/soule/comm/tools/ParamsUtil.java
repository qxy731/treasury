package com.soule.comm.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.app.sys.params.IParamsService;
import com.soule.app.sys.params.ParamsParamsPo;
import com.soule.base.service.ServiceException;

@Service
public class ParamsUtil {

	@Autowired
	IParamsService paramsservice;
    /**
     * 查询系统参数值
     * 
     * @param paraId
     * @return
     */
    public String getParamValueByParamId(String paraId) {
        String paramValue = "";
        ParamsParamsPo paramsPo = null;
        try {
            paramsPo = paramsservice.queryParamByParamId(paraId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (paramsPo != null) {
            paramValue = paramsPo.getParaValue();
        }
        return paramValue;
    }

    public ParamsParamsPo getParamByParamId(String paraId) {
        ParamsParamsPo paramsPo = null;
        try {
            paramsPo = paramsservice.queryParamByParamId(paraId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return paramsPo;
    }

    public ParamsParamsPo getParamByParamIdAndRank(String paraId, String paraRank) {
        ParamsParamsPo paramsPo = null;
        try {
            paramsPo = paramsservice.queryParams(paraId, paraRank);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return paramsPo;
    }

    public String getParamValueByParamIdAndRank(String paraId, String paraRank) {
        String paramValue = "";
        ParamsParamsPo paramsPo = null;
        try {
            paramsPo = paramsservice.queryParams(paraId, paraRank);
            if (paramsPo != null) {
                paramValue = paramsPo.getParaValue();
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return paramValue;
    }

}
