package com.soule.app.sys.funcauth;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数功能权限资源维护:功能权限资源删除
 */
public class FuncAuthDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<FuncAuthRecordPo> deletes;

    public List<FuncAuthRecordPo> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<FuncAuthRecordPo> input) {
        this.deletes = input;
    }
    public String getDeletesStr() {
        return "";
    }

    @SuppressWarnings("unchecked")
	public void setDeletesStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.deletes = (List<FuncAuthRecordPo>) JSONArray.toList(jsonArray, FuncAuthRecordPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}