package com.dev4dan.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * @author Daniel;
 * @since 2012-08-03；
 * 描述：各个方法具体作用参考ICache接口；
 */
public abstract class CacheTemplate<V> {

	private CopyOnWriteArrayList<String> keys = new CopyOnWriteArrayList<String>();
	
	private Logger log = Logger.getLogger("CacheTemplateLog.properties");
	
	private void showInfo(String info){
		//log.info("CacheTemplate . "+info);
	}
	private final ConcurrentMap<String,V> cache = new ConcurrentHashMap<String,V>();
	
	public boolean setCacheInfo(String cacheID,
			V cacheInfo) throws Throwable {
		boolean flag = false;
		synchronized(keys){
			showInfo("setCacheInfo cacheID : "+cacheID+" , cacheInfo : "+cacheInfo.getClass().getName());
			if(!isExist(cacheID)){
				cache.put(cacheID, cacheInfo);
				addKey2List(cacheID);
				flag = true;
			}else{
				throwException("the cache info has been existed!!!该缓存信息已经存在");
			}			
		}
		return flag;
	}
	
	private void addKey2List(String key){
		synchronized(keys){
			keys.add(key);
		}
	}

	public V getCacheInfo(String cacheID)throws Throwable {
		V value = null;
		synchronized(keys){
			if(isExist(cacheID)){
				showInfo("getCacheInfo cacheID : "+cacheID);
				value = cache.get(cacheID);
			}
		}
		return value;
	}

	public boolean removeCacheInfo(String cacheID)
			throws Throwable {
		boolean flag = false;
		synchronized(keys){
			if(isExist(cacheID)){
				cache.remove(cacheID);
				removeKey(cacheID);
				flag = true;
			}else{
				throwException("the specified cache info doesn't exist,can't remove!!!!指定缓存信息不存在，无法删除！！");
			}			
		}
		return flag;
	}
	
	/**
	 * @Description：该方法用于移除指定的key
	 * @param key
	 * 指定需要移除的key
	 * @return boolean
	 */
	private boolean removeKey(String key){
		boolean flag = false;
		synchronized(keys){
			flag = keys.remove(key);
		}
		return flag;
	}

	public int getCacheSize() {
		int size = 0;
		synchronized(keys){
			size = cache.size();	
		}
		return size;
	}

	public Map<String,V> getAllCaches() {
		Map<String,V> map = null;
		map = cache;
		return map;
	}

	public abstract boolean isAvailable();

	public boolean replaceCacheInfo(String cacheID,V newValue) throws Throwable {
		
		boolean flag = false;
		synchronized(keys){
			cache.replace(cacheID, newValue);
			flag = true;
		}
		
		return flag;
	}

	public boolean isExist(String key) {
		boolean flag = false;
		showInfo("isExist key : "+key);
		synchronized(keys){
			for(int i=0 ; i<keys.size() ; i++){
				if(keys.get(i).equals(key)){
					flag = true;
					break;
				}
			}
			if(flag){
				showInfo("isExist the keys list contains the specified key !!!");		
			}else{
				//showInfo("isExist the keys list doesn't contain the specified key !!!");
			}
		}
		return flag;
	}

	public boolean clearCache() {
		boolean flag = false;
		try{
			synchronized(keys){
				cache.clear();
				clearKeys();
				flag = true;
			 }	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * @Description：该方法用于清除缓存的所有key；
	 * @return boolean
	 */
	private boolean clearKeys(){
		boolean flag = false;
		synchronized(keys){
			keys.clear();
			if(keys.size() == 0){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * @Description：该方法用于获取全部的key；
	 * @return List<String>
	 */
	public List<String> getAllKeys(){
		List<String> list = null;
		synchronized(keys){
			list = keys;
		}
		return list;
	}
	
	private String isOK = "OK";
	
	public void isNotOK(){
		synchronized(isOK){
			isOK = "notOK";	
		}
	}
	
	public boolean getOK(){
		synchronized(isOK){
			if(isOK.equals("OK")){
				return true;
			}
		}
		return false;
	}
	
	public void isOK(){
		synchronized(isOK){
			isOK = "OK";	
		}
	}
	
	public void throwException(String info){
		try{
			throw new Exception(info);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
