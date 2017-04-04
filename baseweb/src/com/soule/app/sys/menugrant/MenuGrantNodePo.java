package com.soule.app.sys.menugrant;

import java.io.Serializable;

/**
 * 参数传递列出子菜单的类
 */
public class MenuGrantNodePo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String menuId;
    private String nodeId;
    private String nodeName;
    private Boolean assFlag;
    private Boolean runFlag;
    private Boolean hasChild;

    /**
     * @return 菜单ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId 菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    /**
     * @return 节点ID
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId 节点ID
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    /**
     * @return 节点名称
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName 节点名称
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    /**
     * @return 可分配
     */
    public Boolean getAssFlag() {
        return assFlag;
    }

    /**
     * @param assFlag 可分配
     */
    public void setAssFlag(Boolean assFlag) {
        this.assFlag = assFlag;
    }
    /**
     * @return 可执行
     */
    public Boolean getRunFlag() {
        return runFlag;
    }

    /**
     * @param runFlag 可执行
     */
    public void setRunFlag(Boolean runFlag) {
        this.runFlag = runFlag;
    }
    /**
     * @return 有子节点
     */
    public Boolean getHasChild() {
        return hasChild;
    }

    /**
     * @param hasChild 有子节点
     */
    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }
}