package com.soule.app.sys.pagegrant;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 页面功能权限设置维护模块表现层处理类
 */
@Namespace("/sys")
public class PageGrantAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(PageGrantAction.class);
    @Autowired
    private IPageGrantService service;

    /**
     * 列出子节点 输入参数 
     */
    private PageGrantListChildIn listChildIn; //= new PageGrantListChildIn();
    /**
     * 保存角色页面权限 输入参数 
     */
    private PageGrantSaveAuthIn saveAuthIn; //= new PageGrantSaveAuthIn();

    private String initstr;
    private String roleId;

    public void doInit() {
        HttpServletRequest request=ServletActionContext.getRequest();
        roleId = request.getParameter("roleId");
        PageGrantListChildIn in = new PageGrantListChildIn();
        in.setRoleId(roleId);
        try {
            String jspRoot = ServletActionContext.getServletContext().getRealPath("/");
            in.setRootPath(jspRoot);
            PageGrantListChildOut result = service.listChild(in);
            ServiceResult head = result.getResultHead();
            if (!head.isSuccess()) {
                logger.error(head.getRetCode() + head.getRetMsg());
                return;
            }
            List<PageGrantNodePo> ret = result.getNode();
            this.initstr = JSONArray.fromObject(convertVo(ret)).toString();
        }
        catch(Exception e) {
            handleError(e);
        }
    }
    private List<PageGrantNodeVo> convertVo(List<PageGrantNodePo> list) {
        List<PageGrantNodeVo> ret = new ArrayList<PageGrantNodeVo>();
        for (Object po : list) {
            PageGrantNodeVo one = new PageGrantNodeVo();
            PageGrantNodePo mpo = (PageGrantNodePo)po;
            one.setNodePath(mpo.getNodePath());
            one.setNodeTyp(mpo.getNodeTyp());
            one.setNodeId(mpo.getNodeId());
            one.setNodeName(mpo.getNodeName());
            one.setHasChild(mpo.getHasChild());
            one.setIsextend(Boolean.FALSE);
            one.setRunFlag(mpo.getRunFlag());
            one.setAssFlag(mpo.getAssFlag());
            ret.add(one);
        }
        return ret;
    }

    public String listChild() {
        PageGrantListChildIn in = listChildIn;
        try {
            String jspRoot = ServletActionContext.getServletContext().getRealPath("/");
            in.setRootPath(jspRoot);
            PageGrantListChildOut result = service.listChild(in);
            ServiceResult head = result.getResultHead();
            if (head.isSuccess()) {
                this.rows = convertVo(result.getNode());
            }
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    public String saveAuth() {
        PageGrantSaveAuthIn in = saveAuthIn;
        try {
            PageGrantSaveAuthOut result = service.saveAuth(in);
            ServiceResult head = result.getResultHead();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }

    /**
     * 列出子节点
     */
    @JSON(serialize=false)
    public PageGrantListChildIn getListChildIn() {
        return this.listChildIn;
    }
    /**
     * 列出子节点
     */
    public void setListChildIn(PageGrantListChildIn in) {
        this.listChildIn = in;
    }
    /**
     * 保存角色页面权限
     */
    @JSON(serialize=false)
    public PageGrantSaveAuthIn getSaveAuthIn() {
        return this.saveAuthIn;
    }
    /**
     * 保存角色页面权限
     */
    public void setSaveAuthIn(PageGrantSaveAuthIn in) {
        this.saveAuthIn = in;
    }
    public String getInitstr() {
        return initstr;
    }
    public void setInitstr(String initstr) {
        this.initstr = initstr;
    }
    public String getRoleId() {
        return roleId;
    }

}
