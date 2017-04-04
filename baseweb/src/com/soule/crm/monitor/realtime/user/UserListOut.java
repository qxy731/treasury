package com.soule.crm.monitor.realtime.user;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数在线用户监控:在线用户查询
 */
public class UserListOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<UserDetailPo> detail;


    public List<UserDetailPo> getDetail() {
        return detail;
    }

    public void setDetail(List<UserDetailPo> output) {
        this.detail = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}