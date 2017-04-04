package com.soule.app.sys.funcauth;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数功能权限资源维护:功能权限资源引入
 */
public class FuncAuthParseOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<FuncAuthRecordPo> page;


    public List<FuncAuthRecordPo> getPage() {
        return page;
    }

    public void setPage(List<FuncAuthRecordPo> output) {
        this.page = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}