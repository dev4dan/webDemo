package com.dev4dan.utils;
import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
	
	public static final String format_yyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";

	public static final String format_yyMMdd = "yyyy-MM-dd";

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	/**
	 * 长日期型
	 */
	public static final int LONG = DateFormat.LONG;

	/**
	 * 中日期型
	 */
	public static final int MEDIUM = DateFormat.MEDIUM;

	/**
	 * 短日期型
	 */
	public static final int SHORT = DateFormat.SHORT;

	public final static String HOUR_MINUTE_FORMAT = "HH:mm";
	/**
	 * 时间格式
	 */
	public final static String TIME_FORMAT = "HH:mm:ss";

	/**
	 * 时间格式,主要是针对timestamp
	 */
	public final static String TIME_LONGFORMAT = "HH:mm:ss:SS";
	/**
	 * 缺省短日期格式
	 */
	public final static String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 缺省长日期格式
	 */

	public final static String DEFAULT_LONG_DATE_FORMAT = DEFAULT_SHORT_DATE_FORMAT + " HH:mm:ss";

	/**
	 * 缺省长日期格式,主要是针对timestamp
	 */
	public final static String DEFAULT_LONG_DATE_LONGFORMAT = DEFAULT_SHORT_DATE_FORMAT + " HH:mm:ss:SS";

	public final static String DATE_HOUR_MINUTE_FORMAT = DEFAULT_SHORT_DATE_FORMAT + " " + HOUR_MINUTE_FORMAT;

	private static Process4Date process4Date = Process4Date.getInstance();

	private static final String YYYY_MM_dd =  "yyyy-MM-dd";


	/**
	 * 计算指定日期的偏移日期
	 *
	 * @param selectDate
	 * @param offsetDay
	 * @return
	 */
	public static Date calDate(Date selectDate, int offsetDay) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(selectDate);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + offsetDay);
		return calendar.getTime();

	}

	/**
	 * 获取当前日期的当月第一天
	 *
	 * @param date
	 * @return
	 * @exception
	 */
	public static Date getFirstDayMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static int getCurrentYear(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static int getCurrentMonth(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}

	public static int getCurrentDayOfWeek(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * 获取当前日期的当月最后一天
	 *
	 * @param date
	 * @return
	 * @exception
	 */
	public static Date getLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 获取当前日期的下一天
	 *
	 * @param date
	 * @return
	 * @exception
	 */
	public static Date getNextDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, 24);
		return cal.getTime();
	}

	/**
	 * 获取当前日期的当月的所有日期
	 *
	 * @param date
	 * @return
	 * @exception
	 */
	public static List<Date> getNextDateList(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date whenDate = date;
		Date endDate = getLastDay(date);
		while (whenDate.before(endDate) || whenDate.equals(endDate)) {
			list.add(whenDate);
			whenDate = getNextDate(whenDate);
		}
		return list;
	}

	/***
	 * 转换成date类型格式
	 *
	 * @param dataStr
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dataStr) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.parse(dataStr);
	}

	public static Date stringToDate(String dataStr,String formate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(formate);
		return df.parse(dataStr);
	}

	public static Date StringTodate2(String dataStr) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.parse(dataStr);
	}

	public static Date StringTodate3(String dataStr) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(dataStr);
	}

	public static Date StringTodate4(String dataStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(dataStr);
		} catch (ParseException e) {
			logger.error("StringTodate4 error", e);
		}
		return null;
	}

	public static Date StringTodateYMD(String dataStr) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public static String datetoString2(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return df.format(date);
		}
		return "";
	}

	/**
	 * date类型转化为指定的字符串类型格式
	 *
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		}
		return "";
	}

	/**
	 * date类型转化为指定的字符串类型格式
	 *
	 * @param date
	 * @return
	 */
	public static String dateToString3(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(date);
		}
		return "";
	}

	/**
	 * date类型转化为指定的字符串类型格式
	 *
	 * @param date
	 * @return
	 */
	public static String dateToString2(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			return df.format(date);
		}
		return "";
	}

	/**
	 * date类型转化为指定的字符串类型格式
	 *
	 * @param date
	 * @return
	 */
	public static Date dateToDateYYYYMMDD(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return df.parse(df.format(date));
			} catch (ParseException e) {
				logger.error("dateToDateYYYYMMDD error", e);
			}
		}
		return null;
	}

	/**
	 * date类型转化为指定的字符串类型格式
	 *
	 * @param date
	 * @return
	 */
	public static Date dateToDateHHMMDD(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:dd");
			try {
				return df.parse(df.format(date));
			} catch (ParseException e) {
				logger.error("dateToDateHHMMDD error", e);
			}
		}
		return null;
	}

	public static String dateToStringYYYYMMDD(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(date);
		}
		return "";
	}

	public static String dateToStringYYYY(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			return df.format(date);
		}
		return "";
	}

	/**
	 * 查询2个日期是否同1天
	 *
	 * @param t
	 * @param t2
	 * @return
	 */
	public static boolean IsSameDay(Timestamp t, Timestamp t2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(t);
		int y = cal.get(Calendar.YEAR);
		int d = cal.get(Calendar.DAY_OF_YEAR);

		cal.setTime(t2);
		int y2 = cal.get(Calendar.YEAR);
		int d2 = cal.get(Calendar.DAY_OF_YEAR);

		return (y == y2) && (d == d2);
	}

	/** 获取当前时间的Timestamp值 */
	public static Timestamp NowTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 获取当前时间(注意:返回的是String 已经格式化好的格式)
	 *
	 * @return
	 */
	public static String getNowTime() {
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = formatter.format(now.getTime());
		return str;
	}

	/**
	 * 获取当前时间(注意:返回的是String 已经格式化好的格式)
	 *
	 * @return
	 */
	public static String getNowTimeFormat() {
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = formatter.format(now.getTime());
		return str;
	}

	/**
	 * 获取当前时间(注意:返回的是String 已经格式化好的格式)
	 *
	 * @return
	 */
	public static String getNowTimeFormat(String format) {
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String str = formatter.format(now.getTime());
		return str;
	}

	public static String getNowTimeFormat(Date nowDate) {
		if (nowDate == null)
			nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = formatter.format(now.getTime());
		return str;
	}

	public static long getTimeInMillis(String sDate, String eDate) {
		Timestamp sd = Timestamp.valueOf(sDate);
		Timestamp ed = Timestamp.valueOf(eDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sd);
		long timethis = calendar.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(ed);
		long timeend = calendar2.getTimeInMillis();
		long thedaymillis = timeend - timethis;
		return thedaymillis;
	}

	/**
	 * HH:mm:ss格式化的时间
	 *
	 * @param dTime
	 * @return
	 */
	public static String formatTime(String dTime) {
		String dateTime = "";
		if (dTime != null && !"".equals(dTime)) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}

	/**
	 * 强制转化为时间格式
	 *
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parses(String strDate, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(strDate);
	}

	/**
	 * 当前日期是第几周
	 *
	 * @return
	 */
	public static String getWeekOfYear() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String week = calendar.get(Calendar.WEEK_OF_YEAR) + "";
		return week;
	}

	/**
	 * 返回毫秒
	 *
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static String getTimeInMillis(Date sDate, Date eDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sDate);
		long timethis = calendar.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(eDate);
		long timeend = calendar2.getTimeInMillis();
		long thedaymillis = timeend - timethis;
		return thedaymillis < 1000 ? thedaymillis + "毫秒!" : (thedaymillis / 1000) + "秒钟!";
	}

	/**
	 * 获取第i月之后的时间
	 *
	 * @param ts
	 * @param i
	 * @return
	 */
	public static String getNextDate(String ts, int i) {
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts + " 00:00:00.000");
		now.setTime(t);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		now.add(Calendar.DAY_OF_MONTH, +(i));
		String dt = formatter.format(now.getTime());
		return dt;
	}

	/**
	 * 获取第i分钟之后的时间
	 *
	 * @param ts
	 * @param i
	 * @return
	 */
	public static String getNextTime(String ts, int i) {
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts);
		now.setTime(t);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		now.add(Calendar.MINUTE, +(i));
		String dt = formatter.format(now.getTime());
		return dt;
	}

	/***
	 * 取Unix时间戳
	 *
	 * @param dateTime
	 * @return
	 */
	public static long getUnixTime(String dateTime) {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 08:00:00");
			if(date1 != null && date2 != null){
				long l = (date1.getTime() - date2.getTime()) / 1000;
				return l;
			}
		} catch (ParseException e) {
			logger.error("getUnixTime error", e);
		}
		return -1;
	}

	/**
	 * 返回小时
	 *
	 * @param date
	 * @return
	 */
	public static int getHour(String date) {
		return process4Date.getHour(date);
	}

	public static Date addHour(Date date, int add) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR_OF_DAY, add);
			return c.getTime();
		} catch (Exception e) {
			logger.error("addHour error", e);
			return null;
		}
	}

	public static Date addMinute(Date date, int add) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MINUTE, add);
			return c.getTime();
		} catch (Exception e) {
			logger.error("addMinute error", e);
			return null;
		}
	}

	/**
	 * 返回该日期在一年中的索引
	 *
	 * @param date
	 * @return
	 */
	public static int getDay(String date) {
		try {
			Calendar cNow = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_dd);
			cNow.setTime(dateFormat.parse(date));
			int nowDay = cNow.get(Calendar.DAY_OF_YEAR);
			return nowDay;
		} catch (Exception e) {
			logger.error("getDay error.", e);
			return 0;
		}
	}

	public static int getDay(Date date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_dd);
			String dString = dateFormat.format(date);
			return getDay(dString);
		} catch (Exception e) {
			logger.error("getDay error", e);
			return 0;
		}
	}

	/**
	 * 求两个日期相隔的分钟数，默认第二个日期大于第一个日期
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getDifferMinute(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		Long differ = c2.getTimeInMillis() - c1.getTimeInMillis();
		Long result = differ / 1000 / 60;

		return result.intValue();
	}

	/**
	 * 把字符串转换为数据库类型Timestamp。
	 *
	 * @param dateStr
	 *            日期字符串，只支持"yyyy-MM-dd"和"yyyy-MM-dd HH:mm:ss:SS"两种格式。
	 *            如果为"yyyy-MM-dd"，系统会自动取得当前时间补上。
	 * @return
	 */
	public static Timestamp convertStrToTimestamp(String dateStr) {
		if (dateStr == null) {
			return null;
		}

		String dStr = new String(dateStr).trim();
		if (dStr.indexOf(" ") == -1) {
			dStr = dStr + " " + getCurrDateStr(TIME_FORMAT);
		}

		Date utilDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_LONG_DATE_FORMAT);

		try {
			utilDate = simpleDateFormat.parse(dStr);
		} catch (Exception ex) {
			throw new RuntimeException("DateUtil.convertStrToTimestamp(): " + ex.getMessage());
		}

		return new Timestamp(utilDate.getTime());
	}

	/**
	 * 得到系统当前时间的Timestamp对象
	 *
	 * @return 系统当前时间的Timestamp对象
	 */
	public static Timestamp getCurrTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 方法描述：把Date对象转化为Timestamp对象，用来比较时间 Timestamp的getTime方法
	 * 不返回毫秒,而oracle数据库也是不保存毫秒的 该方法避免用Date比较两个日期的精度问题
	 *
	 * @return
	 */
	public static Timestamp convertDate2Timestamp(Date date) {
		if (date != null) {
			long dateLong = date.getTime();
			String dateStr = "" + dateLong;
			// 去除毫秒
			dateStr = dateStr.substring(0, dateStr.length() - 3) + "000";
			Long timeLong = new Long(dateStr);
			return new Timestamp(timeLong);
		} else {
			return null;
		}

	}

	/**
	 * <p>
	 * 取得当前日期，并将其转换成格式为"dateFormat"的字符串 例子：假如当前日期是 2003-09-24 9:19:10，则：
	 *
	 * <pre>
	 *  getCurrDateStr(&quot;yyyyMMdd&quot;)=&quot;20030924&quot;
	 *  getCurrDateStr(&quot;yyyy-MM-dd&quot;)=&quot;2003-09-24&quot;
	 *  getCurrDateStr(&quot;yyyy-MM-dd HH:mm:ss&quot;)=&quot;2003-09-24 09:19:10&quot;
	 * </pre>
	 *
	 * </p>
	 *
	 * @param dateFormat
	 *            String 日期格式字符串
	 * @return String
	 */
	public static String getCurrDateStr(String dateFormat) {
		return convertDateToStr(getCurrentDate(), dateFormat);
	}

	/**
	 * 将日期类型转换成指定格式的日期字符串
	 *
	 * @param date
	 *            待转换的日期
	 * @param dateFormat
	 *            日期格式字符串
	 * @return
	 */
	public static String convertDateToStr(Date date, String dateFormat) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 将指定格式的字符串转换成日期类型
	 *
	 * @param dateStr
	 *            待转换的日期字符串
	 * @param dateFormat
	 *            日期格式字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStrToDate(String dateStr, String dateFormat) throws ParseException {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.parse(dateStr);
	}

	/**
	 * 计算两个日期之间的相隔的年、月、日。注意：只有计算相隔天数是准确的，相隔年和月都是 近似值，按一年365天，一月30天计算，忽略闰年和闰月的差别。
	 *
	 * @param datepart
	 *            两位的格式字符串，yy表示年，MM表示月，dd表示日
	 * @param startdate
	 *            开始日期
	 * @param enddate
	 *            结束日期
	 * @return 如果enddate>startdate，返回一个大于0的实数，否则返回一个小于等于0的实数
	 */
	public static double dateDiff(String datepart, Date startdate, Date enddate) {
		if (datepart == null || datepart.equals("")) {
			throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
		}

		double distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(enddate.getTime() - startdate.getTime());
		if (datepart.equals("yy")) {
			distance = distance / 365;
		} else if (datepart.equals("MM")) {
			distance = distance / 30;
		} else if (datepart.equals("dd")) {
			distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 1000 * 24);
		} else if (datepart.equals("hh")) {
			distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 1000);
		} else {
			throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
		}
		return distance;
	}

	/**
	 * 方法用途和描述: XXXXXXX（可以分多行编写） 方法的实现逻辑描述（如果是接口方法可以不写）：XXXXXXXXX（可以分多行编写）
	 *
	 * @param startdate
	 * @param enddate
	 * @return
	 * @author 王红鹏 新增日期：2008-12-3
	 * @author 你的姓名 修改日期：2008-12-3
	 */
	public static long dateDiff(Date startdate, Date enddate) {
		long distance = 0;
		long start = getDateTime(startdate);
		long end = getDateTime(enddate);
		distance = end - start;
		distance = distance / (24 * 60 * 60 * 1000l);
		return distance;
	}

	/**
	 *
	 * 方法用途和描述: 得到日期("yyyy-MM-dd")的时间差
	 *
	 * @param date
	 * @return
	 * @author 王红鹏 新增日期：2008-12-3
	 * @author 你的姓名 修改日期：2008-12-3
	 */
	private static long getDateTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 *
	 * 方法用途和描述: 得到日期("yyyy-MM-dd")的时间差
	 *
	 * @param timeInMillis
	 * @return
	 * @author 王红鹏 新增日期：2008-12-3
	 * @author 你的姓名 修改日期：2008-12-3
	 */
	public static Date getDate(long timeInMillis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillis);
		return cal.getTime();
	}

	/**
	 * 计算两个日期之间的相隔的月。
	 *
	 * @param startdate
	 *            开始日期
	 * @param enddate
	 *            结束日期
	 * @return 如果enddate>startdate，返回一个大于0的整数，否则返回0
	 */
	public static int getMonthDiff(Date startdate, Date enddate) {
		int k = 0;
		GregorianCalendar temp = new GregorianCalendar();

		temp.setTime(startdate);
		temp.set(GregorianCalendar.MILLISECOND, 0);
		temp.add(GregorianCalendar.DAY_OF_MONTH, 1);

		int day = temp.getActualMaximum(GregorianCalendar.DATE);

		GregorianCalendar endCal = new GregorianCalendar();
		endCal.setTime(enddate);
		endCal.set(GregorianCalendar.MILLISECOND, 0);
		endCal.add(GregorianCalendar.DAY_OF_MONTH, 1);

		while (temp.getTime().before(endCal.getTime())) {
			k++;
			day = temp.getActualMaximum(GregorianCalendar.DATE);
			temp.add(GregorianCalendar.DAY_OF_MONTH, day);

		}

		return k;
	}

	/**
	 * 把日期对象加减年、月、日后得到新的日期对象
	 *
	 * @param datepart
	 *            年、月、日
	 * @param number
	 *            加减因子
	 * @param date
	 *            需要加减年、月、日的日期对象
	 * @return 新的日期对象
	 */
	public static Date addDate(String datepart, int number, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (datepart.equals("yy")) {
			cal.add(Calendar.YEAR, number);
		} else if (datepart.equals("MM")) {
			cal.add(Calendar.MONTH, number);
		} else if (datepart.equals("dd")) {
			cal.add(Calendar.DATE, number);
		} else {
			throw new IllegalArgumentException("DateUtil.addDate()方法非法参数值：" + datepart);
		}

		return cal.getTime();
	}

	/**
	 * 按照给定的格式style将指定的日期值转换成字符串。
	 *
	 * @param date
	 *            : 待转换的日期
	 * @param style
	 *            : 指定转化类型,style参数取静态常量LONG、MEDIUM和SHORT的值
	 * @param loc
	 *            ：字符定义对象
	 * @return 格式化后的日期字符串
	 * @throws IllegalArgumentException
	 *             : style模板不符合格式时报异常
	 */
	public static String formatDate(Date date, int style, Locale loc) {
		if (style < 1 || style > 3) {
			throw new IllegalArgumentException("parameter is invalid.");
			// IllegalArgumentOfException ie = new IllegalArgumentOfException();
			// ie.setMessageCode("resource_DateFormatUtil_002");
			// ie.setExtendMessage(String.valueOf(style));
			// throw ie;
		}
		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		if (date != null) {
			DateFormat df = DateFormat.getDateInstance(style, loc);
			newDate = df.format(date);
		}
		return newDate;
	}

	/**
	 * 按照给定的格式模板将指定的日期值转换成字符串。
	 *
	 * @param date
	 *            : 待转换的日期
	 * @param pattern
	 *            : 指定转化格式字符串,例如：yyyy-MM-dd
	 * @param loc
	 *            : 字符定义对象
	 * @return 格式化后的日期字符串
	 * @throws IllegalArgumentException
	 *             : pattern模板不符合格式时报异常
	 */
	public static String formatDate(Date date, String pattern, Locale loc) {
		if (pattern == null) {
			throw new IllegalArgumentException("parameter is invalid.");
			// IllegalArgumentOfException ie = new IllegalArgumentOfException();
			// ie.setMessageCode("resource_DateFormatUtil_002");
			// ie.setExtendMessage(pattern);
			// throw ie;
		}
		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
			newDate = sdf.format(date);
		}
		return newDate;
	}

	public static String formatDate(Date date, String pattern) {
		return formatDate(date, pattern, null);
	}

	/**
	 * 按照不同的日期格式和时间格式，将指定的日期时间值转换成字符串。
	 *
	 * @param date
	 *            : 待转换的日期
	 * @param dateStyle
	 *            : 指定的日期类型,style参数取静态常量LONG、MEDIUM和SHORT的值
	 * @param timeStyle
	 *            ：指定的时间类型,style参数取静态常量LONG、MEDIUM和SHORT的值
	 * @param loc
	 *            ：字符定义对象
	 * @return 格式化后的日期时间字符串
	 */
	public static String formatDateTime(Date date, int dateStyle, int timeStyle, Locale loc) {
		if (dateStyle < 1 || dateStyle > 3) {
			throw new IllegalArgumentException("parameter is invalid.");
			// IllegalArgumentOfException ie = new IllegalArgumentOfException();
			// ie.setMessageCode("resource_DateFormatUtil_002");
			// ie.setExtendMessage(String.valueOf(dateStyle));
			// throw ie;
		}
		if (timeStyle < 1 || timeStyle > 3) {
			throw new IllegalArgumentException("parameter is invalid.");
			// IllegalArgumentOfException ie = new IllegalArgumentOfException();
			// ie.setMessageCode("resource_DateFormatUtil_003");
			// ie.setExtendMessage(String.valueOf(timeStyle));
			// throw ie;
		}
		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		if (date != null) {
			DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, loc);
			newDate = df.format(date);
		}
		return newDate;
	}

	/**
	 * 按照不同的格式模板将指定的日期时间值转换成字符串。
	 *
	 * @param date
	 *            : 待转换的日期
	 * @param dateStr
	 *            : 指定日期转化格式字符串模板,例如:yyyy-MM-dd
	 * @param timeStr
	 *            : 指定时间转化格式字符串模板,例如:hh:mm:ss
	 * @param loc
	 *            ：字符定义对象
	 * @return 格式化后的日期时间字符串
	 * @throws IllegalArgumentException
	 *             : pattern模板不符合格式时报异常
	 */
	public static String formatDateTime(Date date, String dateStr, String timeStr, Locale loc) {
		if (dateStr == null) {
			throw new IllegalArgumentException("parameter is invalid.");
			// IllegalArgumentOfException ie = new IllegalArgumentOfException();
			// ie.setMessageCode("resource_DateFormatUtil_002");
			// ie.setExtendMessage(dateStr);
			// throw ie;
		}
		if (timeStr == null) {
			throw new IllegalArgumentException("parameter is invalid.");
			// IllegalArgumentOfException ie = new IllegalArgumentOfException();
			// ie.setMessageCode("resource_DateFormatUtil_003");
			// ie.setExtendMessage(timeStr);
			// throw ie;
		}

		String newDate = "";
		if (loc == null) {
			loc = Locale.getDefault();
		}
		if (date != null) {
			String pattern = (dateStr == null ? "" : dateStr) + " "
					+ (timeStr == null ? "" : timeStr);
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
			newDate = sdf.format(date);
		}
		return newDate;
	}

	/**
	 * 把日期时间格式化为yyyy-MM-dd HH:mm:ss格式
	 *
	 * @param dt
	 *            java.util.Date
	 * @return 格式化后的日期时间字符串
	 */
	public static String formatDateTime(Date dt) {
		String newDate = "";
		if (dt != null) {
			Locale locale = Locale.CHINESE;
			SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
			newDate = dateStyle.format(dt);
		}
		return newDate;
	}

	/**
	 * 将指定的字符串转换成日期
	 *
	 * @param dateStr
	 *            : 待转换的日期符串,以yyyy-MM-dd模板进行转换
	 * @return 返回标准的日期格式yyyy-MM-dd,与字符串dateStr对应的date对象
	 * @throws ParseException
	 */
	public static Date parseStrToDate(String dateStr) throws ParseException {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_dd);
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw e;
			// ParseStringException pse = new
			// ParseStringException(e.getMessage(),e.getErrorOffset());
			// pse.setMessageCode("resource_DateFormatUtil_001");
			// pse.setExtendMessage(dateStr);
			// throw pse;
		}
	}

	/**
	 * 按照不同的格式模板将指定的字符串转换成日期。
	 *
	 * @param date
	 *            : 待转换的日期符串
	 * @param pattern
	 *            : 字符串的格式模板,例如:yyyy-MM-dd HH:mm:ss
	 * @return 与字符串dateStr对应的date对象
	 * @throws ParseException
	 */
	public static Date parseStrToDate(String date, String pattern) throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(date);
		} catch (ParseException e) {
			throw e;
			// ParseStringException pse = new
			// ParseStringException(e.getMessage(),e.getErrorOffset());
			// pse.setMessageCode("resource_DateFormatUtil_001");
			// pse.setExtendMessage(date);
			// throw pse;
		}
	}

	/**
	 * 获得当前日期
	 *
	 * @return Date实例
	 */
	public static Date getCurrentDate() {
		// return new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 只获取年月日的日期
	 * @return
	 */
	public static Date getCurrentDay() {
		Date date = new Date();
		try {
			return convertStrToDate(convertDateToStr(date, DEFAULT_SHORT_DATE_FORMAT), DEFAULT_SHORT_DATE_FORMAT);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得当前系统的时间戳
	 *
	 * @return 从1970-1-1到现在的毫秒数
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 在给定的日期点上加入指定的天数
	 *
	 * @param date
	 *            给定的日期点
	 * @param days
	 *            天数，正数为向后；负数为向前
	 * @return 返回改变后的时间点
	 */
	public static Date addDate(Date date, int days) {
		if (date == null)
			return date;
		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, day + days);
		return cal.getTime();
	}

	/**
	 * 在给定的日期加天数
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		if(date == null || days == 0) {
			return date;
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_MONTH, days);
		return ca.getTime();
	}

	/**
	 * 将给定日期的时分秒和毫秒清零
	 *
	 * @param date
	 *            给定的日期
	 * @return
	 * @throws Exception
	 */
	public static Date clearFromHoursToMillis(Date date) throws Exception {
		if (date == null)
			return date;

		date = changeStrToDate(changeDateToStr(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		return date;
	}

	/**
	 * 在当前的日期点上加入指定的天数
	 *
	 * @param days
	 *            天数，正数为向后；负数为向前
	 * @return 返回改变后的时间
	 */
	public static Date addDate(int days) {
		Locale loc = Locale.getDefault();
		GregorianCalendar cal = new GregorianCalendar(loc);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, day + days);
		return cal.getTime();
	}

	/**
	 * 获得两个时间点之间相差的天数
	 *
	 * @param date1
	 *            开始时间点
	 * @param date2
	 *            结束时间点
	 * @return 具体的天数
	 */
	public static int getDays(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		return (int) ((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000));
	}

	public static final String DATETIME_FORMAT = "HH:mm:ss";

	public static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm:ss";

	// add by messi(chencao)
	/**
	 * 方法描述：把参数日期的时,分,秒清零,返回下一天 如:参数为2006-08-22 12:33 那么该方法返回 2006-08-23 00:00 ×
	 *
	 * @param day
	 * @return
	 */
	public static Date getNextDay(Date day) {
		Calendar nowC = date2Clear(day);
		nowC.add(Calendar.DATE, 1);
		return nowC.getTime();
	}

	/**
	 * 方法描述：把参数日期的时,分,秒清零,返回前一天 如:参数为2006-08-22 12:33 那么该方法返回 2006-08-21 00:00
	 *
	 * @param day
	 * @return
	 */
	public static Date getPreDay(Date day) {
		Calendar nowC = date2Clear(day);
		nowC.add(Calendar.DATE, -1);
		return nowC.getTime();
	}

	public static Date getLastSecPreDay(Date day) {
		Calendar nowC = date2Clear(day);
		nowC.set(Calendar.SECOND, -1);
		return nowC.getTime();
	}

	private static Calendar date2Clear(Date day) {
		Calendar nowC = Calendar.getInstance();
		nowC.setTime(day);
		nowC.set(Calendar.HOUR, 0);
		nowC.set(Calendar.HOUR_OF_DAY, 0);
		nowC.set(Calendar.MINUTE, 0);
		nowC.set(Calendar.SECOND, 0);
		nowC.set(Calendar.MILLISECOND, 0);
		return nowC;
	}

	// end by messi(chencao)

	// 取得当前系统的日期
	public static Date getCurrentDate(String fommatstr) {
		SimpleDateFormat sdf = new SimpleDateFormat(fommatstr);// "yyyy-MM-dd
		// HH:mm:ss"
		try {
			Date now = new Date(System.currentTimeMillis());
			String datestr = sdf.format(now).toString();
			// /System.out.println(test);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 将字符串转成时间Date
	 *
	 * @throws ParseException
	 */
	public static Date changeStrToDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}

	/**
	 * 将时间转成指定格式的字符串
	 */
	public static String changeDateToStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将时间转成yyyy-MM-dd字符形式的Long类型
	 */
	public static Long changeDateToLong(Date date) {
		Calendar c = java.util.GregorianCalendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}

	/**
	 * 将时间转成yyyy-MM-dd的Date类型
	 */
	public static Date changeDateToFormatDate(Date date) {
		Calendar c = java.util.GregorianCalendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 增加,减少天数
	 */
	public static Date updateDay(Date date, int changday) {
		Calendar c = java.util.GregorianCalendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		day += changday;
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}

	/**
	 * 取得时间对应的星期,从星期天开始
	 */
	public static int getWeekFromSunDay(Date date) {
		Calendar c = java.util.GregorianCalendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 取得时间对应的星期,从星期一开始
	 */
	public static int getWeekFromMonday(Date date) {
		int week = 0;
		int temp = 0;
		temp = getWeekFromSunDay(date);
		if (temp == 1) {
			week = 7;
		} else {
			week = temp - 1;
		}
		return week;
	}

	/**
	 * 将小时化成分钟,再加分钟时间
	 */
	public static int getPlayDatehhmm(Date date) {
		Calendar c = java.util.GregorianCalendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
	}

	/**
	 * 方法用途和描述: 获取本周的第一天的日期,从星期一开始，星期日结束
	 * <p>
	 * 方法的实现逻辑描述:
	 *
	 * @return
	 * @author huangtao 新增日期：2008-8-12
	 * @author huangtao 修改日期：2008-8-12
	 */
	public static Date getBeginDateOfWeek() {
		Calendar cal = Calendar.getInstance();
		int amount = cal.get(Calendar.DAY_OF_WEEK);
		if (amount == 1) {
			cal.add(Calendar.DAY_OF_MONTH, -6);
		} else {
			cal.add(Calendar.DAY_OF_MONTH, 2 - amount);
		}
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 获取本周的最后天的日期,从星期一开始，星期日结束
	 * <p>
	 * 方法的实现逻辑描述:
	 *
	 * @return
	 * @author huangtao 新增日期：2008-8-10
	 * @author huangtao 修改日期：2008-8-10
	 */
	public static Date getEndDateOfWeek() {
		Calendar cal = Calendar.getInstance();
		int amount = cal.get(Calendar.DAY_OF_WEEK);
		if (amount != 1) {
			cal.add(Calendar.DAY_OF_MONTH, 8 - amount);
		}
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 获取本月的第一天
	 * <p>
	 * 方法的实现逻辑描述:
	 *
	 * @return
	 * @author huangtao 新增日期：2008-8-12
	 * @author huangtao 修改日期：2008-8-12
	 */
	public static Date getBeginDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 获取指定某个月的第一天，
	 * <p>
	 * 方法的实现逻辑描述:
	 *
	 * @param specMonth
	 *            格式为：200809
	 * @return
	 * @author huangtao 新增日期：2008-9-3
	 * @author huangtao 修改日期：2008-9-3
	 */
	public static Date getBeginDayOfMonth(String specMonth) {
		if (specMonth == null || specMonth.trim().length() == 0) {
			return null;
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		try {
			date = sdf.parse(specMonth);
			cal.setTime(date);
		} catch (ParseException e) {
			logger.error("getBeginDayOfMonth error", e);
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 获取本月的最后一天
	 * <p>
	 * 方法的实现逻辑描述:
	 *
	 * @return
	 * @author huangtao 新增日期：2008-8-12
	 * @author huangtao 修改日期：2008-8-12
	 */
	public static Date getEndDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 获取指定月份的最后一天
	 * <p>
	 * 方法的实现逻辑描述:
	 *
	 * @param specMonth
	 * @return
	 * @author huangtao 新增日期：2008-9-3
	 * @author huangtao 修改日期：2008-9-3
	 */
	public static Date getEndDayOfMonth(String specMonth) {
		if (specMonth == null || specMonth.trim().length() == 0) {
			return null;
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		try {
			date = sdf.parse(specMonth);
			cal.setTime(date);
		} catch (ParseException e) {
			logger.error("getEndDayOfMonth error." ,e);
		}
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 获得参数指定月份的前一个月，参数格式："200811",或"200801"，返回格式："200810",或"200712"
	 * <p>
	 * 方法的实现逻辑描述:
	 *
	 * @param month
	 * @return
	 * @author huangtao 新增日期：2008-9-18
	 * @author huangtao 修改日期：2008-9-18
	 */
	public static String getPreMonth(String month) {
		if (month == null || month.trim().length() == 0) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(month));
			cal.add(Calendar.MONTH, -1);
		} catch (ParseException e) {
			logger.error("getPreMonth error", e);
		}
		return sdf.format(cal.getTime());
	}

	/**
	 * 方法用途和描述: 获取当前日期 格式为：xxxx-yy-zz (eg: 2008-08-19) 方法的实现逻辑描述:
	 *
	 * @return
	 * @author yjx 新增日期：2008-8-19
	 * @author yjx 修改日期：2008-8-19
	 */
	public static String today() {
		int x; // 日期属性：年
		int y; // 日期属性：月
		int z; // 日期属性：日

		Calendar localTime = Calendar.getInstance(); // 当前日期
		String strY = null;
		String strZ = null;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		z = localTime.get(Calendar.DATE);
		// int hour = localTime.get(Calendar.HOUR);
		// int minute = localTime.get(Calendar.MINUTE);
		// int seconds = localTime.get(Calendar.SECOND);
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		strZ = z >= 10 ? String.valueOf(z) : ("0" + z);
		return x + "-" + strY + "-" + strZ;
		// return x + "-" + strY + "-" + strZ + " " + hour
		// +":"+minute+":"+seconds;
	}

	/**
	 * 方法用途和描述: 获取本周的第一天的日期,从星期天开始，星期一结束 方法的实现逻辑描述:
	 *
	 * @return
	 * @author yjx 新增日期：2008-8-19
	 * @author yjx 修改日期：2008-8-19
	 */
	public static String getStartDateOfWeek() {
		int x; // 日期属性：年
		int y; // 日期属性：月
		int z; // 日期属性：日
		String strY = null;
		String strZ = null;
		Calendar localTime = Calendar.getInstance();
		// Modified by 陈艺武 2009-01-16
		int amount = localTime.get(Calendar.DAY_OF_WEEK);
		localTime.add(Calendar.DAY_OF_MONTH, 1 - amount);
		/*
		 * if (amount == 1) { localTime.add(Calendar.DAY_OF_MONTH, -6); } else {
		 * localTime.add(Calendar.DAY_OF_MONTH, 2 - amount); }
		 */

		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);

		z = localTime.get(Calendar.DATE);
		strZ = z >= 10 ? String.valueOf(z) : ("0" + z);
		return x + "-" + strY + "-" + strZ;
	}

	/**
	 * 方法用途和描述: 获取本周的最后一天的日期,从星期天开始，星期一结束 方法的实现逻辑描述:
	 *
	 * @return
	 * @author yjx 新增日期：2008-8-19
	 * @author yjx 修改日期：2008-8-19
	 */
	public static String getEndDateOfWeekend() {
		int x; // 日期属性：年
		int y; // 日期属性：月
		int z; // 日期属性：日
		String strY = null;
		String strZ = null;
		Calendar localTime = Calendar.getInstance();

		int amount = localTime.get(Calendar.DAY_OF_WEEK);
		localTime.add(Calendar.DAY_OF_MONTH, 7 - amount);
		/*
		 * if (amount != 1) { localTime.add(Calendar.DAY_OF_MONTH, 8 - amount);
		 * }
		 */

		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		z = localTime.get(Calendar.DATE);

		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		strZ = z >= 10 ? String.valueOf(z) : ("0" + z);
		return x + "-" + strY + "-" + strZ;
	}

	/**
	 * 方法用途和描述: 得到当前月份月初 格式为：xxxx-yy-zz (eg: 2008-08-19) 方法的实现逻辑描述:
	 *
	 * @return
	 * @author yjx 新增日期：2008-8-19
	 * @author yjx 修改日期：2008-8-19
	 */
	public static String getMonthFromTheFirstDay() {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance(); // 当前日期
		String strY = null;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-01";
	}

	/**
	 * 方法用途和描述: 得到当前月份月底 格式为：xxxx-yy-zz (eg: 2008-08-31) 方法的实现逻辑描述:
	 *
	 * @return
	 * @author yjx 新增日期：2008-8-19
	 * @author yjx 修改日期：2008-8-19
	 */
	public static String getMonthFromTheEndDay() {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance(); // 当前日期
		String strY = null;
		String strZ = null;
		boolean leap = false;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
			strZ = "31";
		}
		if (y == 4 || y == 6 || y == 9 || y == 11) {
			strZ = "30";
		}
		if (y == 2) {
			leap = leapYear(x);
			if (leap) {
				strZ = "29";
			} else {
				strZ = "28";
			}
		}
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-" + strZ;
	}

	/**
	 * 方法用途和描述: 判断输入年份是否为闰年 方法的实现逻辑描述:
	 *
	 * @param year
	 * @return
	 * @author yjx 新增日期：2008-8-19
	 * @author yjx 修改日期：2008-8-19
	 */
	private static boolean leapYear(int year) {
		boolean leap;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					leap = true;
				else
					leap = false;
			} else
				leap = true;
		} else
			leap = false;
		return leap;
	}

	/**
	 * 方法用途和描述: 比较两个日期(不包括时间)的大小 方法的实现逻辑描述（如果是接口方法可以不写）：
	 *
	 * @param d1
	 * @param d2
	 * @return 0代表d1等于d2; -1代表d1小于d2; 1代表d1大于d2
	 * @author 梁锐能 新增日期：2008-8-21
	 * @author 梁锐能 修改日期：2008-8-21
	 */
	public static int compareDateIgnoreTime(Date d1, Date d2) {

		int discrepancy = 0;
		if (d1 == null || d2 == null)
			throw new RuntimeException("The parameter is null");
		int result = compareYearAndMonth(d1, d2);
		if (result != 0)
			return result;

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		discrepancy = cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);
		if (discrepancy != 0)
			return discrepancy < 0 ? -1 : 1;

		return 0;
	}

	/**
	 * 方法用途和描述: 比较日期的年月 方法的实现逻辑描述（如果是接口方法可以不写）：
	 *
	 * @param d1
	 * @param d2
	 * @return 0代表d1等于d2; -1代表d1小于d2; 1代表d1大于d2
	 * @author 梁锐能 新增日期：2008-8-21
	 * @author 梁锐能 修改日期：2008-8-21
	 */
	public static int compareYearAndMonth(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			throw new RuntimeException("The parameter is null");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		int discrepancy = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (discrepancy != 0)
			return discrepancy < 0 ? -1 : 1;

		discrepancy = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
		if (discrepancy != 0)
			return discrepancy < 0 ? -1 : 1;

		return 0;
	}

	/**
	 * 方法用途和描述: 比较时间(不包括日期) 方法的实现逻辑描述（如果是接口方法可以不写）：
	 *
	 * @param d1
	 * @param d2
	 * @return 0代表d1等于d2; -1代表d1小于d2; 1代表d1大于d2
	 * @author 梁锐能 新增日期：2008-8-21
	 * @author 梁锐能 修改日期：2008-8-21
	 */
	public static int compareTime(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			throw new RuntimeException("The parameter is null");
		int discrepancy = 0;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		discrepancy = cal1.get(Calendar.HOUR_OF_DAY) - cal2.get(Calendar.HOUR_OF_DAY);
		if (discrepancy != 0)
			return discrepancy < 0 ? -1 : 1;

		discrepancy = cal1.get(Calendar.MINUTE) - cal2.get(Calendar.MINUTE);
		if (discrepancy != 0)
			return discrepancy < 0 ? -1 : 1;

		discrepancy = cal1.get(Calendar.SECOND) - cal2.get(Calendar.SECOND);
		if (discrepancy != 0)
			return discrepancy < 0 ? -1 : 1;

		return 0;
	}

	/**
	 * 方法用途描述: 验证是否是前三天或当天的其中一天 实现逻辑描述: 如下
	 *
	 * @param date
	 * @return
	 * @author 陈艺武 新增日期：2008-10-31
	 * @author 你的姓名 修改日期：2008-10-31
	 */
	public boolean isAvaildPeriod(Date date) {
		Calendar cal = Calendar.getInstance();
		int curDateOfYear = cal.get(Calendar.DAY_OF_YEAR);

		cal.setTime(date);
		int selectDateOfYear = cal.get(Calendar.DAY_OF_YEAR);
		int betValue = curDateOfYear - selectDateOfYear;

		if (betValue >= 0 && betValue <= 3)
			return true;
		else
			return false;
	}

	public static Date getDateBefore(int beforeDay) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, beforeDay);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today.getTime();
	}


	/**
	 *
	 *
	 * 功能描述：计算两个日期之间的月差数。
	 *
	 * @param startdate
	 * @param enddate
	 * @return 如果enddate>startdate，返回一个大于0的整数，
	 *         如果enddate小于startdate，返回一个小于0的整数，enddate=startdate则返回0
	 * @author <a href="mailto:wandalong@ceopen.cn">万大龙</a>
	 * @version SCMS1.0
	 * @since SCMS1.0 create on: 2010-10-10
	 *
	 */
	public static int getMonthDiff2(Date startdate, Date enddate) {
		int k = 0;
		GregorianCalendar temp = new GregorianCalendar();

		temp.setTime(startdate);
		temp.set(GregorianCalendar.MILLISECOND, 0);
		temp.add(GregorianCalendar.DAY_OF_MONTH, 1);
		int day = temp.getActualMaximum(GregorianCalendar.DATE);

		GregorianCalendar endCal = new GregorianCalendar();
		endCal.setTime(enddate);
		endCal.set(GregorianCalendar.MILLISECOND, 0);
		endCal.add(GregorianCalendar.DAY_OF_MONTH, 1);

		while (temp.getTime().before(endCal.getTime())) {
			k++;
			day = temp.getActualMaximum(GregorianCalendar.DATE);
			temp.add(GregorianCalendar.DAY_OF_MONTH, day);
		}
		if (k == 0) {
			while (temp.getTime().after(endCal.getTime())) {
				day = endCal.getActualMaximum(GregorianCalendar.DATE);
				endCal.add(GregorianCalendar.DAY_OF_MONTH, day);
				if (temp.getTime().after(endCal.getTime())) {
					k--;
				}
			}
		}
		return k;
	}

	/**
	 * 功能描述：计算两个日期之间的天数差。
	 *
	 * @param startDate
	 * @param endDate
	 * @return 如果endDate>startDate，返回一个大于0的整数，
	 *         如果endDate<startDate，返回一个小于0的整数，enddate=startdate则返回0
	 */
	public static long getDateDiff(Date startDate, Date endDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime())
				/ (1000 * 60 * 60 * 24);
	}

	/**
	 * 功能描述：计算两个时间之间的秒数差。
	 *
	 * @param startTime
	 * @param endTime
	 * @return 如果endDate>startDate，返回一个大于0的整数，
	 *         如果endDate<startDate，返回一个小于0的整数，enddate=startdate则返回0
	 */
	public static long getSecondDiff(Date startTime, Date endTime) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startTime);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endTime);

		return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000);
	}

	public static Calendar toDayStartTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * 验证日期格式
	 * @param date
	 * @return
	 */
	public static boolean checkDateFormat(String date) {
		String eL = "^((//d{2}(([02468][048])|([13579][26]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])|([1-2][0-9])))))|(//d{2}(([02468][1235679])|([13579][01345789]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(//s(((0?[0-9])|([1][0-9])|([2][0-3]))//:([0-5]?[0-9])((//s)|(//:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean b = m.matches();
		return b;

	}

	
	/**
	 * 获取日期
	 * @param day 日期偏离值，0为当天，负数为已过去的日期
	 * @return
	 */
	public static String getDateByLong(long day) {
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
		try {

//			Date date = DateUtil.addDate(new Date(), -40);
//			System.out.println(new Date(1447948800000L));
//			System.out.println("date : "+date);
//			System.out.println(DateUtil.convertStrToDate("2015-10-21 10:00:00", DateUtil.DEFAULT_LONG_DATE_FORMAT));

			Date date1 = convertStrToDate("2015-10-21 10:00:00", DateUtils.DEFAULT_LONG_DATE_FORMAT);
			Date date2 = convertStrToDate("2015-10-21 10:00:01", DateUtils.DEFAULT_LONG_DATE_FORMAT);
			System.out.println("date1 before date2 : "+ date1.before(date2));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getDateYYMMDD("2012-01-01"));
	}
	
}