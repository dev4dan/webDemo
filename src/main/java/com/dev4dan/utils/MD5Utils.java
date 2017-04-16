package com.dev4dan.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Utils {

	private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * 标准MD5生成
	 * @param SourceString
	 * @return
	 * @throws Exception
     */
	public static String officialEncode32(String SourceString){
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
			if(digest == null){
				return null;
			}
			digest.update(SourceString.getBytes("utf-8"));
			byte messageDigest[] = digest.digest();
			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("加密异常", e);
		}
		return null;
	}

	/**
	 * MD5生成，不标准
	 * @param originString
	 * @return
     */
	public static String encode32(String originString){
		String result="";
		if (originString != null) {
			try {
				// 指定加密的方式为MD5
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 进行加密运算
				byte bytes[] = md.digest(originString.getBytes());
				for (int i = 0; i < bytes.length; i++) {
					// 将整数转换成十六进制形式的字符串 这里与0xff进行与运算的原因是保证转换结果为32位
					String str = Integer.toHexString(bytes[i] & 0xFF);
					if (str.length() == 1) {
						str += "F";
					}
					result += str;
				}
			} catch (NoSuchAlgorithmException e) {
				logger.error("加密异常", e);
			}
		}
		return result.toUpperCase();
	} 
	/**
	 * md5 编码
	 * @param origin
	 * @param charsetname
	 * @return
	 */
	public static String encode(String origin,String charsetname ){  
		String resultString = null;
		try{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if(charsetname == null || "".equals(charsetname))
				resultString = toHexString(md.digest(resultString.getBytes()));
			else
				resultString = toHexString(md.digest(resultString.getBytes(charsetname)));
		}catch(Exception exception){
		}
		return resultString;
	} 
	
	public static void main(String[] args){
		String str = "merchantNo=00008&orderNo=2016090102&key=123456";
		String temp =MD5Utils.encode32(str);
		System.out.println("加密后数据为："+temp);

		System.out.println(officialEncode32(str));
	}
}