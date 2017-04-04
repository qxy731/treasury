package com.soule.app.sys.filetransfer;

import com.soule.base.service.ServiceException;

/**
 * 上传下载业务操作
 */
public interface IFileTransferService {

    /**
     * 上传文件
     */
    public FileTransferUploadOut upload(FileTransferUploadIn in) throws ServiceException;
    /**
     * 上传文件列表
     */
    public FileTransferListOut list(FileTransferListIn in) throws ServiceException;
    /**
     * 删除上传文件
     */
    public FileTransferDeleteOut delete(FileTransferDeleteIn in) throws ServiceException;
    /**
     * 下载文件
     */
    public FileTransferDownloadOut download(FileTransferDownloadIn in) throws ServiceException;

}
