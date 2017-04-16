package com.dev4dan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Process4Date{
    private static Logger logger = LoggerFactory.getLogger(Process4Date.class);
	
	/**
	 * 日期格式：小时-分钟-秒
	 */
	private static String HH_MM_SS = "HH:mm:ss";
	
	/**
	 * 在mainmenu页面显示的当前日期的模板格式
	 */
	private static final String MAINMENU_DATE_FORMAT = "EEE, dd MMM yyyy";
	
	/**
	 * 日期格式：日-月-年 小时-分钟-秒
	 */
	private static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm:ss";
	
	/**
	 * 日期格式：日-月-年
	 */
	private static final String DATE_FORMAT = "dd MMM yyyy";
	
	/**
	 * 
	 */
	private static SimpleDateFormat DIS_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT,
			Locale.ENGLISH);
	
	/**
	 * 
	 */
	private static final SimpleDateFormat DIS_DATE_TIME_FORMAT = new SimpleDateFormat(
			DATE_TIME_FORMAT, Locale.ENGLISH);
	
	private Process4Date(){}
	
	private final static ThreadLocal<Process4Date> instance = new ThreadLocal<Process4Date>(){
		@Override
		protected Process4Date initialValue() {
			return new Process4Date();
		}
	};
	
	public static Process4Date getInstance(){
		return instance.get();
	}
	
//	private final ThreadLocal<SimpleDateFormat> YYYYMMDD = new ThreadLocal<SimpleDateFormat>(){
//		@Override
//		protected SimpleDateFormat initialValue() {
//			return new SimpleDateFormat("yyyyMMdd");
//		}
//	};
	
	private SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd"); 
	
	/**
	 * 将指定的字符串转换成日期
	 * 
	 * @param dateStr
	 *            : 待转换的日期符串,以yyyy-MM-dd模板进行转换
	 * @return 返回标准的日期格式yyyy-MM-dd,与字符串dateStr对应的date对象
	 * @throws ParseStringException
	 */
	public Date parseStrToDate(String dateStr) throws ParseException {
		try {
			SimpleDateFormat dateFormat = YYYYMMDD;
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw e;
		}
	}		
	
	/**
	 * 返回该日期在一年中的索引
	 * 
	 * @param date
	 * @return
	 */
	public int getDay(String date) {
		try {
			Calendar cNow = Calendar.getInstance();
			SimpleDateFormat dateFormat = YYYYMMDD;
			cNow.setTime(dateFormat.parse(date));
			int nowDay = cNow.get(Calendar.DAY_OF_YEAR);
			return nowDay;
		} catch (Exception e) {
			logger.error("getDay error.", e);
			return 0;
		}
	}	
	
	/**
	 * 返回小时
	 * 
	 * @param date
	 * @return
	 */
	public int getHour(String date) {
		try {
			SimpleDateFormat timeFormat = new SimpleDateFormat(HH_MM_SS);
			Date d1 = timeFormat.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d1);
			return c.get(Calendar.HOUR_OF_DAY);
		} catch (Exception e) {
			logger.error("getHour error", e);
			return 0;
		}
	}
	
	public int getDay(Date date) {
		try {
			SimpleDateFormat dateFormat = YYYYMMDD;
			String dString = dateFormat.format(date);
			return getDay(dString);
		} catch (Exception e) {
			logger.error("getDay error", e);
			return 0;
		}
	}
	
	public SimpleDateFormat getDIS_DATE_FORMAT(){
		return DIS_DATE_FORMAT;
	}
	
	public SimpleDateFormat getDIS_DATE_TIME_FORMAT (){
		return DIS_DATE_TIME_FORMAT;
	} 
	
}