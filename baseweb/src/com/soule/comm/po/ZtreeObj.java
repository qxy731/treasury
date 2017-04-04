package com.soule.comm.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZtreeObj implements Serializable {

    //private boolean checked;// 是否被勾选
    // private boolean click;//设定节点在鼠标点击后做的事情，相当于 onclick="...." 的内容，可用于一些简单操作，如果过于复杂的，建议通过 click
    // 事件进行控制处理
    //private boolean icon;
    private String id;
    private String pId;
    private String href;

   /* public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }*/

    /*private String iconClose;
    private String iconOpen;*/
    // private String iconSkin;
    private boolean isIsParent;
    private String name;
    // 此功能仅仅影响 checkbox 或 radio 的显示与否，不影响 zTree 内部关于checked等属性的计算。
    // 对于 getChangeCheckedNodes() 和 getCheckedNodes(checked) 两个方法的结果中会过滤掉 nocheck = true 的节点数据
    /*private boolean nocheck;*/
    // 设置父节点初始化展开状态,对于不需要异步获取子节点信息的父节点有效。
    private boolean open;

    // 对于存在 url 属性的节点，设置点击后跳转的目标，同超链接的 target 属性（"_blank", "_self"等）
    // private String target;
    // 指定节点被点击后的跳转页面 URL地址
    // private String url;
   /* public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }*/

    public boolean isIsParent() {
        return isIsParent;
    }

    public void setIsParent(boolean isIsParent) {
        this.isIsParent = isIsParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }*/

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}