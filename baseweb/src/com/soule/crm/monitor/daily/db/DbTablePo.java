package com.soule.crm.monitor.daily.db;

import java.io.Serializable;

/**
 * 参数传递表信息的类
 */
public class DbTablePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String tableName;
    private String used;
    private String tsId;
    private String ttype;

    /**
     * @return 表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * @return 占用空间
     */
    public String getUsed() {
        return used;
    }

    /**
     * @param used 占用空间
     */
    public void setUsed(String used) {
        this.used = used;
    }
    /**
     * @return 表空间ID
     */
    public String getTsId() {
        return tsId;
    }

    /**
     * @param runFlag 表空间ID
     */
    public void setTsId(String tsId) {
        this.tsId = tsId;
    }
    /**
     * @return 对象类型
     */
    public String getTtype() {
        return ttype;
    }

    /**
     * @param type 对象类型
     */
    public void setTtype(String type) {
        this.ttype = type;
    }
}