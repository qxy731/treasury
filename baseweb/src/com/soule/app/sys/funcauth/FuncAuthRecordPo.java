package com.soule.app.sys.funcauth;

import java.io.Serializable;

/**
 * 参数传递查询功能权限资源的类
 */
public class FuncAuthRecordPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String funcId;
    private String funcCode;
    private String jspPath;
    private String funcName;
    private String funcDesc;

    /**
     * @return 功能的ID
     */
    public String getFuncId() {
        return funcId;
    }

    /**
     * @param funcId 功能的ID
     */
    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }
    /**
     * @return 功能编码
     */
    public String getFuncCode() {
        return funcCode;
    }

    /**
     * @param funcKey 功能KEY
     */
    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }
    /**
     * @return 所属页面
     */
    public String getJspPath() {
        return jspPath;
    }

    /**
     * @param jspPath 所属页面
     */
    public void setJspPath(String jspPath) {
        this.jspPath = jspPath;
    }
    /**
     * @return 功能名
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * @param funcName 功能名
     */
    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }
    /**
     * @return 功能描述
     */
    public String getFuncDesc() {
        return funcDesc;
    }

    /**
     * @param funcDesc 功能描述
     */
    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }
}