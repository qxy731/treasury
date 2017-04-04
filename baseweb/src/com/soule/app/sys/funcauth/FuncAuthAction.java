package com.soule.app.sys.funcauth;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 功能权限资源维护维护模块表现层处理类
 */
@Namespace("/sys")
public class FuncAuthAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    private IFuncAuthService service;

    /**
     * 查询功能权限资源 输入参数 
     */
    private FuncAuthQueryIn queryIn; //= new FuncAuthQueryIn();
    /**
     * 新增功能权限资源 输入参数 
     */
    private FuncAuthInsertIn insertIn; //= new FuncAuthInsertIn();
    /**
     * 功能权限资源删除 输入参数 
     */
    private FuncAuthDeleteIn deleteIn; //= new FuncAuthDeleteIn();
    /**
     * 功能权限资源引入 输入参数 
     */
    private FuncAuthParseIn parseIn; //= new FuncAuthParseIn();

    private String jsppath;
    public void doInit() {
        HttpServletRequest request=ServletActionContext.getRequest();
        jsppath = request.getParameter("jsppath");
    }
    public String query() {
        FuncAuthQueryIn in = queryIn;
        try {
            FuncAuthQueryOut result = service.query(in);
            ServiceResult head = result.getResultHead();
            rows = result.getRecord();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String insert() {
        FuncAuthInsertIn in = insertIn;
        try {
            IServiceResult result = service.insert(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String delete() {
        FuncAuthDeleteIn in = deleteIn;
        try {
            // TODO
            IServiceResult result = service.delete(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String parse() {
        FuncAuthParseIn in = parseIn;
        String jspRoot = ServletActionContext.getServletContext().getRealPath("/");
        try {
            if (in != null) {
                in.setRootPath(jspRoot);
            }
            FuncAuthParseOut result = service.parse(in);
            ServiceResult head = result.getResultHead();
            rows = result.getPage();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 查询功能权限资源
     */
    @JSON(serialize=false)
    public FuncAuthQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询功能权限资源
     */
    public void setQueryIn(FuncAuthQueryIn in) {
        this.queryIn = in;
    }
    /**
     * 新增功能权限资源
     */
    @JSON(serialize=false)
    public FuncAuthInsertIn getInsertIn() {
        return this.insertIn;
    }
    /**
     * 新增功能权限资源
     */
    public void setInsertIn(FuncAuthInsertIn in) {
        this.insertIn = in;
    }
    /**
     * 功能权限资源删除
     */
    @JSON(serialize=false)
    public FuncAuthDeleteIn getDeleteIn() {
        return this.deleteIn;
    }
    /**
     * 功能权限资源删除
     */
    public void setDeleteIn(FuncAuthDeleteIn in) {
        this.deleteIn = in;
    }
    /**
     * 功能权限资源引入
     */
    @JSON(serialize=false)
    public FuncAuthParseIn getParseIn() {
        return this.parseIn;
    }
    /**
     * 功能权限资源引入
     */
    public void setParseIn(FuncAuthParseIn in) {
        this.parseIn = in;
    }
    public String getJsppath() {
        return jsppath;
    }
    public void setJsppath(String jsppath) {
        this.jsppath = jsppath;
    }
}
