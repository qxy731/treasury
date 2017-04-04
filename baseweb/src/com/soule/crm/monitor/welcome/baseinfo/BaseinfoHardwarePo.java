package com.soule.crm.monitor.welcome.baseinfo;

import java.io.Serializable;

/**
 * 参数传递首页初始化的类
 */
public class BaseinfoHardwarePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String hardwareAgre;
    private String cpuCount;
    private String osName;
    private String osVersion;
    private String osLanguage;
    private String osCharset;
    private String memory;
    private String freeMemory;

    /**
     * @return 硬件架构
     */
    public String getHardwareAgre() {
        return hardwareAgre;
    }

    /**
     * @param hardwareAgre 硬件架构
     */
    public void setHardwareAgre(String hardwareAgre) {
        this.hardwareAgre = hardwareAgre;
    }
    /**
     * @return CPU个数
     */
    public String getCpuCount() {
        return cpuCount;
    }

    /**
     * @param cpuCount CPU个数
     */
    public void setCpuCount(String cpuCount) {
        this.cpuCount = cpuCount;
    }
    /**
     * @return 操作系统名称
     */
    public String getOsName() {
        return osName;
    }

    /**
     * @param osName 操作系统名称
     */
    public void setOsName(String osName) {
        this.osName = osName;
    }
    /**
     * @return 操作系统版本
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * @param osVersion 操作系统版本
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
    /**
     * @return 语言环境
     */
    public String getOsLanguage() {
        return osLanguage;
    }

    /**
     * @param osLanguage 语言环境
     */
    public void setOsLanguage(String osLanguage) {
        this.osLanguage = osLanguage;
    }
    /**
     * @return 系统字符集
     */
    public String getOsCharset() {
        return osCharset;
    }

    /**
     * @param osCharset 系统字符集
     */
    public void setOsCharset(String osCharset) {
        this.osCharset = osCharset;
    }
    /**
     * @return 物理内存
     */
    public String getMemory() {
        return memory;
    }

    /**
     * @param memory 物理内存
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }
    /**
     * @return 可用内存
     */
    public String getFreeMemory() {
        return freeMemory;
    }

    /**
     * @param freeMemory 可用内存
     */
    public void setFreeMemory(String freeMemory) {
        this.freeMemory = freeMemory;
    }
}