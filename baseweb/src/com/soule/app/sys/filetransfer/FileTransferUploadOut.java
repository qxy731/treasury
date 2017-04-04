package com.soule.app.sys.filetransfer;

import java.io.Serializable;
import java.util.List;

import com.soule.app.table.SysUploadFilePo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数上传下载:上传文件
 */
public class FileTransferUploadOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();

    private SysUploadFilePo file;

    public SysUploadFilePo getFile() {
        return file;
    }

    public void setFile(SysUploadFilePo file) {
        this.file = file;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}