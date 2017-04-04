package com.soule.app.sys.filetransfer;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数上传下载:上传文件删除
 */
public class FileTransferDeleteIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private FileTransferFilePo delete;

    public FileTransferFilePo getDelete() {
        return delete;
    }

    public void setDelete(FileTransferFilePo input) {
        this.delete = input;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}