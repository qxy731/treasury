package com.soule.crm.sys.app.homepage;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数首页定制:修改首页布局
 */
public class HomepageModifyOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();


    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}