package com.soule.app.sys.batch;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数批处理配置:查询所有批处理类型ID
 */
public class BatchQueryIdOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BatchStepIdPo> stepId;


    public List<BatchStepIdPo> getStepId() {
        return stepId;
    }

    public void setStepId(List<BatchStepIdPo> output) {
        this.stepId = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}