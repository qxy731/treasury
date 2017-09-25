package com.soule.data.tools;

import java.util.List;
import java.util.Map;

import com.soule.comm.file.IValueHandler;
import com.soule.comm.file.config.Field;

public class SubStrPrefixValueHandler implements IValueHandler {
	
	@Override
	public Object special(@SuppressWarnings("rawtypes") Map context, List<Field> list, String[] values, int i)  {
	   try{
		   String value = values[i];
		   String columnSeparator = list.get(i).getParam();
		   value = value.replaceAll("(", "").replaceAll(")", "").replaceAll("（", "").replaceAll("）", "");
		   String[] ret = value.split(columnSeparator);
		   return ret[0];
		}catch(Exception e){
			
		}
        return null;
	}
	
}
