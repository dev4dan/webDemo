package com.dev4dan.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;
public class GeekStringUtils extends StringUtils {
    /**
	 * 校验手机号码是否为常规手机号码
	 * @param mobile
	 * @return
	 */
	public static boolean isValidatedMobile(String mobile){
		if(mobile == null || mobile.equals("")){
			return false;
		}
		Pattern p = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");  	  
		Matcher m = p.matcher(mobile);  	    
		return m.matches();
	}
	
	public final static int EMAIL_SUCCESS = 0;
	public final static int EMAIN_FAIL = 1;
	public final static int EMAIL_NULL = 2;
	
	/**
	 * 校验邮箱是否为合法邮箱
	 * @param email
	 * @return
	 */
	public static int isValidatedEmail(String email){
		//校验邮箱
		if(!isEmpty(email)){
			if(email.indexOf("@")>0){//只要有@就行
				return EMAIL_SUCCESS;
			}		
			return EMAIN_FAIL;
		}	
		return EMAIL_NULL;
	}
	
	/**
	 * 判断一个字符串是否为数字（建议以后数值类型判断调用对应数值判断方法：isIntegerString，isLongString）
	 * @param numberStr
	 * @return
	 */
	public static boolean isNumberString(String numberStr){
		return isIntegerString(numberStr);
	}
	
	/**
	 * 判断一个字符串是否为整型
	 * @param numberStr
	 * @return
	 */
	public static boolean isIntegerString(String numberStr){
		if(numberStr == null || numberStr.equals("")){
			return false;
		}
		
		Pattern p = Pattern.compile("^[0-9]+$");
		if(isNegativeNum(numberStr)&& numberStr.length() <= 11){
			String[] strTemp = numberStr.split("-");
			Matcher m = p.matcher(strTemp[1]);

			if(!m.matches()){
				return false;
			}
			
			if(numberStr.length() == 11){				
				int head = Integer.valueOf(strTemp[1].substring(0,5));// 前半部分，5位数字
				if(head > 21474){
					return false;
				}
				int tail = Integer.valueOf(strTemp[1].substring(5,strTemp[1].length()));
				if(tail > 83648){ // 后半部分，5位数字
					return false;
				}
			}
			return true;
		}

		Matcher m = p.matcher(numberStr);
		if(m.matches() && numberStr.length() <= 10){
			if(numberStr.length() == 10){				
				int headInt = Integer.valueOf(numberStr.substring(0, 5));
				if(headInt > 21474){
					return false;
				}
				int tailInt = Integer.valueOf(numberStr.substring(5, numberStr.length()));
				if(tailInt > 83647){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 判断一个字符串是否为长整型
	 * @param numberStr
	 * @return
	 */
	public static boolean isLongString(String numberStr){
		if(numberStr == null || numberStr.equals("")){
			return false;
		}
		Pattern p = Pattern.compile("^[0-9]+$");
		if(isNegativeNum(numberStr)&& numberStr.length() <= 20){
			String[] strTemp = numberStr.split("-");
			if(strTemp[1].length() == 19){				
				long head = Long.valueOf(strTemp[1].substring(0,10)); // 前半部分，10位数字
				if(head > 9223372036l){
					return false;
				}
				long tail = Long.valueOf(strTemp[1].substring(10,strTemp[1].length()));
				if(tail > 854775807){ // 后半部分，9位数字
					return false;
				}
			}
			return true;
		}
		
		Matcher m = p.matcher(numberStr);  	
		if(m.matches() && numberStr.length() <= 19){
			if(numberStr.length() == 19){				
				long head = Long.valueOf(numberStr.substring(0, 10)); // 前半部分，10位数字
				if(head > 9223372036l){
					return false;
				}
				long tail = Long.valueOf(numberStr.substring(10, numberStr.length()));
				if(tail > 854775807l){  // 后半部分，9位数字
					return false;
				}
			}
			return true;
		}else{			
			return false;
		}
	}
	
	
	public static final int NOT_BOOLEAN = -1; // 非布尔值
	public static final int BOOLEAN_TRUE = 1; // 布尔值为true
	public static final int BOOLEAN_FALSE = 0; // 布尔值为false
	
	/**
	 * 判断给定的string是否为一个boolean，即true或者false
	 * @return
	 */
	public static int isBooleanString(String booleanString){
		if(!isEmpty(booleanString)){
			if(booleanString.equals("true")){
				return BOOLEAN_TRUE;
			}else if(booleanString.equals("false")){
				return BOOLEAN_FALSE;
			}
		}
		return NOT_BOOLEAN;
	}
	
	/**
	 * 判断给定的字符串是否为负数
	 * @param numberStr
	 * @return
	 */
	private static boolean isNegativeNum(String numberStr){
		if(numberStr.indexOf("-") != -1){ // 字符串含有一个负号-，则分割为两个字符串；若含有多个就返回错误
			String[] tmpStr = numberStr.split("-");
			
			if(tmpStr.length == 2){
				return true;
			}
			return false;
		}
		return false;
	}
	
	public static boolean checkFax(String fax){
		if(isEmpty(fax))return true;
		String reg = "\\d+";
		return fax.matches(reg);
	}
	
	public static final int STRING_NULL = 0; // 字符串不能为空
	public static final int NUMBER_IS_NOT_INTEGER = 1;
	public static final int NUMBER_IS_NOT_DOUBLE = 2;
	public static final int NUMBER_SUCCESS = 3;
	
	/**
	 * 判断一个字符串是否为double数值
	 * @param doubleString
	 * @return
	 */
	public static int isDoubleString(String doubleString){
		if(doubleString == null || doubleString.equals("")){
			return STRING_NULL;
		}
		
		Pattern p = Pattern.compile("^[0-9]+$");  	  
		
		if(doubleString.indexOf(".") != -1){
			
			if(!isNegativeNum(doubleString)){
				return NUMBER_IS_NOT_DOUBLE;
			}
			String[] tmpStr = doubleString.split("-");
			String[] strs = tmpStr[1].split("\\.");
			
			if(strs.length != 2){
				return NUMBER_IS_NOT_DOUBLE;
			}
			
			for(String str : strs){
				Matcher m = p.matcher(str);
				if(!m.matches()){
					return NUMBER_IS_NOT_DOUBLE;
				}
			}
			return NUMBER_SUCCESS;
		}
		
		Matcher m = p.matcher(doubleString);
		
		if (m.matches()) {
			return NUMBER_SUCCESS;
		}
		
		return NUMBER_IS_NOT_DOUBLE;
	}
	
	public static final int NAME_NULL = 0; // 姓名不能为空
	public static final int NAME_CN_EN = 1; // 姓名应该为中文字符或者英文字母
	public static final int NAME_CN_LIMIT = 2; // 姓名长度应该在2到8个字符
	public static final int NAME_EN_LIMIT = 3; // length of name should between 4 to 50
	public static final int NAME_SUCCESS = 4; // 姓名验证成功
	public static final int NAME_CN_LIMIT_LEN = 8; // 中文名字长度
	public static final int NAME_EN_LIMIT_LEN = 16; // 英文名字长度
	
	public static int checkName(String empName){
		if (isEmpty(empName)) {
			return NAME_NULL;
		}

		String regexCN = "[\u4e00-\u9fa5]+";// 长度2~8个中文字符，英文字符为4-50
		Matcher matcherCN = Pattern.compile(regexCN).matcher(empName);
		String regexEN = "[a-zA-Z]+";
		Matcher matcherEN = Pattern.compile(regexEN).matcher(empName);
		if (!matcherCN.matches() && !matcherEN.matches()) {
			return NAME_CN_EN;
		} else if (matcherCN.matches()
				&& (empName.length() < 2 || empName.length() > NAME_CN_LIMIT_LEN)) {
			return NAME_CN_LIMIT;
		} else if (matcherEN.matches()
				&& (empName.length() < 4 || empName.length() > NAME_EN_LIMIT_LEN)) {
			return NAME_EN_LIMIT;
		}
		return NAME_SUCCESS;
	}
	
	public static String blankString(int num) {
		String str = "";
		while (num > 0) {
			str = str + " ";
			num--;
		}
		return str;
	}

	public static boolean isNull(String str) {
		if ((str == null) || ("".equals(str.trim()))) {
			return true;
		}
		return false;
	}

	public static String subString(String src, String start, String end) {
		src = src.substring(src.indexOf(start)).substring(start.length());
		if (end != null) {
			src = src.substring(0, src.indexOf(end));
		}
		return src;
	}

	public static String subStringFromLastStart(String src, String lastStart,
			String end) {
		src = src.substring(src.lastIndexOf(lastStart)).substring(
				lastStart.length());
		if (end != null) {
			src = src.substring(0, src.indexOf(end));
		}
		return src;
	}

	public static String subStringSmart(String src, String start, String end) {
		if (!isNull(start)) {
			src = src.indexOf(start) > -1 ? src.substring(src.indexOf(start))
					.substring(start.length()) : src;
		}
		if (!isNull(end)) {
			src = src.indexOf(end) > -1 ? src.substring(0, src.indexOf(end))
					: src;
		}
		return src;
	}

	public static String removeHtmlTags(String htmlSource) {
		return htmlSource.replaceAll("\\<.*?>", "");
	}
	public static String removeScript(String htmlSource) {
		return htmlSource.replaceAll("\\<script.*?/script>", "");
	}
	public static String removeHreflink(String htmlSource) {
		htmlSource = htmlSource.replaceAll("\\<a[^>]*>", "");
		htmlSource = htmlSource.replaceAll("\\</a>", "");
		return htmlSource;
	}

	public static String removeScriptAndHrefTags(String htmlSource) {
		htmlSource = removeScript(htmlSource);
		htmlSource = removeHreflink(htmlSource);
		return htmlSource;
	}

	public static String addStringBeforeAll(String source, String locateString,
			String addString) {
		int index = 0;
		StringBuffer realBody = new StringBuffer(source);
		String bstr = blankString(addString.length() + locateString.length());
		while ((index = source.indexOf(locateString)) >= 0) {
			source = bstr + source.substring(0, index - 1)
					+ source.substring(index + locateString.length() - 1);
			realBody = realBody.insert(index, addString);
		}
		return realBody.toString();
	}

	public static String substringBeforeLast(String str, String separator) {
		if ((str == null) || (separator == null) || (str.length() == 0)
				|| (separator.length() == 0)) {
			return str;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String substringBeforeLastWithSeparator(String str,
			String separator) {
		String result = substringBeforeLast(str, separator);
		if (!result.equals(str)) {
			result = result + separator;
		}
		return result;
	}

	public static String returnBlankIfNull(String str) {
		return str == null ? "" : str;
	}
	public static Boolean returnFalseIfNull(String str) {
		return Boolean.valueOf(str == null ? Boolean.FALSE.booleanValue()
				: Boolean.parseBoolean(str));
	}

	public static List<String> getListFromStart2End(String content,
			String start, String end, boolean isFirstMainContent) {
		List<String> replysList = new ArrayList<String>();
		if (content.indexOf(start) < 0) {
			return replysList;
		}
		int index_start = -1;
		int num = 1;
		while ((index_start = content.indexOf(start)) > 0) {
			content = content.substring(index_start);
			int index_end = content.indexOf(end);
			if (index_end <= 0) {
				break;
			}
			String reply = subString(content, start, end);
			if ((!isFirstMainContent) || ((isFirstMainContent) && (num > 1))) {
				replysList.add(reply);
			}
			content = content.substring(content.indexOf(end) + end.length());

			num++;
		}
		return replysList;
	}

	public static String replaceContent(String content, String from, String to,
			boolean isIssRegularExpression) {
		if ((!isNull(from)) && (!isNull(to))) {
			if (isIssRegularExpression) {
				content = content.replaceAll(from, to);
			} else {
				content = content.replace(from, to);
			}
		}
		return content;
	}

	public static String encryptContent(String content)
			throws UnsupportedEncodingException {
		String str = URLEncoder
				.encode(URLEncoder.encode("°", "UTF-8"), "UTF-8");
		if (content.indexOf("°") != -1) {
			content = content.replace("°", str);
		}
		if (content.indexOf("±") != -1) {
			content = content.replace("±", "%C2%B");
		}
		return content;
	}

	public static boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		if (!file.exists()) {
			return flag;
		}
		if (file.isFile()) {
			return deleteFile(sPath);
		}
		return deleteDirectory(sPath);
	}

	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		if ((file.isFile()) && (file.exists())) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public static boolean deleteDirectory(String sPath) {
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		if (dirFile == null ||  (!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;

		File[] files = dirFile.listFiles();
		if(files == null){
			return false;
		}
		
		for (int i = 0; i < files.length; i++) {
			if(files[i] != null ){				
				if (files[i].isFile()) {
					flag = deleteFile(files[i].getAbsolutePath());
					if (!flag) {
						break;
					}
				} else {
					flag = deleteDirectory(files[i].getAbsolutePath());
					if (!flag) {
						break;
					}
				}
			}
		}
		if (!flag) {
			return false;
		}
		if (dirFile.delete()) {
			return true;
		}
		return false;
	}

	public static List<String> splitString(String rawStr) {
		if (rawStr.startsWith("!")) {
			return splitByexclamation(rawStr);
		}
		return splitByUnderline(rawStr);
	}

	public static List<String> splitByexclamation(String rawStr) {
		List<String> params = new ArrayList<String>();
		String[] strs = rawStr.split("!");
		for (String str : strs) {
			if (str != null && !str.equals("")) {
				params.add(str);
			}
		}
		return params;
	}

	public static List<String> splitByAt(String rawStr) {
		List<String> params = new ArrayList<String>();
		String[] strs = rawStr.split("@");
		for (String str : strs) {
			if (str != null && !str.equals("")) {
				params.add(str);
			}
		}
		return params;
	}

	public static List<String> splitByUnderline(String rawStr) {
		List<String> params = new ArrayList<String>();
		String[] strs = rawStr.split("\\|");
		for (String str : strs) {
			if (str != null && !str.equals("")) {
				params.add(str);
			}
		}
		return params;
	}

	public static String toTime(long milliseconds) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date(milliseconds);
		return sdf.format(d);
	}

	public static Date toDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static long toLong(String strLong) {
		return Long.valueOf(strLong);
	}
	public static int toInt(String strInt) {
		return Integer.valueOf(strInt);
	}
	public static double toDouble(String strDouble) {
		return Double.valueOf(strDouble);
	}
	public static Timestamp toTimestamp(String strTimestamp) {
		return Timestamp.valueOf(strTimestamp);
	}
	public static boolean toBoolean(String strBoolean) {
		return Boolean.valueOf(strBoolean);
	}
	public static <T> List<T> convertList2Type(List<String> datas, int type) {
		List<T> list = new ArrayList<T>();
		if (datas != null) {
			for (String data : datas) {
				T t = convert2Type(data, type);
				list.add(t);
			}
		}
		return list;
	}

	private static Pattern digitPattern = Pattern.compile("^[0-9]*$");
	public static int getIntByPattern(String str) {
		Matcher m = digitPattern.matcher(str);
		if (m.find()) {
			return toInt(str);
		}
		return -1;
	}

	public static List<Integer> getIntegers(List<String> pages) {
		List<Integer> nums = new ArrayList<Integer>();
		if (pages != null) {
			for (String page : pages) {
				if (page.equals("")) {
					continue;
				}
				Matcher m = digitPattern.matcher(page);
				if (m.find()) {
					nums.add(toInt(page));
				}
			}
		}
		return nums;
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert2Type(String data, int type) {
		Object obj = null;
		switch (type) {
		case 0:
			obj = data;
			break;
		case 1:
			obj = toInt(data);
			break;
		case 2:
			obj = toLong(data);
			break;
		case 3:
			obj = toDate(data);
			break;
		case 4:
			obj = toBoolean(data);
			break;

		}
		return (T) obj;
	}
	public static void main(String[] args) {
//		System.out.println(isDoubleString("-1.00"));
		
		System.out.println(isIntegerString("2147483648"));
	}
}