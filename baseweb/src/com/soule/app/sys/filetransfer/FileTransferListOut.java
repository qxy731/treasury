package com.soule.app.sys.filetransfer;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

/**
 * 输出参数上传下载:上传文件列表
 */
public class FileTransferListOut implements Serializable, IServiceResult {

    private static final long serialVersionUID = 1L;

    private ServiceResult head = new ServiceResult();
    private List<FileTransferFilePo> file;


    public List<FileTransferFilePo> getFile() {
        return file;
    }

    public void setFile(List<FileTransferFilePo> output) {
        this.file = output;
    }

    public ServiceResult getResultHead() {
        return head;
    }

    public void setResultHead(ServiceResult head) {
        this.head = head;
    }

}