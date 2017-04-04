package com.soule.app.sys.pagegrant;

import java.io.Serializable;

/**
 * 参数传递列出子节点的类
 */
public class PageGrantNodePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String nodeId;
    private String nodePath;
    private String nodeName;
    private String nodeTyp;//不能是nodeType 和jsp中nodeType 冲突
    private Boolean assFlag;
    private Boolean runFlag;
    private Boolean hasChild;

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
     * @return 节点路径
     */
    public String getNodePath() {
        return nodePath;
    }

    /**
     * @param nodePath 节点路径
     */
    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
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
     * @return 节点类型
     */
    public String getNodeTyp() {
        return nodeTyp;
    }

    /**
     * @param nodeType 节点类型
     */
    public void setNodeTyp(String nodeType) {
        this.nodeTyp = nodeType;
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