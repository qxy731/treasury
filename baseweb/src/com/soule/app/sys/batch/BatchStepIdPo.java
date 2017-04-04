package com.soule.app.sys.batch;

import java.io.Serializable;

/**
 * 参数传递查询所有批处理类型ID的类
 */
public class BatchStepIdPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String batchId;

    /**
     * @return 步骤ID
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId 步骤ID
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}