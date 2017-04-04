package com.soule.app.sys.pagegrant;

import java.util.ArrayList;
import java.util.List;

public class PageGrantNodeVo extends PageGrantNodePo {
    private static final long serialVersionUID = 1L;
    /**
     * 是否展开节点
     */
    private boolean isextend = false;
    private List<PageGrantNodeVo> children = new ArrayList<PageGrantNodeVo>();

    public List<PageGrantNodeVo> getChildren() {
        return children;
    }
    public void setChildren(List<PageGrantNodeVo> children) {
        this.children = children;
    }
    public void add(PageGrantNodeVo one) {
        children.add(one);
    }
    public boolean isIsextend() {
        return isextend;
    }
    public void setIsextend(boolean isextend) {
        this.isextend = isextend;
    }
}
