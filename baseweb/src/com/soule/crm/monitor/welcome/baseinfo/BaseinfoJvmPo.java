package com.soule.crm.monitor.welcome.baseinfo;

import java.io.Serializable;

/**
 * 参数传递首页初始化的类
 */
public class BaseinfoJvmPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String jvmName;
    private String jvmVersio;
    private String jvmVendor;
    private String jvmMemory;
    private String jvmAvlMe;

    /**
     * @return JVM名称
     */
    public String getJvmName() {
        return jvmName;
    }

    /**
     * @param jvmName JVM名称
     */
    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }
    /**
     * @return JVM版本
     */
    public String getJvmVersio() {
        return jvmVersio;
    }

    /**
     * @param jvmVersio JVM版本
     */
    public void setJvmVersio(String jvmVersio) {
        this.jvmVersio = jvmVersio;
    }
    /**
     * @return JVM厂商
     */
    public String getJvmVendor() {
        return jvmVendor;
    }

    /**
     * @param jvmVendor JVM厂商
     */
    public void setJvmVendor(String jvmVendor) {
        this.jvmVendor = jvmVendor;
    }
    /**
     * @return 物理内存
     */
    public String getJvmMemory() {
        return jvmMemory;
    }

    /**
     * @param jvmMemory 物理内存
     */
    public void setJvmMemory(String jvmMemory) {
        this.jvmMemory = jvmMemory;
    }
    /**
     * @return 可用内存
     */
    public String getJvmAvlMe() {
        return jvmAvlMe;
    }

    /**
     * @param jvmAvlMe 可用内存
     */
    public void setJvmAvlMe(String jvmAvlMe) {
        this.jvmAvlMe = jvmAvlMe;
    }
}