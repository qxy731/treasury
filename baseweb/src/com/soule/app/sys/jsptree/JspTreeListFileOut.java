package com.soule.app.sys.jsptree;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数树状显示JSP:列出子目录和jsp文件
 */
public class JspTreeListFileOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<JspTreeListPo> list;


    public List<JspTreeListPo> getList() {
        return list;
    }

    public void setList(List<JspTreeListPo> output) {
        this.list = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}