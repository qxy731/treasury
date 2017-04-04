package com.soule.app.sys.bizline.blass;

import java.io.Serializable;
import java.util.List;

import com.soule.base.po.BizLinePo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数业务线人员分配:查询人员所有业务线信息
 */
public class BlassQueryByStaffOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<BizLinePo> bizass;


    public List<BizLinePo> getBizass() {
        return bizass;
    }

    public void setBizass(List<BizLinePo> output) {
        this.bizass = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}