package com.soule.app.sys.menugrant;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数菜单权限设置:列出子菜单
 */
public class MenuGrantListSubOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<MenuGrantNodePo> node;


    public List<MenuGrantNodePo> getNode() {
        return node;
    }

    public void setNode(List<MenuGrantNodePo> output) {
        this.node = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}