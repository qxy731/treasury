package com.soule.app.sys.batch;

import java.io.Serializable;
import java.util.List;
import com.soule.base.service.ServiceInput;
import net.sf.json.JSONArray;

/**
 * 输出参数批处理配置:删除步骤
 */
public class BatchDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<BatchStepPo> deletes;

    public List<BatchStepPo> getDeletes() {
        return deletes;
    }

    public void setDeletes(List<BatchStepPo> input) {
        this.deletes = input;
    }
    public String getDeletesStr() {
        return "";
    }

    @SuppressWarnings("unchecked")
	public void setDeletesStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.deletes = (List<BatchStepPo>) JSONArray.toList(jsonArray, BatchStepPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}