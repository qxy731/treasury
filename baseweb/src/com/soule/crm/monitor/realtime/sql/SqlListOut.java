package com.soule.crm.monitor.realtime.sql;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数Sql监控:Sql监控结果查询
 */
public class SqlListOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<SqlDetailPo> detail;


    public List<SqlDetailPo> getDetail() {
        return detail;
    }

    public void setDetail(List<SqlDetailPo> output) {
        this.detail = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}