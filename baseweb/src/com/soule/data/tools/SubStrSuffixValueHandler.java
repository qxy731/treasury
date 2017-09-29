package com.soule.data.tools;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.soule.comm.file.IValueHandler;
import com.soule.comm.file.config.Field;

public class SubStrSuffixValueHandler implements IValueHandler {
	
	@Override
	public Object special(@SuppressWarnings("rawtypes") Map context, List<Field> list, String[] values, int i)  {
        try{
			String param = list.get(i).getParam();
			String[] arrayParam = param.split("\\|");
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
	        if(StringUtils.isBlank(value)){
				   return null;
			}
			String[] ret = value.split(columnSeparator);
			if(ret.length==1){
				return ret[0];
		    }else{
			   return ret[1];
		    }
		}catch(Exception e){
			//e.printStackTrace();
		}
        return null;
	}
	
}
