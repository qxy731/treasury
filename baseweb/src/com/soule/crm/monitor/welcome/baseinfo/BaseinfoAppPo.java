package com.soule.crm.monitor.welcome.baseinfo;

import java.io.Serializable;

/**
 * 参数传递首页初始化的类
 */
public class BaseinfoAppPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String machineName;
    private String machineIp;
    private String appMachineType;
    private String appMatchineVersion;
    private String managerPort;
    private String servicePort;

    /**
     * @return 机器名称
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * @param machineName 机器名称
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    /**
     * @return IP地址
     */
    public String getMachineIp() {
        return machineIp;
    }

    /**
     * @param machineIp IP地址
     */
    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }
    /**
     * @return 应用服务器类型
     */
    public String getAppMachineType() {
        return appMachineType;
    }

    /**
     * @param appMachineType 应用服务器类型
     */
    public void setAppMachineType(String appMachineType) {
        this.appMachineType = appMachineType;
    }
    /**
     * @return 应用服务器版本
     */
    public String getAppMatchineVersion() {
        return appMatchineVersion;
    }

    /**
     * @param appMatchineVersion 应用服务器版本
     */
    public void setAppMatchineVersion(String appMatchineVersion) {
        this.appMatchineVersion = appMatchineVersion;
    }
    /**
     * @return 管理监听端口
     */
    public String getManagerPort() {
        return managerPort;
    }

    /**
     * @param managerPort 管理监听端口
     */
    public void setManagerPort(String managerPort) {
        this.managerPort = managerPort;
    }
    /**
     * @return 服务监听端口
     */
    public String getServicePort() {
        return servicePort;
    }

    /**
     * @param servicePort 服务监听端口
     */
    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }
}