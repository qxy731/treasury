package com.soule.crm.monitor.daily.netstat;

import java.io.Serializable;

/**
 * 参数传递网卡信息的类
 */
public class NetstatNetPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String hwId;
    private String address;
    private String desc;
    private String hwAddr;
    private String netmask;
    private String type;
    private Long speed;
    private Long rxBytes;
    private Long txBytes;
    private Long rxErrors;
    private Long txErrors;

    /**
     * @return 网卡ID
     */
    public String getHwId() {
        return hwId;
    }

    /**
     * @param hwId 网卡ID
     */
    public void setHwId(String hwId) {
        this.hwId = hwId;
    }
    /**
     * @return 网卡地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 网卡地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @return 网卡描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc 网卡描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * @return 硬件地址
     */
    public String getHwAddr() {
        return hwAddr;
    }

    /**
     * @param hwAddr 硬件地址
     */
    public void setHwAddr(String hwAddr) {
        this.hwAddr = hwAddr;
    }
    /**
     * @return 网关
     */
    public String getNetmask() {
        return netmask;
    }

    /**
     * @param netmask 网关
     */
    public void setNetmask(String netmask) {
        this.netmask = netmask;
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
    /**
     * @return 网卡速度
     */
    public Long getSpeed() {
        return speed;
    }

    /**
     * @param speed 网卡速度
     */
    public void setSpeed(Long speed) {
        this.speed = speed;
    }
    /**
     * @return 接受字节数
     */
    public Long getRxBytes() {
        return rxBytes;
    }

    /**
     * @param rxBytes 接受字节数
     */
    public void setRxBytes(Long rxBytes) {
        this.rxBytes = rxBytes;
    }
    /**
     * @return 发送字节数
     */
    public Long getTxBytes() {
        return txBytes;
    }

    /**
     * @param txBytes 发送字节数
     */
    public void setTxBytes(Long txBytes) {
        this.txBytes = txBytes;
    }
    /**
     * @return 接受错误数
     */
    public Long getRxErrors() {
        return rxErrors;
    }

    /**
     * @param rxErrors 接受错误数
     */
    public void setRxErrors(Long rxErrors) {
        this.rxErrors = rxErrors;
    }
    /**
     * @return 发送错误数
     */
    public Long getTxErrors() {
        return txErrors;
    }

    /**
     * @param txErrors 发送错误数
     */
    public void setTxErrors(Long txErrors) {
        this.txErrors = txErrors;
    }
}