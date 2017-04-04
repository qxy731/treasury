package com.soule.app.table;

import java.io.Serializable;

/**
 * 对应表SYS_UPLOAD_FILE的类
 */
public class SysUploadFilePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上传编码
     */
    private String uploadId;
    /**
     * 文件编码
     */
    private String fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Integer fileSize;
    /**
     * 服务器文件路径
     */
    private String filePath;
    /**
     * 删除标志
     */
    private String delFlag;
    /**
     * 下载次数
     */
    private Integer downNum;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private java.util.Date createTime;

    /**
     * @return 上传编码
     */
    public String getUploadId () {
        return uploadId;
    }

    /**
     * @param uploadId 上传编码
     */
    public void setUploadId (String uploadId) {
        this.uploadId = uploadId;
    }
    /**
     * @return 文件编码
     */
    public String getFileId () {
        return fileId;
    }

    /**
     * @param fileId 文件编码
     */
    public void setFileId (String fileId) {
        this.fileId = fileId;
    }
    /**
     * @return 文件名称
     */
    public String getFileName () {
        return fileName;
    }

    /**
     * @param fileName 文件名称
     */
    public void setFileName (String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return 文件大小
     */
    public Integer getFileSize () {
        return fileSize;
    }

    /**
     * @param fileSize 文件大小
     */
    public void setFileSize (Integer fileSize) {
        this.fileSize = fileSize;
    }
    /**
     * @return 服务器文件路径
     */
    public String getFilePath () {
        return filePath;
    }

    /**
     * @param filePath 服务器文件路径
     */
    public void setFilePath (String filePath) {
        this.filePath = filePath;
    }
    /**
     * @return 删除标志
     */
    public String getDelFlag () {
        return delFlag;
    }

    /**
     * @param delFlag 删除标志
     */
    public void setDelFlag (String delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * @return 下载次数
     */
    public Integer getDownNum () {
        return downNum;
    }

    /**
     * @param downNum 下载次数
     */
    public void setDownNum (Integer downNum) {
        this.downNum = downNum;
    }
    /**
     * @return 创建人
     */
    public String getCreateUser () {
        return createUser;
    }

    /**
     * @param createUser 创建人
     */
    public void setCreateUser (String createUser) {
        this.createUser = createUser;
    }
    /**
     * @return 创建时间
     */
    public java.util.Date getCreateTime () {
        return createTime;
    }

    /**
     * @param createTime 创建时间
     */
    public void setCreateTime (java.util.Date createTime) {
        this.createTime = createTime;
    }

}