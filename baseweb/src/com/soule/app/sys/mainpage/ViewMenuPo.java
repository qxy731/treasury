package com.soule.app.sys.mainpage;

import java.util.ArrayList;

/**
 * 菜单展示PO
 * @author wuwei
 *
 */
public class ViewMenuPo {
    /**
     * 显示值
     */
    private String text;
    /**
     * url
     */
    private String url;
    /**
     * 图标类型
     */
    private String icon;
    /**
     * 节点Id
     */
    private String nodeId;
    
    private String click = "itemclick";
    /**
     * 子菜单 ligerMenu 第一层用items，第二层用children
     */
    private ArrayList<ViewMenuPo> items = new ArrayList<ViewMenuPo>();
    /**
     * 
     */
    private ArrayList<ViewMenuPo> children = new ArrayList<ViewMenuPo>();
    /**
     * 宽度
     */
    private String width;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public ArrayList<ViewMenuPo> getChildren() {
        return children;
    }
    public void setChildren(ArrayList<ViewMenuPo> children) {
        this.children = children;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getClick() {
        return click;
    }
    public void setClick(String click) {
        this.click = click;
    }
    public String getNodeId() {
        return nodeId;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    public ArrayList<ViewMenuPo> getItems() {
        return items;
    }
    public void setItems(ArrayList<ViewMenuPo> items) {
        this.items = items;
    }


}
