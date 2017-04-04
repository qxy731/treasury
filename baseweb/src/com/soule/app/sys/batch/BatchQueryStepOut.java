package com.soule.app.sys.batch;

import java.io.Serializable;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数批处理配置:查询单个步骤信息
 */
public class BatchQueryStepOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private BatchStepPo one;


    public BatchStepPo getOne() {
        return one;
    }

    public void setOne(BatchStepPo output) {
        this.one = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}