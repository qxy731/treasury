package com.soule.comm.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.soule.comm.CommConstants;


public class DateFormatCalendar{
	
	/** 
	  * 
	  * 描述:此类用于取得当前日期相对应的月初，月末，季初，季末，年初，年末，返回值均为String字符串 
	  *      1、得到当前日期         today() 
	  *      2、得到当前月份月初      thisMonth() 
	  *      3、得到当前月份月底      thisMonthEnd() 
	  *      4、得到当前季度季初      thisSeason() 
	  *      5、得到当前季度季末      thisSeasonEnd() 
	  *      6、得到当前年份年初      thisYear() 
	  *      7、得到当前年份年底      thisYearEnd() 
	  *      8、判断输入年份是否为闰年 leapYear   
	  * 注意事项:  日期格式为：xxxxyyzz (eg: 20071205)
	  * 实例: 
	  * @author peter
	  */
	
	private Integer year;                  // 日期属性：年  
    private Integer month;                  // 日期属性：月  
	private Integer day;                  // 日期属性：日  
    private String format = DateFormatDefine.FORMAT_YYYYMMDD_01;
    private SimpleDateFormat sdf;
    private Calendar calendar;
    private static DateFormatCalendar manager;
    
    public static Calendar getLocalCalendar(){
    	return manager.calendar;
    }
    
    public static String getLocalTime(){
    	return manager.sdf.format(getLocalCalendar().getTime());
    }
    
	public static DateFormatCalendar getInstance(){
		if (manager == null) {
			 manager = new DateFormatCalendar();
        }
    	return getInstance(Calendar.getInstance().getTime(),manager.format);
    }
	
	public static DateFormatCalendar getInstance(String date) throws ParseException{
		if (manager == null) {
			 manager = new DateFormatCalendar();
        }
		manager.sdf = new SimpleDateFormat(manager.format);
    	return getInstance(manager.sdf.parse(date),manager.format);
    }
    
    public static DateFormatCalendar getInstance(Date date){
    	if (manager == null) {
			 manager = new DateFormatCalendar();
        }
    	return getInstance(date,manager.format);
    }
    
    public static DateFormatCalendar getInstance(String date,String format) throws ParseException{
    	if (manager == null) {
			 manager = new DateFormatCalendar();
        }
    	 manager.sdf = new SimpleDateFormat(manager.format);
    	 return getInstance(manager.sdf.parse(date),format);
    }
    
    public static DateFormatCalendar getInstance(Date date,String format){
    	 if (manager == null) {
			 manager = new DateFormatCalendar();
         }
    	 manager.format = format;
    	 manager.sdf = new SimpleDateFormat(format);
    	 if(manager.calendar==null){
     		manager.calendar = Calendar.getInstance();
     	 }
    	 getLocalCalendar().setTime(date);
    	 manager.year = getLocalCalendar().get(Calendar.YEAR);
    	 manager.month = getLocalCalendar().get(Calendar.MONTH) + 1;
    	 manager.day = getLocalCalendar().get(Calendar.DATE);
         return manager;
    }
	
	/**
	 * 传入日期，处理成月末日期  YYYYMMDD
	 * 1。年度文件。处理成年末日期  getYear()+"1231"
	 * 2。季度文件，处理成季末日期 getYear()+ "" +"30"
	 * 3。月度文件，处理成月末日期 getYear()+ getMonth()+""
	 * 4。日度文件，不需要处理
	 * @param args
	 */
    public static String getDayEndDate(){
    	return manager.sdf.format(getLocalCalendar().getTime());
    }
    
    public static String getTenDaysEndDate(){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(getLocalCalendar().getTime());
		 int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		 if(manager.day>=1 && manager.day<=10){
			 lastDay = 10;
		 }else if(manager.day>=11 && manager.day<=20){
			 lastDay = 20;
		 }
		 calendar.set(Calendar.DATE,lastDay);
		 return manager.sdf.format(calendar.getTime());
	}
	
	public static String getMonthEndDate(){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(getLocalCalendar().getTime());
		 calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		 return manager.sdf.format(calendar.getTime());
	}
	
	public static String getSeasonEndDate() {
		Calendar calendar = Calendar.getInstance();
		if (manager.month >= 1 && manager.month <= 3) {
			calendar.set(manager.year,2,31);
		}else if (manager.month >= 4 && manager.month <= 6) {
			calendar.set(manager.year,5,30);
		}else if (manager.month >= 7 && manager.month <= 9) {
			calendar.set(manager.year,8,30);
		}else if (manager.month >= 10 && manager.month <= 12) {
			calendar.set(manager.year,11,31);
		}
		return manager.sdf.format(calendar.getTime());
	}
	
	public static String getHalfYearEndDate(){
		Calendar calendar = Calendar.getInstance();
		if (manager.month >= 1 && manager.month <= 6) {
			calendar.set(manager.year,5,30);
		}
		if (manager.month >= 7 && manager.month <= 12) {
			calendar.set(manager.year,11,31);
		}
		return manager.sdf.format(calendar.getTime());
	}
	
	public static String getYearEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(manager.year,11,31);
		return manager.sdf.format(calendar.getTime());
	}
	
	public static String getBusinessDate(String fileFreq){
		if(CommConstants.DATA_FILE_FREQUENCY_TDE.equals(fileFreq)){
			return getTenDaysEndDate();
		}else if(CommConstants.DATA_FILE_FREQUENCY_ME.equals(fileFreq)){
			return getMonthEndDate();
		}else if(CommConstants.DATA_FILE_FREQUENCY_QE.equals(fileFreq)){
			return getSeasonEndDate();
		}else if(CommConstants.DATA_FILE_FREQUENCY_HYE.equals(fileFreq)){
			return getHalfYearEndDate();
		}else if(CommConstants.DATA_FILE_FREQUENCY_YE.equals(fileFreq)){
			return getYearEndDate();
		}else{
			return getDayEndDate();
		}
	}
	
	
	public static void main(String[] args){
		try {
			DateFormatCalendar.getInstance("20170211");
			System.out.println(DateFormatCalendar.getTenDaysEndDate());
			System.out.println(DateFormatCalendar.getMonthEndDate());
			System.out.println(DateFormatCalendar.getSeasonEndDate());
			System.out.println(DateFormatCalendar.getHalfYearEndDate());
			System.out.println(DateFormatCalendar.getYearEndDate());
			System.out.println(DateFormatCalendar.getDayEndDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
