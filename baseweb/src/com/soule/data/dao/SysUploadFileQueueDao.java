package com.soule.data.dao;

import com.soule.comm.db.DatabaseHelper;
import com.soule.data.bean.SysUploadFileQueue;

public class SysUploadFileQueueDao {
	
	public int queryUploadFileQueueCount(){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer("select count(1) from sys_upload_file_queue");
		return helper.getRecordCount(sql.toString());
	}
	
	public SysUploadFileQueue getSysUploadFileQueue(){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer("select ");
		sql.append("QUEUE_ID as queueId,");
		sql.append("UPLOAD_ID as uploadId,");
		sql.append("IN_QUEUE_TIME as inQueueTime");
		sql.append(" from sys_upload_file_queue");
		sql.append(" order by IN_QUEUE_TIME limit 1");
		System.out.println(sql.toString());
		return helper.getOneRecord(sql.toString(), SysUploadFileQueue.class);
	}
	
	public int deleteSysUploadFileQueue(String queueId){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer("delete from sys_upload_file_queue");
		sql.append(" where QUEUE_ID = '").append(queueId).append("'");
		System.out.println(sql.toString());
		return helper.executeUpdate(sql.toString());
	}
	
}
