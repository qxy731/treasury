package com.soule.data.dao;

import com.soule.comm.db.DatabaseHelper;

public class BatTableDao {
	
	public void deleteCurrentBatData(String table,String date,String batchId){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ");
		sql.append(table);
		sql.append(" where TAR_DATE='");
		sql.append(date);
		sql.append("' and BATCH_ID='");
		sql.append(batchId);
		sql.append("'");
		System.out.println(sql.toString());
		helper.execute(sql.toString());		
	}
	
	public int getSuccessTotalCount(String table,String date,String batchId){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ");
		sql.append(table);
		sql.append(" where TAR_DATE='");
		sql.append(date);
		sql.append("' and BATCH_ID='");
		sql.append(batchId);
		sql.append("'");
		return helper.getRecordCount(sql.toString());		
	}

}
