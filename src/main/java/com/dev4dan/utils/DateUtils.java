package com.dev4dan.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final String format_yyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";

	public static final String format_yyMMdd = "yyyy-MM-dd";
	
	/**
	 * 获取日期
	 * @param day 日期偏离值，0为当天，负数为已过去的日期
	 * @return
	 */
	public static String getDate(long day) {
		String dateString = "";
		Date d=new Date();   
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
	    dateString = df.format(new Date(d.getTime() + day * 24 * 60 * 60 * 1000));
		
		return dateString;
	}
	
	/**
	 * 获取当前日期
	 * @param dateFormat : 日期格式 YYYY-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDate(String dateFormat){
		String dateString = "";
		Date d=new Date();   
	    SimpleDateFormat df=new SimpleDateFormat(dateFormat); 
	    dateString = df.format(new Date(d.getTime()));
	    
	    return dateString;
	}
	
	/**
	 * 获取指定日期
	 * @param dateFormat : 日期格式 YYYY-MM-dd HH:mm:ss
	 * @param times : Long 时间偏移值
	 * @return
	 */
	public static String getDate(String dateFormat, Long times){
		String dateString = "";
	    SimpleDateFormat df=new SimpleDateFormat(dateFormat); 
	    dateString = df.format(new Date(times));
	    
	    return dateString;
	}
	
	public static Date getDateYYMMDD(String dateString) {
		SimpleDateFormat df = new SimpleDateFormat(format_yyMMdd);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通过某一日期获取指定日期
	 * @param dateString : 指定日期
	 * @param days : 偏移值
	 * @param dateFormat : 日期格式 YYYY-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateAdd(String dateString,long days,String dateFormat){
	    SimpleDateFormat df=new SimpleDateFormat(dateFormat); 
		try {
			Date d = df.parse(dateString);
			dateString = df.format(new Date(d.getTime() + days * 24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

	    return dateString;
	}
	
	/**
	 * 通过指定日期格式，将字符串转换成指定日期的字符串
	 * @param dateFormat 日期格式
	 * @param dateString 日期
	 * @return
	 */
	public static Date getDate(String dateFormat, String dateString){
	    SimpleDateFormat df=new SimpleDateFormat(dateFormat); 
	    try {
			return df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    return null;
	}
	
	/**
	 * 通过指定日期格式，延后毫秒数，最后将当前时间加上延后毫秒数转成指定日期格式的string
	 * @param dateFormat
	 * @param extraTime
	 * @return
	 */
	public static String getDateByAddMillis(String dateFormat, long extraTime){
		String dateString = "";
		Date d=new Date();   
	    SimpleDateFormat df=new SimpleDateFormat(dateFormat); 
	    dateString = df.format(new Date(d.getTime() + extraTime));
	    
	    return dateString;
	}
	
	/**
	 * 获取两个日期差值
	 * @param beginDate 起始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public static long betweenDate(String beginDate,String endDate){
		long between = 0;
		long days = 0;
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date begin = dfs.parse(beginDate);
			Date end = dfs.parse(endDate);
			between = (end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒  
			days = between/(24*3600) + 1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	}
	
	/**
	 * 比较两个时间大小
	 * @param beginTime 起始时间
	 * @param endTime 结束时间
	 * @return 两个时间相等返回0，起始时间大于结束时间返回1，起始时间小于结束时间返回-1
	 */
	public static int compareTime(String beginTime,String endTime){
		int times = -2;
		DateFormat df=new SimpleDateFormat("HH:mm:ss");
		Calendar t1=Calendar.getInstance();
		Calendar t2=Calendar.getInstance();
		
		try{
			t1.setTime(df.parse(beginTime));
			t2.setTime(df.parse(endTime));
			times = t1.compareTo(t2);
		}catch(ParseException e){
			times = -3;
		}
		
		return times;
	}
	
	
	/**
	 * 返回当前日期是周几并转换为相对数值
	 * @param dt Date
	 * @return int
	 */
	public static int getWeekOfDate(Date dt) {
		 String[] weekDays = {"0000001", "1000000", "0100000", "0010000", "0001000", "0000100", "0000010"};
	     Calendar cal = Calendar.getInstance();
	     cal.setTime(dt);
	     int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	     if (w < 0)
	    	 w = 0;
	     return Integer.parseInt(weekDays[w],2);
	}
	
	public static void main(String[] args){
		System.out.println(getDateYYMMDD("2012-01-01"));
	}
	
}