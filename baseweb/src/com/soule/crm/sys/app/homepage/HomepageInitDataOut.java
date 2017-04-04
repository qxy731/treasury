package com.soule.crm.sys.app.homepage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数首页定制:首页初始化
 */
public class HomepageInitDataOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private ArrayList<ArrayList<HomepagePageConfigPo>> pageConfig;
    private List<HomepageWidgetPo> widget;

    public ArrayList<ArrayList<HomepagePageConfigPo>> getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(ArrayList<ArrayList<HomepagePageConfigPo>> output) {
        this.pageConfig = output;
    }

    public List<HomepageWidgetPo> getWidget() {
        return widget;
    }

    public void setWidget(List<HomepageWidgetPo> widget) {
        this.widget = widget;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}