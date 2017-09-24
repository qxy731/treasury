package com.soule.app.table;

import java.io.Serializable;
import java.util.Date;


public class SysUploadFileQueuePo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String queueId;
    private String uploadId;
    private Date inQueueTime;
    
	public String getQueueId() {
		return queueId;
	}
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public Date getInQueueTime() {
		return inQueueTime;
	}
	public void setInQueueTime(Date inQueueTime) {
		this.inQueueTime = inQueueTime;
	}
}