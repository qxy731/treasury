package com.soule.app.sys.menugrant;

import java.util.ArrayList;
import java.util.List;

public class ViewMenuPo extends MenuGrantNodePo {
    private static final long serialVersionUID = 1L;
    /**
     * 是否展开节点
     */
    private boolean isextend = false;
    private List<ViewMenuPo> children = new ArrayList<ViewMenuPo>();

    public List<ViewMenuPo> getChildren() {
        return children;
    }
    public void setChildren(List<ViewMenuPo> children) {
        this.children = children;
    }
    public void add(ViewMenuPo one) {
        children.add(one);
    }
    public boolean isIsextend() {
        return isextend;
    }
    public void setIsextend(boolean isextend) {
        this.isextend = isextend;
    }
}
