package com.soule.app.sys.jsptree;

import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceResult;

/**
 * 树状显示JSP维护模块表现层处理类
 */
@Namespace("/sys")
public class JspTreeAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    private IJspTreeService service;

    /**
     * 列出子目录和jsp文件 输入参数 
     */
    private JspTreeListFileIn listFileIn; //= new JspTreeListFileIn();
    private List<JspTreeListPo> rows;
    private String initstr ;

    public void doInit() {
        this.initstr = "[{'pathType':'1','name':'jsp','path':'jsp','children': [],'isexpand':false}]";
    }

    public void validateListFile() {
        if (listFileIn == null) {
            listFileIn = new JspTreeListFileIn();
        }
        if (listFileIn.getCurrPath() == null) {
            listFileIn.setCurrPath("");
        }
    }
    public String listFile() {
        JspTreeListFileIn in = listFileIn;
        try {
            String jspRoot = ServletActionContext.getServletContext().getRealPath("/");
            listFileIn.setRootPath(jspRoot);
            listFileIn.setCurrPath(URLDecoder.decode(listFileIn.getCurrPath(),"UTF8"));
            JspTreeListFileOut result = service.listFile(in);
            rows = result.getList();
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
     * 列出子目录和jsp文件
     */
    @JSON(serialize=false)
    public JspTreeListFileIn getListFileIn() {
        return this.listFileIn;
    }
    /**
     * 列出子目录和jsp文件
     */
    public void setListFileIn(JspTreeListFileIn in) {
        this.listFileIn = in;
    }
    public List<JspTreeListPo> getRows() {
        return rows;
    }
    public String getInitstr() {
        return initstr;
    }
    public void setInitstr(String initstr) {
        this.initstr = initstr;
    }
}
