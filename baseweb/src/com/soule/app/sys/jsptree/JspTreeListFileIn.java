package com.soule.app.sys.jsptree;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数树状显示JSP:列出子目录和jsp文件
 */
public class JspTreeListFileIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private String currPath;
    private String rootPath;


    public String getCurrPath() {
        return currPath;
    }

    public void setCurrPath(String currPath) {
        this.currPath= currPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath= rootPath;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}