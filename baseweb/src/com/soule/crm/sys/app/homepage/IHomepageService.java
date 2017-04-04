package com.soule.crm.sys.app.homepage;

import com.soule.base.service.ServiceException;

/**
 * 首页定制业务操作
 */
public interface IHomepageService {

    /**
     * 查询系统中某个用户的首页配置信息
     */
    public HomepageInitDataOut initData(HomepageInitDataIn in) throws ServiceException;
    /**
     * 修改首页布局信息
     */
    public HomepageModifyOut modify(HomepageModifyIn in) throws ServiceException;
    /**
     * 查询所有微件信息
     */
    public HomepageListWidgetOut listWidget(HomepageListWidgetIn in) throws ServiceException;


}
