package com.soule.data.tools;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.soule.comm.file.IValueHandler;
import com.soule.comm.file.config.Field;

public class SubStrPrefixValueHandler implements IValueHandler {
	
	@Override
	public Object special(@SuppressWarnings("rawtypes") Map context, List<Field> list, String[] values, int i)  {
	   try{
		   String value = values[i];
		   if(StringUtils.isBlank(value)){
			   return null;
		   }
		   String columnSeparator = list.get(i).getParam();
		   value = value.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("（", "").replaceAll("）", "");
		   String[] ret = value.split(columnSeparator);
		   /*if(ret.length==1){
			   return null;
		   }else{
			   return ret[0];
		   }*/
		   return ret[0];
		}catch(Exception e){
			//e.printStackTrace();
		}
        return null;
	}
	
	public static void main(String[] args){
		String value="23452354";
		String[] ret = value.split(",");
		System.out.println(ret[0]);
	}
	
}
