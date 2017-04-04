package com.soule.app.sys.filetransfer;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数上传下载:上传文件
 */
public class FileTransferUploadIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private com.soule.app.table.SysUploadFilePo file;

    public com.soule.app.table.SysUploadFilePo getFile() {
        return file;
    }

    public void setFile(com.soule.app.table.SysUploadFilePo input) {
        this.file = input;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}