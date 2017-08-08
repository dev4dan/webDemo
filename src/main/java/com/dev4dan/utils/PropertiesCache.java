package com.dev4dan.utils;

import java.util.Properties;

public class PropertiesCache extends CacheTemplate<Properties> {
	
	private PropertiesCache(){}
	private static PropertiesCache instance = null;
	synchronized public static PropertiesCache getInstance(){
//		System.out.println("PropertiesCache . getInstance start!!");
		
		if(instance == null){
			instance = new PropertiesCache();
//			System.out.println("PropertiesCache . getInstance end!!");
		}			
		return instance;
	}
	
	public Properties getProperties(String fileName){
		Properties prop = null;
		try {
			prop = getCacheInfo(fileName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public void setProperties(String fileName,Properties prop){
		try {
			setCacheInfo(fileName,prop);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void removeProperties(String fileName){
		try {
			removeCacheInfo(fileName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isAvailable() {
		return false;
	}
}
