package org.agcs.system.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @文件名:DateUtil.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2014-10-30
 * @描述:
 * @版本:V 1.0
 */
public class DateUtil {
	/**
	 * 将日期转化为字符串格式为"yyyy-MM-dd"
	 * 
	 * @param date
	 * @return String
	 */
	public static String date2String(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 将日期转化为指定格式字符串
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String date2String(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {

		return new Date(System.currentTimeMillis());
	}

	
	/**
	 * 获取系统几天前前时间(正数为day天后，负数为day天前)
	 * 
	 * @param day
	 * @return Date
	 */
	public static Date getAppointDate(int day){
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        Date date = calendar.getTime();
        return date;
	}
	
	
	/**
	 * 获取系统几月前前日期(正数为month天前，负数为month天后)
	 * 
	 * @param day
	 * @return Date
	 */
	public static Date getMonthDate(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 获取系统几天前前时间(正数为day天前，负数为day天后)
	 * 
	 * @param day
	 * @return Date
	 */
	public static Date getAppointDateByHour(int hour) {

		return new Date(System.currentTimeMillis() - hour * 3600 * 1000);

	}

	/**
	 * 将日期转化为字符串格式为"yyyy-MM-dd"
	 * 
	 * @param date
	 * @return String
	 */
	public static Date string2Date(String str) {
		if (str.equals(""))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将日期转化为字符串格式为format
	 * 
	 * @param str
	 * @param format
	 * @return String
	 */
	public static Date string2Date(String str, String format) {
		if (str.equals(""))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取id
	 * 
	 * @param args
	 * @return String
	 */
	public static String grantId() {
		Date date = new Date();
		String id = "s" + date2String(date, "yyyyMMddHHmmss");
		return id;
	}

	/**
	 * 
	 * @param args
	 * @return String
	 */
	public static String grantString() {
		Date date = new Date();
		return date2String(date, "yyyyMMddHHmmssSSS");
	}
	
	/**
     * 获取输入的当前时间
     * @return
     */
    public static String todaytime(Long time,String dateformat) {  
        Date now = new Date(time); 
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式  
        String hehe = dateFormat.format(now);  
        return hehe;  
    }  
    
    public static String tomorrowtime(Long time,String dateformat) throws ParseException {  
    	SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
   	    Calendar cal = Calendar.getInstance();  
   	    cal.setTime(dateFormat.parse(todaytime(time, dateformat)));  
   	    cal.add(Calendar.DAY_OF_YEAR, +1);  
   	    String nextDate_1 = dateFormat.format(cal.getTime()); 
   	    return nextDate_1;
    }
    
    /** 
     * 两个时间之间的天数 
     *  
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static long getDays(String date1, String date2) {  
        if (date1 == null || date1.equals(""))  
            return 0;  
        if (date2 == null || date2.equals(""))  
            return 0;  
        // 转换为标准时间  
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
        java.util.Date date = null;  
        java.util.Date mydate = null;  
        try {  
            date = myFormatter.parse(date1);  
            mydate = myFormatter.parse(date2);  
        } catch (Exception e) {  
        }  
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);  
        return day;  
    }
	
	public static void main(String[] args) {
		System.out.println(DateUtil.date2String(getMonthDate(-1), "yyyyMMddHHmmss"));
	}
}
