package com.soule.app.sys.qstaff;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数选择客户公共页面:查询客户信息
 */
public class QstaffQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<QstaffBasePo> base;


    public List<QstaffBasePo> getBase() {
        return base;
    }

    public void setBase(List<QstaffBasePo> output) {
        this.base = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}