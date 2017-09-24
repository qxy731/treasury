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
    private String fileType;
    private String resultType;
    private String createOrg;

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
     * 数据日期
     */
    private String businessDate;
    
    /**
     * 后续改进方向：
     * uploadNo和batchId两个字段，以满足需求：
     * 1：一份数据：一个文件一次上传。(一个fileId[程序处理],一个uploadNo,一个batchId)
     * 2：一份数据：多个文件一次上传。(一个fileId[程序处理],一个uploadNo,多个batchId)
     * 3：一份数据：多个文件多次上传。(一个fileId[程序处理],多个uploadNo,多个batchId)
     * 当前系统已实现
     * 1：多个文件一次上传。(一个fileId,一个uploadNo,多个batchId)
     * 2：多个文件多次上传。(多个fileId,多个uploadNo,多个batchId)
     * 同一个fileId[程序处理]说明：根据sys_upload_file_mapping配置信息判断(同一fileType,只有一个fileId)
     * 
     * 保存路径：根目录/年/月/日/系统来源/文件ID/文件名
     * 根目录:系统文件配置
     * 年:YYYY
     * 月:MM
     * 日:DD
     * 系统来源:等于sys_upload_file_mapping表的FILE_SOURCE,如果没有默认为other
     * 文件ID:fileId,一个batchId,不创建此目录;如果有多个batchId,创建此目录
     * 文件名:uploadId
     */
    
    /**
     * 上传编号
     */
    private String uploadNo;
    
    /**
     * 文件数据批次号
     *  
     */
    private String batchId;
    
    /**
     * 导入形式：1-覆盖，2-追加
     */
    private String importType;
    
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

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}   
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public String getUploadNo() {
		return uploadNo;
	}

	public void setUploadNo(String uploadNo) {
		this.uploadNo = uploadNo;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

}