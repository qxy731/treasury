package com.soule.app.sys.jsptree;

import java.io.Serializable;

/**
 * 参数传递列出子目录和jsp文件的类
 */
public class JspTreeListPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String path;
    private String pathType;

    /**
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @return 节点类型
     */
    public String getPathType() {
        return pathType;
    }

    /**
     * @param pathType 节点类型
     */
    public void setPathType(String pathType) {
        this.pathType = pathType;
    }
}