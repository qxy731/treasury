package com.soule.app.sys.funcauth;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数功能权限资源维护:查询功能权限资源
 */
public class FuncAuthQueryOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<FuncAuthRecordPo> record;


    public List<FuncAuthRecordPo> getRecord() {
        return record;
    }

    public void setRecord(List<FuncAuthRecordPo> output) {
        this.record = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}