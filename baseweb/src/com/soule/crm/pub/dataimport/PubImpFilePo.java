package com.soule.crm.pub.dataimport;

import java.io.Serializable;

/**
 * 对应表PUB_IMP_FILE的类
 */
public class PubImpFilePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 目标表名
     */
    private String targetTable;
    /**
     * 存储过程名
     */
    private String procName;
    /**
     * 文件描述
     */
    private String remark;
    /**
     * 特殊服务ID
     */
    private String specServices;

    /**
     * @return 文件类型
     */
    public String getFileType () {
        return fileType;
    }

    /**
     * @param fileType 文件类型
     */
    public void setFileType (String fileType) {
        this.fileType = fileType;
    }
    /**
     * @return 目标表名
     */
    public String getTargetTable () {
        return targetTable;
    }

    /**
     * @param targetTable 目标表名
     */
    public void setTargetTable (String targetTable) {
        this.targetTable = targetTable;
    }
    /**
     * @return 存储过程名
     */
    public String getProcName () {
        return procName;
    }

    /**
     * @param procName 存储过程名
     */
    public void setProcName (String procName) {
        this.procName = procName;
    }
    /**
     * @return 文件描述
     */
    public String getRemark () {
        return remark;
    }

    /**
     * @param remark 文件描述
     */
    public void setRemark (String remark) {
        this.remark = remark;
    }

    public String getSpecServices() {
        return specServices;
    }

    public void setSpecServices(String specServices) {
        this.specServices = specServices;
    }

}