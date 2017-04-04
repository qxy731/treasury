package com.soule.crm.monitor.daily.file;

import com.soule.base.service.ServiceException;

/**
 * 文件系统监控业务操作
 */
public interface IFileService {

    /**
     * 文件系统查询
     */
    public FileListOut list(FileListIn in) throws ServiceException;


}
