package com.soule.base.service;

import java.util.Date;

public interface IServerTime {

    /**
     * 业务日期
     * @return
     */
    public Date getBizTime();
    /**
     * 系统日期
     */
    public Date getSysTime();
}