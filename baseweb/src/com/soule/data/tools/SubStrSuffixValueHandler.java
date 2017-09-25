package com.soule.data.tools;

import java.util.List;
import java.util.Map;

import com.soule.comm.file.IValueHandler;
import com.soule.comm.file.config.Field;

public class SubStrSuffixValueHandler implements IValueHandler {
	
	@Override
	public Object special(@SuppressWarnings("rawtypes") Map context, List<Field> list, String[] values, int i)  {
        try{
			String param = list.get(i).getParam();
			String[] arrayParam = param.split("|");
			String columnSeparator = arrayParam[0];
			String columnName = arrayParam[1];
			int idx=0;
	        String value = null;
	        for (;idx < list.size(); idx ++) {
	            Field field = list.get(idx);
	            if (field.getField().equals(columnName)) {
	                value = values[idx];
	                break;
	            }
	        }
			String[] ret = value.split(columnSeparator);
			return ret[1];
		}catch(Exception e){
			
		}
        return null;
	}
	
}
