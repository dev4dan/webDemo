package com.dev4dan.dataFactory;

import com.dev4dan.utils.Constants;
import com.dev4dan.utils.FileUtils;
import com.dev4dan.utils.PropertiesUtil;

import java.util.*;

/**
 * 随机生成中文姓名，性别，Email，手机号，住址 
 * @author X-rapido 
 */ 
public class RandomInfo {

    public static String base = null;
    private static String firstName = null;
    private static String girl = null;
    private static String boy = null;
    private static String[] road = null;
    private static String[] email_suffix = null;
    private static String[] telFirst = null;

    static{
        String path = FileUtils.getRootDir(Constants.pkgPath,
                "/")
                +"/basicEmployeeInfo.properties";
        Properties properties = PropertiesUtil.loadProperties(path);
        base = ((String)properties.get(Constants.baseLetterDigit));
        road = ((String)properties.get(Constants.road)).split(",");
        firstName = ((String)properties.get(Constants.firstName));
        girl = ((String)properties.get(Constants.girl));
        boy = ((String)properties.get(Constants.boy));
        email_suffix = ((String)properties.get(Constants.email_suffix)).split(",");
        telFirst = ((String)properties.get(Constants.telFirst)).split(",");
    }

    public static int getNum(int start,int end) {  
        return (int)(Math.random()*(end-start+1)+start);  
    }  
       
    /** 
     * 返回Email 
     * @param lMin 最小长度 
     * @param lMax 最大长度 
     * @return 
     */ 
    public static String getEmail(int lMin,int lMax) {  
        int length=getNum(lMin,lMax);  
        StringBuffer sb = new StringBuffer();       
        for (int i = 0; i < length; i++) {       
            int number = (int)(Math.random()*base.length());  
            sb.append(base.charAt(number));       
        }  
        sb.append(email_suffix[(int)(Math.random()*email_suffix.length)]);  
        return sb.toString();     
    }  
   
    /** 
     * 返回手机号码 
     */ 
    public static String getTel() {
        int index=getNum(0,telFirst.length-1);  
        String first=telFirst[index];  
        String second=String.valueOf(getNum(1,888)+10000).substring(1);  
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);  
        return first+second+thrid;  
    }  
       
    /** 
     * 返回中文姓名 
     */ 
    private static String name_sex = "";  
    public static String getChineseName() {
        int index=getNum(0, firstName.length()-1);  
        String first=firstName.substring(index, index+1);  
        int sex=getNum(0,1);  
        String str=boy;  
        int length=boy.length();  
        if(sex==0){  
            str=girl;  
            length=girl.length();  
            name_sex = "女";  
        }else {  
            name_sex="男";  
        }  
        index=getNum(0,length-1);  
        String second=str.substring(index, index+1);  
        int hasThird=getNum(0,1);  
        String third="";  
        if(hasThird==1){  
            index=getNum(0,length-1);  
            third=str.substring(index, index+1);  
        }  
        return first+second+third;  
    }  
       
    /** 
     * 返回地址 
     * @return 
     */ 
    public static String getRoad() {
        int index=getNum(0,road.length-1);  
        String first=road[index];  
        String second=String.valueOf(getNum(11,150))+"号";  
        String third="-"+getNum(1,20)+"-"+getNum(1,10);  
        return first+second+third;  
    }  
       
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {  
//          System.out.println(getEmailName(6,9));
        }  
    }  
}