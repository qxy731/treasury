package com.soule.crm.sys.app.homepage;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数首页定制:修改首页布局
 */
public class HomepageModifyIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String userId;
    private String layoutData;


    public String getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(String layoutData) {
        this.layoutData= layoutData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}