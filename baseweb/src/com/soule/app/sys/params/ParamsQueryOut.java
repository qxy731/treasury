package com.soule.app.sys.params;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数参数配置:查询参数配置
 */
public class ParamsQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<ParamsParamsPo> params;
    private ParamsParamsPo paramsPo;

    public List<ParamsParamsPo> getParams() {
        return params;
    }

    public void setParams(List<ParamsParamsPo> output) {
        this.params = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

    public ParamsParamsPo getParamsPo() {
        return paramsPo;
    }

    public void setParamsPo(ParamsParamsPo paramsPo) {
        this.paramsPo = paramsPo;
    }

}