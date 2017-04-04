package com.soule.app.sys.batch;

import java.io.Serializable;
import com.soule.base.service.ServiceInput;

/**
 * 输出参数批处理配置:查询所有批处理类型ID
 */
public class BatchQueryIdIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();



    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}