package com.soule.app.sys.filetransfer;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数上传下载:文件下载
 */
public class FileTransferDownloadOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private com.soule.app.table.SysUploadFilePo file;


    public com.soule.app.table.SysUploadFilePo getFile() {
        return file;
    }

    public void setFile(com.soule.app.table.SysUploadFilePo output) {
        this.file = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}