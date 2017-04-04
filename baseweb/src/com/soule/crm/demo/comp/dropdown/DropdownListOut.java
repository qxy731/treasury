package com.soule.crm.demo.comp.dropdown;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数机构下拉框:获取匹配数据
 */
public class DropdownListOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<DropdownDataPo> data;


    public List<DropdownDataPo> getData() {
        return data;
    }

    public void setData(List<DropdownDataPo> output) {
        this.data = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}