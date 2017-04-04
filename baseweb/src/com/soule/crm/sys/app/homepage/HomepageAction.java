package com.soule.crm.sys.app.homepage;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;
import com.soule.comm.tools.AppUtils;

/**
 * 首页定制维护模块表现层处理类
 */
@Namespace("/sys")
public class HomepageAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(HomepageAction.class);
    @Autowired
    private IHomepageService service;

    /**
     * 首页初始化 输入参数 
     */
    private HomepageInitDataIn initDataIn; //= new HomepageInitDataIn();
    /**
     * 修改首页布局 输入参数 
     */
    private HomepageModifyIn modifyIn; //= new HomepageModifyIn();
    /**
     * 微件信息 输入参数 
     */
    private HomepageListWidgetIn listWidgetIn; //= new HomepageListWidgetIn();

    private String initStr ="[]";

    /**
     * 初始化微件列表
     */
    private String widgetsStr ="[]";

    public void initWidget() {
        HomepageInitDataIn in = new HomepageInitDataIn();
        try {
            String uid = AppUtils.getLogonUserInfo().getUser().getUserID();
            if (uid == null) {
                return;
            }
            in.setUserId(uid);
            HomepageInitDataOut result = service.initData(in);
            ServiceResult head = result.getResultHead();
            if (head.isSuccess()) {
                initStr = JSONArray.fromObject(result.getPageConfig()).toString();
                logger.debug("init page config=" + initStr);
                widgetsStr = JSONArray.fromObject(result.getWidget()).toString();
                logger.debug("widgets =" + widgetsStr);
            }
        }
        catch(Exception e) {
            handleError(e);
        }

    }
    public String modify() {
        HomepageModifyIn in = modifyIn;
        try {
            String uid = AppUtils.getLogonUserInfo().getUser().getUserID();
            if (uid == null) {
                throw new RuntimeException("用户ID为空");
            }
            in.setUserId(uid);
            HomepageModifyOut result = service.modify(in);
            ServiceResult head = result.getResultHead();
            // TODO
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 首页初始化
     */
    @JSON(serialize=false)
    public HomepageInitDataIn getInitDataIn() {
        return this.initDataIn;
    }
    /**
     * 首页初始化
     */
    public void setInitDataIn(HomepageInitDataIn in) {
        this.initDataIn = in;
    }
    /**
     * 修改首页布局
     */
    @JSON(serialize=false)
    public HomepageModifyIn getModifyIn() {
        return this.modifyIn;
    }
    /**
     * 修改首页布局
     */
    public void setModifyIn(HomepageModifyIn in) {
        this.modifyIn = in;
    }
    /**
     * 微件信息
     */
    @JSON(serialize=false)
    public HomepageListWidgetIn getListWidgetIn() {
        return this.listWidgetIn;
    }
    /**
     * 微件信息
     */
    public void setListWidgetIn(HomepageListWidgetIn in) {
        this.listWidgetIn = in;
    }
    public String getInitStr() {
        return initStr;
    }
    public void setInitStr(String initStr) {
        this.initStr = initStr;
    }
    public String getWidgetsStr() {
        return widgetsStr;
    }
    public void setWidgetsStr(String widgetsStr) {
        this.widgetsStr = widgetsStr;
    }

}
