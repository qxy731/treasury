package com.soule.app.sys.batch;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数批处理配置:新增步骤信息
 */
public class BatchInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private BatchStepPo step;

    public BatchStepPo getStep()  {
        return step;
    }

    public void setStep(BatchStepPo input) {
        this.step = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}