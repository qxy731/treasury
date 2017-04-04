package com.soule.crm.monitor.daily.db;

import java.io.Serializable;

/**
 * 参数传递表空间信息的类
 */
public class DbTsPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String tsId;
    private String size;
    private String free;
    private Double useRate;

    /**
     * @return 表空间ID
     */
    public String getTsId() {
        return tsId;
    }

    /**
     * @param tsId 表空间ID
     */
    public void setTsId(String tsId) {
        this.tsId = tsId;
    }
    /**
     * @return 表空间大小
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size 表空间大小
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * @return 空闲空间
     */
    public String getFree() {
        return free;
    }

    /**
     * @param free 空闲空间
     */
    public void setFree(String free) {
        this.free = free;
    }
    /**
     * @return 使用百分比
     */
    public Double getUseRate() {
        return useRate;
    }

    /**
     * @param useRate 使用百分比
     */
    public void setUseRate(Double useRate) {
        this.useRate = useRate;
    }
}