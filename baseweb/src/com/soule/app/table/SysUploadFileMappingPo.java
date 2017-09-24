package com.soule.app.table;

import java.io.Serializable;
import java.util.Date;


public class SysUploadFileMappingPo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String filePk;
    private String fileSource;
    private String fileType;
    private String fileName;
    private String fileFreq;
    private String fileTemplate;
    private String fileTable;
    private int fileNum;
    private int importNum;
    private String importStatus;
    private Date lastUpdTime;
    
	public String getFilePk() {
		return filePk;
	}
	public void setFilePk(String filePk) {
		this.filePk = filePk;
	}
	public String getFileSource() {
		return fileSource;
	}
	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
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
	public String getFileFreq() {
		return fileFreq;
	}
	public void setFileFreq(String fileFreq) {
		this.fileFreq = fileFreq;
	}
	
	public String getFileTemplate() {
		return fileTemplate;
	}
	public void setFileTemplate(String fileTemplate) {
		this.fileTemplate = fileTemplate;
	}
	public String getFileTable() {
		return fileTable;
	}
	public void setFileTable(String fileTable) {
		this.fileTable = fileTable;
	}
	
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public int getImportNum() {
		return importNum;
	}
	public void setImportNum(int importNum) {
		this.importNum = importNum;
	}
	public String getImportStatus() {
		return importStatus;
	}
	public void setImportStatus(String importStatus) {
		this.importStatus = importStatus;
	}
	public Date getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}    
}