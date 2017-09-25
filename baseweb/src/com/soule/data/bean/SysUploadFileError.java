package com.soule.data.bean;

import java.io.Serializable;
import java.util.Date;


public class SysUploadFileError implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String errorId;
    private String uploadId;
    private Long totalNumber;
    private Long successNumber;
    private Long failureNumber;
    private Date createTime;
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	
	public Long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}
	public Long getSuccessNumber() {
		return successNumber;
	}
	public void setSuccessNumber(Long successNumber) {
		this.successNumber = successNumber;
	}
	public Long getFailureNumber() {
		return failureNumber;
	}
	public void setFailureNumber(Long failureNumber) {
		this.failureNumber = failureNumber;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}