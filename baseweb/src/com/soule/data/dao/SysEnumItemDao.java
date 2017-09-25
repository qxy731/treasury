package com.soule.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soule.comm.db.DatabaseHelper;
import com.soule.data.bean.SysEnumItem;

public class SysEnumItemDao{
	
	public Map<String,String> querySysEnumItem(){
		DatabaseHelper helper = new DatabaseHelper();
		StringBuffer sql= new StringBuffer("select ");
		sql.append("A.ENUM_ID as enumId,");
		sql.append("A.ITEM_ID as itemId,");
		sql.append("A.ITEM_VALUE as itemValue,");
		sql.append("A.ITEM_DESC as itemDesc,");
		sql.append("A.SEQ_ID as seqId");
		sql.append(" from sys_enum_item A ");
		sql.append(" where ENUM_ID='uploadfile_type' ");
		sql.append(" order by A.SEQ_ID");
		Map<String,String> map = new HashMap<String,String>();
		List<SysEnumItem> enumItemList = helper.getRecords(sql.toString(),SysEnumItem.class);
		if(enumItemList!=null&&!enumItemList.isEmpty()){
			for(int i=0;i<enumItemList.size();i++){
				SysEnumItem sysEnumItem = enumItemList.get(i);
				map.put(sysEnumItem.getEnumId(), sysEnumItem.getItemDesc());
			}
		}
		return map;
	}

}
