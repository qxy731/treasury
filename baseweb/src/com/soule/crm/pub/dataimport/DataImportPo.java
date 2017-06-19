package com.soule.crm.pub.dataimport;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

public class DataImportPo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orgCode;
    private String orgName;
    private String uploadId;
    private String fileId;
    private String fileType;
    private String fileName;
    private String fileSize;
    private String staffId;
    private String staffName;
    private String resultType;
    private java.util.Date uploadDate;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public java.util.Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(java.util.Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
