package com.soule.app.sys.batch;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数批处理配置:查询步骤信息列表
 */
public class BatchQueryMainOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BatchStepPo> steps;


    public List<BatchStepPo> getSteps() {
        return steps;
    }

    public void setSteps(List<BatchStepPo> output) {
        this.steps = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}