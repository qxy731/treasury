package com.soule.app.sys.pagegrant;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数页面功能权限设置:列出子节点
 */
public class PageGrantListChildOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<PageGrantNodePo> node;


    public List<PageGrantNodePo> getNode() {
        return node;
    }

    public void setNode(List<PageGrantNodePo> output) {
        this.node = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}