package com.soule.app.sys.menugrant;

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
import com.soule.comm.CommConstants;

/**
 * 菜单权限设置维护模块表现层处理类
 */
@Namespace("/sys")
public class MenuGrantAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final static Log logger = LogFactory.getLog(MenuGrantAction.class);

    /**
     * 列出子菜单 输入参数 
     */
    private MenuGrantListSubIn listSubIn; //= new MenuGrantListSubIn();
    /**
     * 保存角色菜单权限 输入参数 
     */
    private MenuGrantSaveAuthIn saveAuthIn; //= new MenuGrantSaveAuthIn();

    /**
     * 系统菜单
     */
    private String sysZNodes;
    /**
     * 自定义菜单
     */
    private String cusZNodes;
    private String menuId;
    private String roleId;
    @Autowired
    private IMenuGrantService service ;
    public void doInit() {
        HttpServletRequest request=ServletActionContext.getRequest();
        roleId = request.getParameter("roleId");
        MenuGrantListSubIn in = new MenuGrantListSubIn();
        in.setRoleId(roleId);
        try {
            MenuGrantListSubOut result = service.listSub(in);
            ServiceResult head = result.getResultHead();
            if (!head.isSuccess()) {
                logger.error(head.getRetCode() + head.getRetMsg());
                return;
            }
            List<MenuGrantNodePo> ret = result.getNode();
            List<MenuGrantNodePo> sysRoot = new ArrayList<MenuGrantNodePo>();
            List<MenuGrantNodePo> cusRoot = new ArrayList<MenuGrantNodePo>();
            for (MenuGrantNodePo po: ret) {
                if (CommConstants.SYS_MENU_ROOT_ID.equals(po.getMenuId())) {
                    sysRoot.add(po);
                }
                else{
                    cusRoot.add(po);
                }
            }
            List<ViewMenuPo> viewTree = convertViewTree(sysRoot);
            this.sysZNodes = JSONArray.fromObject(viewTree).toString();
            viewTree = convertViewTree(cusRoot);
            this.cusZNodes = JSONArray.fromObject(viewTree).toString();
        }
        catch(Exception e) {
            handleError(e);
        }
    }
    @SuppressWarnings("rawtypes")
	private List<ViewMenuPo> convertViewTree(List listTree) {
        List<ViewMenuPo> ret = new ArrayList<ViewMenuPo>();
        for (Object po : listTree) {
            ViewMenuPo one = new ViewMenuPo();
            MenuGrantNodePo mpo = (MenuGrantNodePo)po;
            one.setMenuId(mpo.getMenuId());
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
    public String listSub() {
        MenuGrantListSubIn in = listSubIn;
        try {
            MenuGrantListSubOut result = service.listSub(in);
            ServiceResult head = result.getResultHead();
            this.rows = convertViewTree(result.getNode());
            this.menuId = listSubIn.getMenuId();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }
        catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    public String saveAuth() {
        MenuGrantSaveAuthIn in = saveAuthIn;
        try {
            MenuGrantSaveAuthOut result = service.saveAuth(in);
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
     * 列出子菜单
     */
    public MenuGrantListSubIn getListSubIn() {
        return this.listSubIn;
    }
    /**
     * 列出子菜单
     */
    public void setListSubIn(MenuGrantListSubIn in) {
        this.listSubIn = in;
    }
    /**
     * 保存角色菜单权限
     */
    @JSON(serialize=false)
    public MenuGrantSaveAuthIn getSaveAuthIn() {
        return this.saveAuthIn;
    }
    /**
     * 保存角色菜单权限
     */
    public void setSaveAuthIn(MenuGrantSaveAuthIn in) {
        this.saveAuthIn = in;
    }
    public String getSysZNodes() {
        return sysZNodes;
    }
    public void setSysZNodes(String sysZNodes) {
        this.sysZNodes = sysZNodes;
    }
    public String getCusZNodes() {
        return cusZNodes;
    }
    public void setCusZNodes(String cusZNodes) {
        this.cusZNodes = cusZNodes;
    }
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
