package com.dev4dan.utils;

import java.util.Map;

public class Constants {

	public static final int AREA_PROVINCE = 1;
	public static final int AREA_CITY = 2;
	public static final int AREA_DIST_COUNTY = 3;
	public static final String baseLetterDigit = "baseLetterDigit";
	public static final String road = "road";
	public static final String firstName = "firstName";
	public static final String girl = "girl";
	public static final String boy = "boy";
	public static final String email_suffix = "email_suffix";
	public static final String telFirst = "telFirst";

	public static final String pkgPath = "/demo/target/classes/com/dev4dan/utils";

	public static final Map<String, String> envs = System.getenv();

	public static final String PWD = envs.get("PWD");

	private static String defaultPath = null;

	static{
		if(defaultPath == null){
			String[] tmps = Constants.class.getResource("").getPath().split("/");
			StringBuilder path = new StringBuilder();
			for(int i=0 ; i<tmps.length-5 ; i++){
				if(tmps[i] != null && !tmps[i].equals("")){
					path.append("/"+tmps[i]) ;
				}
			}
			defaultPath = path.toString();
		}

	}

	public static String getDefaultProjectPath(){
		return defaultPath;
	}

	public static enum CONDITION{
		OR("OR"), AND("AND"), GROUP_BY("GROUP BY"), HAVING("HAVING"),ORDER_BY("ORDER BY");
		private String condition;
		private CONDITION(String cond){
			this.condition = cond;
		}
		
		public String toString(){
			return condition;
		}
	}
}
