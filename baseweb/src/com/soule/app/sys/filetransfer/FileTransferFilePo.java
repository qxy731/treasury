package com.soule.app.sys.filetransfer;

import java.io.Serializable;

/**
 * 参数传递上传文件列表的类
 */
public class FileTransferFilePo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String uploadId;
    private String fileId;
    private String fileName;
    private Integer fileSize;
    private String filePath;
    private String delFlag;
    private String createUser;
    private java.util.Date createTime;
    private String createUserName;

    /**
     * @return 上传编码
     */
    public String getUploadId() {
        return uploadId;
    }

    /**
     * @param uploadId 上传编码
     */
    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
    /**
     * @return 文件编码
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId 文件编码
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    /**
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return 文件大小
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize 文件大小
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
    /**
     * @return 服务器文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath 服务器文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * @return 删除标志
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag 删除标志
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * @return 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    /**
     * @return 创建时间
     */
    public java.util.Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @return 创建人
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * @param createUserName 创建人
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}