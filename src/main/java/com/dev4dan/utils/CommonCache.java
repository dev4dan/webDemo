package com.dev4dan.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonCache {
	
	// 公共缓存为多线程共享单例
	private static class CommonCacheHolder{
		public static final CommonCache instance = new CommonCache();
	}
	
	private CommonCache(){}
	
	public static CommonCache getInstance(){
		return CommonCacheHolder.instance;
	}
	
	private static final Map<String, Field[]> clzInfos = new ConcurrentHashMap<String, Field[]>(); //用来保存bean的字段
	
	public Field [] getClzFields(Class<?> clz){
		if(clz == null){			
			throw new NullPointerException();
		}
		String clzName = clz.getName();
		if(clzInfos.containsKey(clzName)){
			return clzInfos.get(clzName);
		}else{
			Field [] fields = clz.getDeclaredFields();
			clzInfos.put(clzName, fields);
			return fields;
		}
		
	}
}
