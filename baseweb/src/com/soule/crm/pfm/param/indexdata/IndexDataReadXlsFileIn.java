package com.soule.crm.pfm.param.indexdata;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输入参数对象指标数据补录:解析上传文件
 */
public class IndexDataReadXlsFileIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private java.util.Date recoreDate;
    private String objectType;
    private String uploadId;
    private String fileId;


    public java.util.Date getRecoreDate() {
        return recoreDate;
    }

    public void setRecoreDate(java.util.Date recoreDate) {
        this.recoreDate= recoreDate;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType= objectType;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId= uploadId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId= fileId;
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}