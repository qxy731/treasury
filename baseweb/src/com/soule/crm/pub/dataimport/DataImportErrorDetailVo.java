package com.soule.crm.pub.dataimport;

public class DataImportErrorDetailVo{
    private long totalNumber;
    private long successNumber;
    private long failureNumber;
    private String uploadId;
    private String errorId;
    private String fileName;
    private String fileType;
	public long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}
	public long getSuccessNumber() {
		return successNumber;
	}
	public void setSuccessNumber(long successNumber) {
		this.successNumber = successNumber;
	}
	public long getFailureNumber() {
		return failureNumber;
	}
	public void setFailureNumber(long failureNumber) {
		this.failureNumber = failureNumber;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
