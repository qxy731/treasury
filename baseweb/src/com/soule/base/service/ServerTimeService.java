package com.soule.base.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service("ServerTime")
public class ServerTimeService implements IServerTime {

    public Date getBizTime() {
        return new Date();
    }

    public Date getSysTime() {
        return new Date();
    }

}
