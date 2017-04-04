package com.soule.app.sys.batch;

import java.io.Serializable;
import com.soule.base.service.ServiceInput;

/**
 * 输出参数批处理配置:查询所有子步骤信息
 */
public class BatchQueryChildIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String batchId;
    private Integer stepId;


    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId= batchId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId= stepId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}