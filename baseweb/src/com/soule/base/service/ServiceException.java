package com.soule.base.service;

import com.soule.base.MessageException;

/**
 * Service层的异常定义
 */
public class ServiceException extends MessageException {
    public ServiceException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    private static final long serialVersionUID = 1L;



}