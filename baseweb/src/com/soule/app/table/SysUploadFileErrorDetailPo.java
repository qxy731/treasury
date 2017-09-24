package com.soule.app.table;

import java.io.Serializable;
import java.util.Date;


public class SysUploadFileErrorDetailPo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String detailId;
    private String errorId;
    private Long errorRow;
    private Integer errorColumn;
    private String errorMessage;
    private Date createTime;
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public Long getErrorRow() {
		return errorRow;
	}
	public void setErrorRow(Long errorRow) {
		this.errorRow = errorRow;
	}
	public Integer getErrorColumn() {
		return errorColumn;
	}
	public void setErrorColumn(Integer errorColumn) {
		this.errorColumn = errorColumn;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
   
}