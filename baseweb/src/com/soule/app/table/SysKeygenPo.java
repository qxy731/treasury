package com.soule.app.table;

import java.io.Serializable;

/**
 * 对应表SYS_KEYGEN的类
 */
public class SysKeygenPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;
    /**
     * 下一段初始值
     */
    private Long seqVal;
    /**
     * 段长
     */
    private Integer seqLen;
    /**
     * 修改中标志
     */
    private String modifing;

    /**
     * @return 表名
     */
    public String getTableName () {
        return tableName;
    }

    /**
     * @param tableName 表名
     */
    public void setTableName (String tableName) {
        this.tableName = tableName;
    }
    /**
     * @return 下一段初始值
     */
    public Long getSeqVal () {
        return seqVal;
    }

    /**
     * @param seqVal 下一段初始值
     */
    public void setSeqVal (Long seqVal) {
        this.seqVal = seqVal;
    }
    /**
     * @return 段长
     */
    public Integer getSeqLen () {
        return seqLen;
    }

    /**
     * @param seqLen 段长
     */
    public void setSeqLen (Integer seqLen) {
        this.seqLen = seqLen;
    }
    /**
     * @return 修改中标志
     */
    public String getModifing () {
        return modifing;
    }

    /**
     * @param modifing 修改中标志
     */
    public void setModifing (String modifing) {
        this.modifing = modifing;
    }

}