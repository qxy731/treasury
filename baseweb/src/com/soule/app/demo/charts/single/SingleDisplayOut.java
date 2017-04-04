package com.soule.app.demo.charts.single;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数基本图形:显示图形
 */
public class SingleDisplayOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<SingleDataPo> data;


    public List<SingleDataPo> getData() {
        return data;
    }

    public void setData(List<SingleDataPo> output) {
        this.data = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}