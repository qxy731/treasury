package com.soule.crm.sys.app.homepage;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数首页定制:微件信息
 */
public class HomepageListWidgetOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<HomepageWidgetPo> widget;


    public List<HomepageWidgetPo> getWidget() {
        return widget;
    }

    public void setWidget(List<HomepageWidgetPo> output) {
        this.widget = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}