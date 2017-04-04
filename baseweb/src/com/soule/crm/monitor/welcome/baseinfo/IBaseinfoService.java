package com.soule.crm.monitor.welcome.baseinfo;

import com.soule.base.service.ServiceException;

/**
 * 基本信息展示业务操作
 */
public interface IBaseinfoService {

    /**
     * 查询系统配置信息
     */
    public BaseinfoInitDataOut initData(BaseinfoInitDataIn in) throws ServiceException;


}
