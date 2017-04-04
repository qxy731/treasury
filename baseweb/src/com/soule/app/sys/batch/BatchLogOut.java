package com.soule.app.sys.batch;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class BatchLogOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BatchLogPo> batchLogs ;


    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

    public List<BatchLogPo> getBatchLogs() {
        return batchLogs;
    }

    public void setBatchLogs(List<BatchLogPo> batchLogs) {
        this.batchLogs = batchLogs;
    }

}
