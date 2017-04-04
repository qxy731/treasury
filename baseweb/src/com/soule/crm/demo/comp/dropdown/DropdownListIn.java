package com.soule.crm.demo.comp.dropdown;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数机构下拉框:获取匹配数据
 */
public class DropdownListIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String simple;


    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple= simple;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}