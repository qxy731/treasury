package com.soule.app.pfm.tm.propdef;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 删除指标类（输入参数）
 */
public class PropDefDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<PropDefPo> deletes;

    public List<PropDefPo> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<PropDefPo> input) {
        this.deletes = input;
    }
    public String getDeletesStr() {
        return "";
    }

    @SuppressWarnings("unchecked")
	public void setDeletesStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.deletes = (List<PropDefPo>) JSONArray.toList(jsonArray, PropDefPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}