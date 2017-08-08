package com.dev4dan.dataFactory;


import com.dev4dan.utils.Constants;
import com.dev4dan.utils.DateUtils;
import com.dev4dan.utils.FileUtils;
import com.dev4dan.utils.PropertiesUtil;

import java.text.ParseException;
import java.util.*;

/**
 * 
 * 身份证算法实现
 * 
* 1、号码的结构 公民身份号码是特征组合码，
*     由十七位数字本体码和一位校验码组成。
* 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码  三位数字顺序码和一位数字校验码。 
* 
* 2、地址码(前六位数） 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 
* 
* 3、出生日期码（第七位至十四位） 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 
* 
* 4、顺序码（第十五位至十七位） 
*    表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。 
* 
* 5、校验码（第十八位数） 
*   （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 
* ，先对前17位数字的权求和
*  Ai:表示第i位置上的身份证号码数字值
*   Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 
* （2）计算模 Y = mod(S, 11) 
* （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7  8 9 10 
*   校验码: 1 0 X 9 8 7 6 5 4 3 2 
* 
* 
 * 
 * @author longgangbai
 *
 */
public class IdCardGenerator {

    public static final Map<String, Integer> areaCode = new HashMap<>();

    private static final Map<Integer, String> provinces = new HashMap<>();

    static {
        String path = FileUtils.getRootDir(Constants.pkgPath,
                "/")
                +"/newAreaCode.properties";

        Properties properties = PropertiesUtil.loadProperties(path);
        Set keys = properties.keySet();
        Iterator it = keys.iterator();
        while(it.hasNext()){
            Object key = it.next();
            provinces.put(Integer.valueOf((String)key), (String)properties.get(key));
        }
    }

    public static Map<Integer, List<Area>> getProvincesGroup(){
        Map<Integer, String> testMap = new HashMap<>();
        int provinceCode = 110000; // 省份编号
        int provinceStep = 10000; // 省份编号间隔
        int cityStep = 100; // 城市编号间隔
        int cityLimit = 99; // 每个省份城市数量限制
        int distLimit = 88; // 每个城市区限制

        Map<Integer, List<Area>> areas = new HashMap<>();

        String province;
        int prov = 1;

        // 本方法用于将身份证区号做分组，先将省分组，再将对应的城市加入到省列表中，最后将县或者去放入到相应的城市列表
        while( provinceCode < 830000){
            province = provinces.get(provinceCode);
            if(province != null && !province.trim().equals("")){
                testMap.put(provinceCode, province);

                if(!areas.containsKey(prov)){
                    areas.put(prov, new ArrayList<Area>());
                }

                // 将省份放入同一个列表中
                List<Area> provinceList = areas.get(prov);
                Area provinceInfo = new Area();
                provinceInfo.setCode(provinceCode);
                provinceInfo.setName(province);
                provinceInfo.setType(Constants.AREA_PROVINCE);
                provinceList.add(provinceInfo);

                String city;
                int cityCode = provinceCode + cityStep;
                if(!areas.containsKey(provinceCode)){
                    areas.put(provinceCode, new ArrayList<Area>());
                }
                List<Area> cityList = areas.get(provinceCode);

                int cityCounter = 0;
                while(cityCounter < cityLimit){
                    city = provinces.get(Integer.valueOf(cityCode));
                    if(city != null && !city.trim().equals("")){
                        testMap.put(cityCode, city);

                        // 将城市放入其对应的省份
                        Area cityArea = new Area();
                        cityArea.setCode(cityCode);
                        cityArea.setName(city);
                        cityArea.setType(Constants.AREA_CITY);
                        cityList.add(cityArea);

                        int areaCode = cityCode;
                        int tmp = 0;

                        if(!areas.containsKey(cityCode)){
                            areas.put(cityCode, new ArrayList<Area>());
                        }

                        List<Area> distList = areas.get(cityCode);

                        while( tmp < distLimit ){
                            areaCode += 1 ;
                            String area = provinces.get(areaCode);

                            if(area != null){
                                testMap.put(areaCode, area);
                                //将城市的区或者县放入到区列表
                                Area distArea = new Area();
                                distArea.setCode(areaCode);
                                distArea.setName(area);
                                distArea.setType(Constants.AREA_DIST_COUNTY);
                                distList.add(distArea);
                            }
                            tmp ++;
                        }
                    }
                    cityCounter ++;
                    cityCode += cityStep;
                }
            }
            provinceCode += provinceStep;
        }

        testAreaGroup(testMap, areas);
        return areas;
    }

    public static void testAreaGroup(Map<Integer, String> testMap, Map<Integer, List<Area>> areas){

        System.out.println("testMap size : " + testMap.size());
        System.out.println("provinces size : " + provinces.size());
        List<Area> list = areas.get(110100);

        for(Area a : list){
            System.out.println(a.getCode() + " : " + a.getName());
        }


        Iterator<Integer> it = provinces.keySet().iterator();
        while(it.hasNext()){
            int key = it.next();
            if(testMap.get(key) == null){
                System.out.println(key + " : "+provinces.get(key));
            }
        }
    }

    public static String generate() {
        StringBuilder generator = new StringBuilder();
        generator.append(randomAreaCode_1());
        generator.append(randomBirthday());
        generator.append(randomCode());
        generator.append(calcTrailingNumber(generator.toString().toCharArray()));
        return generator.toString();
    }

    public static int randomAreaCode() {
        int index = (int) (Math.random() * IdCardGenerator.areaCode.size());
        Collection<Integer> values = IdCardGenerator.areaCode.values();
        Iterator<Integer> it = values.iterator();
        int i = 0;
        int code = 0;
        while (i < index && it.hasNext()) {
            i++;
            code = it.next();
        }
        return code;
    }

    public static int randomAreaCode_1() {
        List<Integer> provincesList = new ArrayList<>(provinces.keySet());
        Random random = new Random();
        int index = random.nextInt(provincesList.size());

        int code = provincesList.get(index);
        return code;
    }

    public static Date randomBirthday2Date(){
        try {
            String date = randomBirthday();
            char[] chars = date.toCharArray();
            StringBuilder builder = new StringBuilder();
            for(int i=0 ; i<chars.length ; i++){
                if(i==4 || i==6 ){
                    builder.append("-"+chars[i]);
                }else{
                    builder.append(chars[i]);
                }
            }

            return DateUtils.convertStrToDate(builder.toString()+" 00:00:00", DateUtils.format_yyMMdd_HHmmss);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /*
     * <p>18位身份证验证</p>
     * 根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * 第十八位数字(校验码)的计算方法为：
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2.将这17位数字和系数相乘的结果相加。
     * 3.用加出来和除以11，看余数是多少？
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     */
    public static char calcTrailingNumber(char[] chars) {
        if (chars.length < 17) {
            return ' ';
        }
        int[] c = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] r = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }

    public static String randomCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }

    public static void main(String[] args) {
        IdCardGenerator generator = new IdCardGenerator();
        System.out.println(IdCardGenerator.provinces.size());
        System.out.println(IdcardUtils.validateCard(generator.generate()));
        for (int i = 0; i < 10; i++) {
            System.out.print("\t");
            System.out.print(generator.generate());
            System.out.print("\t");
            System.out.print(generator.generate());
            System.out.print("\t");
            System.out.println(generator.generate());
        }
    }

    public static class Area{
        private int code;
        private String name;
        private int type;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}