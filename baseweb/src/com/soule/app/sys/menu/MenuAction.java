package com.soule.app.sys.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.tools.AppUtils;

@Namespace("/sys")
public class MenuAction extends BaseAction {
    private static final long serialVersionUID = -5798085778409908461L;
    @Autowired
    private IMenuService menuService ;
    private final Log logger = LogFactory.getLog(MenuAction.class);

    /**
     * 系统菜单
     */
    private String sysZNodes;
    /**
     * 自定义菜单
     */
    private String cusZNodes;
    /**
     * 子菜单
     */
    private String subZNodes;

    private String menuId;
    private String nodeId;
    /**
     * 应该是nodeName 因与js冲突
     */
    private String node_name;
    private String nodeUrl;
    private String parentId;
    private Integer seqId;
    private String nodeImg;
    private String relaFlag;
    private String nodeTarget;
    private String nodeCmd;
    private String nodeVisible;
    private String nodeTooltip;
    private String hasChildFlag;


    /**
     * 页面初始化方法，加载系统菜单和自定义菜单
     * 
     * @return
     */
    @Action(results={@Result(name="success", location="/jsp/sysmgr/menu/menu.jsp")})
    public String initialize() {
        try {
            List<MenuPo> sysTree = menuService.getSysMenuRoot();
            List<MenuTreeObj> viewTree = convertMenuZtree(sysTree);
            this.sysZNodes = JSONArray.fromObject(viewTree).toString();
            List<MenuPo> cusTree = menuService.getCusMenusRoot();
            viewTree = convertMenuZtree(cusTree);
            this.cusZNodes = JSONArray.fromObject(viewTree).toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    @JSON(serialize=false)
    public String queryTreeNodeModel() {
        try {
            MenuPo mpo = menuService.getMenuItemById(menuId, nodeId);
            String jsonMenuObj = JSONObject.fromObject(mpo).toString();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonMenuObj);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 删除子菜单
     * 
     * @return
     */
    public String deleteMenuModel() {
        StringBuffer sb = new StringBuffer("{");
        try {
            int flag = menuService.deleteMenuModel(menuId, nodeId);
            if (flag == 1) {
                sb.append("\"state\":\"1\"");
            } else {
                sb.append("\"state\":\"0\"");
            }
        } catch (Exception e) {
            sb.append("\"state\":\"-1\"");
            logger.error("ACTION",e);
        } finally {
            sb.append("}");
            try {
                ServletActionContext.getResponse().getWriter().write((sb.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 加载子菜单
     */
    @JSON(serialize=false)
    public String getSubMenuModel() {
        try {
            List<MenuPo> subTree = menuService.getSubMenuModel(menuId, nodeId);
            HttpServletResponse response = ServletActionContext.getResponse();
            List<MenuTreeObj> viewTree = convertMenuZtree(subTree);
            this.subZNodes = JSONArray.fromObject(viewTree).toString();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write((subZNodes));
        } catch (Exception e) {
            logger.error("ACTION",e);
        } finally {

        }
        return null;
    }

    /**
     * 保存数据
     */
    @SuppressWarnings("unchecked")
    public String saveMenu() {
        try {
            Map paramMap = new HashMap();
            paramMap.put("menuId", menuId);
            paramMap.put("nodeId", nodeId);
            paramMap.put("nodeName", node_name);
            paramMap.put("nodeUrl", nodeUrl);
            paramMap.put("parentNode", parentId);
            paramMap.put("seqId", seqId);
            paramMap.put("nodeImg", nodeImg);
            paramMap.put("relaFlag", relaFlag);
            paramMap.put("nodeTarget", nodeTarget);
            paramMap.put("nodeCmd", nodeCmd);
            paramMap.put("nodeVisible", nodeVisible);
            paramMap.put("nodeTooltip", nodeTooltip);
            paramMap.put("hasChildFlag", hasChildFlag);
            int flag = menuService.saveMenuModel(paramMap);
            if (flag == 1) {
                setRetCode(MsgConstants.I0000);
                setRetMsg(AppUtils.getMessage(MsgConstants.I0000));
            } else {
                setRetCode(MsgConstants.E0010);
                setRetMsg(AppUtils.getMessage(MsgConstants.E0010));
            }
        } catch (Exception e) {
            logger.error("", e);
            setRetCode(MsgConstants.E0010);
            setRetMsg((AppUtils.getMessage(MsgConstants.E0010)));
        } finally {
        }
        return JSON;
    }

    private List<MenuTreeObj> convertMenuZtree(List<MenuPo> listTree) {
        ArrayList<MenuTreeObj> ret = new ArrayList<MenuTreeObj>();
        for (MenuPo mpo : listTree) {
            MenuTreeObj one = new MenuTreeObj();
            one.setId(mpo.getNodeId());
            one.setpId(mpo.getParentNode());
            one.setName(mpo.getNodeName());
            one.setHref(mpo.getNodeUrl());
            YesNoFlag flag = YesNoFlag.getInstance(mpo.getHasChildFlag());
            one.setIsParent(flag == YesNoFlag.YES);
            one.setMenuId(mpo.getMenuId());
            ret.add(one);
        }
        return ret;
    }

    public String getSubZNodes() {
        return subZNodes;
    }

    public void setSubZNodes(String subZNodes) {
        this.subZNodes = subZNodes;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    public String getNodeImg() {
        return nodeImg;
    }

    public void setNodeImg(String nodeImg) {
        this.nodeImg = nodeImg;
    }

    public String getRelaFlag() {
        return relaFlag;
    }

    public void setRelaFlag(String relaFlag) {
        this.relaFlag = relaFlag;
    }

    public String getNodeTarget() {
        return nodeTarget;
    }

    public void setNodeTarget(String nodeTarget) {
        this.nodeTarget = nodeTarget;
    }

    public String getNodeCmd() {
        return nodeCmd;
    }

    public void setNodeCmd(String nodeCmd) {
        this.nodeCmd = nodeCmd;
    }

    public String getNodeVisible() {
        return nodeVisible;
    }

    public void setNodeVisible(String nodeVisible) {
        this.nodeVisible = nodeVisible;
    }

    public String getNodeTooltip() {
        return nodeTooltip;
    }

    public void setNodeTooltip(String nodeTooltip) {
        this.nodeTooltip = nodeTooltip;
    }

    public String getHasChildFlag() {
        return hasChildFlag;
    }

    public void setHasChildFlag(String hasChildFlag) {
        this.hasChildFlag = hasChildFlag;
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
}