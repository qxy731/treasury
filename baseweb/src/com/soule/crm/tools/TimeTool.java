package com.soule.crm.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

public class TimeTool {
	
	/**
     * 获取 java.util.Date
     * 字符串转时间
     * @return
     */
    public static java.util.Date paserDate(String datetime) {	    		    	
    	DateFormat df = DateFormat.getDateInstance();
    	try {
    		return  df.parse(datetime);
		} catch (ParseException e) {				
			return null;
		}	        
    }
    
    /**
     * 转换读取的EXCEL表中时间格式
     * @param forMatDate
     * @return
     */
    public static Date timeFormat(String forMatDate){
        if (StringUtils.isBlank(forMatDate)){
            return null;
        }
        Date forMatDates = null;
        if (forMatDate.split("[-]").length > 1){
            forMatDates = TimeTool.paserDate(forMatDate.trim(), "yyyy-MM-dd");
        }else {
            String[] s1 = forMatDate.split("/");
            if (s1[0].length() != 4){
                forMatDates = TimeTool.paserDate(forMatDate.trim(), "dd/MM/yyyy");
            }else{
                forMatDates = TimeTool.paserDate(forMatDate.trim(), "yyyy/MM/dd");
            }
        }
        return forMatDates;
   }

    
    public static Date paserDate(String datetime, String partten){
    	try {
	    	SimpleDateFormat sdf = new SimpleDateFormat(partten);
	        Date d = sdf.parse(datetime);
	        return d;
    	} catch (ParseException e){	
			return null;
		}
    }
    
    /**
     * 时间型字符串转成partten形式的字符串
     * @param datetime
     * @param partten
     * @return
     */
    public static String paserString(String datetime,String partten) {	    		    	
    	try {
    		DateFormat df = DateFormat.getDateInstance();
    		SimpleDateFormat sdf = new SimpleDateFormat(partten);
 	        String dateStr = sdf.format(df.parse(datetime));//指标日期
    		return  dateStr;
		} catch (ParseException e){	
			return null;
		}
    }
    
    public static Date paserDate(Date date,String partten) {	    		    	
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(partten);
 	        Date date1 = sdf.parse(sdf.format(date));
    		return  date1;
		} catch (ParseException e){	
			return null;
		}
    }
    
    public static String paserString(Date date,String partten){
    	if(date == null){
    		return "";
    	}
		SimpleDateFormat sdf = new SimpleDateFormat(partten);
		return sdf.format(date);
    }
    
    public static int dateDiff(Date startDate,Date endDate){
    	long times = endDate.getTime()-startDate.getTime();
    	int dnum = (int)(times/1000/60/60/24);
    	return dnum;
    }
    
    public static Date getAllocateEndDate(){
    	DateFormat df = DateFormat.getDateInstance();
    	try {
    		return  df.parse("9999-12-31");
		} catch (ParseException e) {				
			return null;
		} 
    }
    public static String getNearbyMonth(String month,int i) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");  
        Date   mydate=formatter.parse(month);
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(mydate);
        calendar.add(Calendar.MONTH ,i);
        return  formatter.format(calendar.getTime());
        
	}
    
    /**
     * 取末年某月的最后一天
     * @param year 
     * @param month - 1
     * @return
     */
    public static int getLastDayOfMonth(String date){
		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.MONTH, 1);
		cal.setTime(paserDate(date));
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
    /**
     * 时间转时间
     * 
     */
    
    
    /**
     * 时间转字符串
     */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
