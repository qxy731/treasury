package com.soule.crm.monitor.daily.file;

import java.io.Serializable;

/**
 * 参数传递文件系统信息的类
 */
public class FileDetailPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String fileId;
    private String size;
    private String used;
    private String avail;
    private String usedRate;
    private String mountOn;
    private String type;

    /**
     * @return 文件系统
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId 文件系统
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    /**
     * @return 总数
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size 总数
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * @return 已使用
     */
    public String getUsed() {
        return used;
    }

    /**
     * @param used 已使用
     */
    public void setUsed(String used) {
        this.used = used;
    }
    /**
     * @return 可使用
     */
    public String getAvail() {
        return avail;
    }

    /**
     * @param avail 可使用
     */
    public void setAvail(String avail) {
        this.avail = avail;
    }
    /**
     * @return 使用百分比
     */
    public String getUsedRate() {
        return usedRate;
    }

    /**
     * @param usedRate 使用百分比
     */
    public void setUsedRate(String usedRate) {
        this.usedRate = usedRate;
    }
    /**
     * @return 挂接点
     */
    public String getMountOn() {
        return mountOn;
    }

    /**
     * @param mountOn 挂接点
     */
    public void setMountOn(String mountOn) {
        this.mountOn = mountOn;
    }
    /**
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }
}