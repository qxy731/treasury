package com.soule.app.sys.params;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数参数配置:删除参数配置
 */
public class ParamsDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String paraId;
    private List<ParamsParamsPo> deletes;

    public String getParaId() {
        return paraId;
    }

    public void setParaId(String paraId) {
        this.paraId = paraId;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

    public List<ParamsParamsPo> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<ParamsParamsPo> input) {
        this.deletes = input;
    }

    public String getDeletesStr() {
        return "";
    }

    public void setDeletesStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.deletes = (List<ParamsParamsPo>) JSONArray.toList(jsonArray, ParamsParamsPo.class);
    }

}