/**
 * 
 */
package com.soule.web.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TreeTag extends SimpleTagSupport {
    //页面定义树的名字，必输项
    protected String name;
    //指定service名称，必输项
    protected String service;
    //树结点文本，必须指定
    protected String textFieldName = "text";
    //是否一次性全部加载
    protected String isShowAll = "false";
    //树结点的id，当isShowAll="true"时必须指定
    protected String idFieldName;
    //上级树结点的id，当isShowAll="true"时必须指定
    protected String parentIDFieldName;
    //树根节点的值，当isShowAll="true"时必须指定
    protected String topParentIDValue = null;
    //判断是否父结点的字段名称，必输项
    protected String hasChildFlag;
    //判断是否父结点的字段的值，必输项
    protected String hasChildValue = "1";
    //是否显示多选框
    protected Boolean checkbox = false;
    //父结点图标
    protected String parentIcon = "folder";
    //叶子结点图标
    protected String childIcon = "leaf";
    //是否显示连接线
    protected Boolean treeLine = true;
    //是否单选，当checkbox=true时
    protected Boolean single = false;
    //是否以动画的形式显示(展开/收缩节点)
    protected Boolean slide = true;
    //树结点宽度
    protected String nodeWidth = "150";
    //选中结点时的事件自定义名称，function XX(data)
    protected String treeOnClick;
    
    public void doTag() throws JspException, IOException {
        StringBuilder sbf = new StringBuilder();
        String treeName = "root_" + (Math.round(Math.random() * 1000));
        sbf.append("<script type='text/javascript'>\n");
        sbf.append(" var " + this.name + " = null;\n");
        sbf.append(" $(function (){\n");
        sbf.append(" $('#" + treeName+ "Tree').ligerTree({ \n");
        if(null==this.topParentIDValue||"null".equals(this.topParentIDValue)){
            sbf.append(" url:'treeManager!getSubTree.action?service="+this.service+"',\n");
        }else{
            sbf.append(" url:'treeManager!getSubTree.action?service="+this.service+"&nodeIdValue=" + this.topParentIDValue + "',\n");
        }
        if ("true".equals(this.isShowAll)) {
            sbf.append(" idFieldName : '" + this.idFieldName + "', \n");
            sbf.append(" parentIDFieldName : '" + this.parentIDFieldName + "', \n");
            if("null".equals(this.topParentIDValue)){
                sbf.append(" topParentIDValue : null, \n");
            }else{
                sbf.append(" topParentIDValue : '"+this.topParentIDValue+"', \n");
            }
        }
        sbf.append(" textFieldName : '" + this.textFieldName + "', \n");
        sbf.append(" nodeWidth : " + this.nodeWidth + ", \n");
        sbf.append(" checkbox : " + this.checkbox + ", \n");
        sbf.append(" parentIcon : '" + this.parentIcon + "', \n");
        sbf.append(" childIcon : '" + this.childIcon + "', \n");
        sbf.append(" treeLine : " + this.treeLine + ", \n");
        sbf.append(" single : " + this.single + ", \n");
        sbf.append(" slide : " + this.slide + ", \n");
        sbf.append(" onSelect : function(node){\n");
        if (null != this.treeOnClick && !"".equals(this.treeOnClick)) {
            sbf.append("   " + this.treeOnClick + "(node.data);\n");
        }
        sbf.append("   if(node.data."+this.hasChildFlag+"=='" + this.hasChildValue + "'){\n");
        sbf.append("     if (node.data.children && node.data.children.length == 0) { \n");
        sbf.append("       var param = {};\n");
        sbf.append("       param.data = JSON.stringify(node.data);\n");
        sbf.append("       param.service = '" + this.service + "';\n");
        sbf.append("       " + this.name + ".loadData(node.target, 'treeManager!getSubTree.action',param);\n");
        sbf.append(" }}},\n");
        sbf.append(" isLeaf:function(data){return data."+this.hasChildFlag+"=='" + this.hasChildValue + "'} \n");
        sbf.append(" });\n");
        sbf.append(this.name + " = $('#" + treeName + "Tree').ligerGetTreeManager();\n");
        sbf.append(" });\n");
        sbf.append("</script>\n");
        sbf.append("<ul id='" + treeName + "Tree' ></ul>");
        JspWriter out = getJspContext().getOut();
        out.print(sbf.toString());
    }
    
    public String getTreeOnClick() {
        return treeOnClick;
    }

    public void setTreeOnClick(String treeOnClick) {
        this.treeOnClick = treeOnClick;
    }

    public String getHasChildFlag() {
        return hasChildFlag;
    }

    public void setHasChildFlag(String hasChildFlag) {
        this.hasChildFlag = hasChildFlag;
    }

    public String getHasChildValue() {
        return hasChildValue;
    }

    public void setHasChildValue(String hasChildValue) {
        this.hasChildValue = hasChildValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIsShowAll() {
        return isShowAll;
    }

    public void setIsShowAll(String isShowAll) {
        this.isShowAll = isShowAll;
    }

    public String getTextFieldName() {
        return textFieldName;
    }

    public void setTextFieldName(String textFieldName) {
        this.textFieldName = textFieldName;
    }

    public String getIdFieldName() {
        return idFieldName;
    }

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public String getParentIDFieldName() {
        return parentIDFieldName;
    }

    public void setParentIDFieldName(String parentIDFieldName) {
        this.parentIDFieldName = parentIDFieldName;
    }

    public String getTopParentIDValue() {
        return topParentIDValue;
    }

    public void setTopParentIDValue(String topParentIDValue) {
        this.topParentIDValue = topParentIDValue;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getParentIcon() {
        return parentIcon;
    }

    public void setParentIcon(String parentIcon) {
        this.parentIcon = parentIcon;
    }

    public String getChildIcon() {
        return childIcon;
    }

    public void setChildIcon(String childIcon) {
        this.childIcon = childIcon;
    }

    public Boolean getTreeLine() {
        return treeLine;
    }

    public void setTreeLine(Boolean treeLine) {
        this.treeLine = treeLine;
    }

    public Boolean getSingle() {
        return single;
    }

    public void setSingle(Boolean single) {
        this.single = single;
    }

    public Boolean getSlide() {
        return slide;
    }

    public void setSlide(Boolean slide) {
        this.slide = slide;
    }

    public String getNodeWidth() {
        return nodeWidth;
    }

    public void setNodeWidth(String nodeWidth) {
        this.nodeWidth = nodeWidth;
    }
    
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
