package com.soule.crm.monitor.daily.db;

import java.io.Serializable;

/**
 * 参数传递数据库状态的类
 */
public class DbDbPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String dbId;
    private String address;
    private String runFlag;

    /**
     * @return 数据库ID
     */
    public String getDbId() {
        return dbId;
    }

    /**
     * @param dbId 数据库ID
     */
    public void setDbId(String dbId) {
        this.dbId = dbId;
    }
    /**
     * @return 数据库地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 数据库地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @return 数据库状态
     */
    public String getRunFlag() {
        return runFlag;
    }

    /**
     * @param runFlag 数据库状态
     */
    public void setRunFlag(String runFlag) {
        this.runFlag = runFlag;
    }
}