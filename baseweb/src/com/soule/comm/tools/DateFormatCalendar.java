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
    private String format;
    private SimpleDateFormat sdf;
    private Calendar calendar;
    
    private Calendar getLocalCalendar(){
    	if(calendar == null){
    		calendar = Calendar.getInstance();
    	}
    	return calendar;
    }
    
    private Date getLocalTime(){
    	return getLocalCalendar().getTime();
    }
    
	public DateFormatCalendar(String format){
		 this.format = format;
	   	 this.sdf = new SimpleDateFormat(format);
	   	 this.calendar = getLocalCalendar();
	   	 this.year = getLocalCalendar().get(Calendar.YEAR);
	   	 this.month = getLocalCalendar().get(Calendar.MONTH) + 1;
	   	 this.day = getLocalCalendar().get(Calendar.DATE);
    }
	
	public DateFormatCalendar(String date,String format) throws ParseException{
    	 this.format = format;
	   	 this.sdf = new SimpleDateFormat(format);
	   	 this.calendar = getLocalCalendar();
	   	 getLocalCalendar().setTime(sdf.parse(date));
	   	 this.year = getLocalCalendar().get(Calendar.YEAR);
	   	 this.month = getLocalCalendar().get(Calendar.MONTH) + 1;
	   	 this.day = getLocalCalendar().get(Calendar.DATE);
    }
    
    public DateFormatCalendar(Date date,String format){
    	 this.format = format;
		 this.sdf = new SimpleDateFormat(format);
		 this.calendar = getLocalCalendar();
		 getLocalCalendar().setTime(date);
		 this.year = getLocalCalendar().get(Calendar.YEAR);
		 this.month = getLocalCalendar().get(Calendar.MONTH) + 1;
		 this.day = getLocalCalendar().get(Calendar.DATE);
    }
    
   /**
	 * 传入日期，处理成月末日期  YYYYMMDD
	 * 1。年度文件。处理成年末日期  getYear()+"1231"
	 * 2。季度文件，处理成季末日期 getYear()+ "" +"30"
	 * 3。月度文件，处理成月末日期 getYear()+ getMonth()+""
	 * 4。日度文件，不需要处理
	 * @param args
	 */
    public String getDayEndDate(){
    	return sdf.format(getLocalTime());
    }
    
    public String getTenDaysEndDate(){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(getLocalTime());
		 int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		 if(day>=1 && day<=10){
			 lastDay = 10;
		 }else if(day>=11 && day<=20){
			 lastDay = 20;
		 }
		 calendar.set(Calendar.DATE,lastDay);
		 return sdf.format(calendar.getTime());
	}
	
	public String getMonthEndDate(){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(getLocalTime());
		 calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		 return sdf.format(calendar.getTime());
	}
	
	public String getSeasonEndDate() {
		Calendar calendar = Calendar.getInstance();
		if (month >= 1 && month <= 3) {
			calendar.set(year,2,31);
		}else if (month >= 4 && month <= 6) {
			calendar.set(year,5,30);
		}else if (month >= 7 && month <= 9) {
			calendar.set(year,8,30);
		}else if (month >= 10 && month <= 12) {
			calendar.set(year,11,31);
		}
		return sdf.format(calendar.getTime());
	}
	
	public String getHalfYearEndDate(){
		Calendar calendar = Calendar.getInstance();
		if (month >= 1 && month <= 6) {
			calendar.set(year,5,30);
		}
		if (month >= 7 && month <= 12) {
			calendar.set(year,11,31);
		}
		return sdf.format(calendar.getTime());
	}
	
	public String getYearEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year,11,31);
		return sdf.format(calendar.getTime());
	}
	
	public String getBusinessDate(String fileFreq){
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
			/*DateFormatCalendar dfc1 =new DateFormatCalendar("20170211",DateFormatDefine.FORMAT_YYYYMMDD_01);
			System.out.println(dfc1.getDayEndDate());
			System.out.println(dfc1.getTenDaysEndDate());
			System.out.println(dfc1.getMonthEndDate());
			System.out.println(dfc1.getSeasonEndDate());
			System.out.println(dfc1.getHalfYearEndDate());
			System.out.println(dfc1.getYearEndDate());*/
			DateFormatCalendar dfc2 =new DateFormatCalendar("2017-12-25",DateFormatDefine.FORMAT_YYYYMMDD_02);
			/*System.out.println(dfc2.getDayEndDate());
			System.out.println(dfc2.getTenDaysEndDate());
			System.out.println(dfc2.getMonthEndDate());
			System.out.println(dfc2.getSeasonEndDate());
			System.out.println(dfc2.getHalfYearEndDate());
			System.out.println(dfc2.getYearEndDate());*/
			
			
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(dfc2.getLocalTime());
			 calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			 //SimpleDateFormat sdf = new SimpleDateFormat(dfc2.format);
			 System.out.println(dfc2.sdf.format(calendar.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
