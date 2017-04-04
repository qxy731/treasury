package com.soule.crm.monitor.daily.netstat;

import com.soule.base.service.ServiceException;

/**
 * 网络状况I/O监控业务操作
 */
public interface INetstatService {

    /**
     * 网卡实时信息查询
     */
    public NetstatHwinfoOut hwinfo(NetstatHwinfoIn in) throws ServiceException;
    /**
     * 网卡信息查询
     */
    public NetstatFlowInfoOut flowInfo(NetstatFlowInfoIn in) throws ServiceException;


}
