package com.dev4dan.utils;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Daniel;
 * @since 2012-07-17
 * 
 * */
public class PropertiesUtil {
	/**
	 * 描述：私有化构造函数，防止该工具类被实例化；
	 * */
	private PropertiesUtil (){}
	//创建一个私有化的输入流；
	private static PropertiesCache cache = PropertiesCache.getInstance();
	//指定一个文件夹名称；
	private static String folderName = "properties/";
	//获取一个properties实例；
	private static Properties getPropertiesFile(){
		return new Properties();
	}
	
	private static Logger log = Logger.getLogger("PropertiesUtilLog.properties");	
	private static void showInfo(String info){
		log.info("PropertiesUtil . "+info);
	}
	/**
	 * @param fileName 指定文件的名称；
	 * @return Properties返回一个指定名称的properties文件；
	 * 描述：该方法用于返回一个指定名称的properties文件；
	 * */
	public static Properties loadProperties(String fileName){
		Properties property;
		if(cache.getProperties(fileName) == null){
			property = getPropertiesFile();
			try {
				FileInputStream inputStream = new FileInputStream(new File(fileName));
				property.load(new InputStreamReader(inputStream, "UTF-8"));
				if (!cache.isExist(fileName)) {
					cache.setProperties(fileName, property);
				}
			} catch (FileNotFoundException e) {
				showInfo("PropertiesUtil . loadProperties this file "+fileName+" doesn't exist!!!");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				showInfo("PropertiesUtil . loadProperties this file "+fileName+" doesn't exist!!!");
			}
		}else{
			property = cache.getProperties(fileName);
		}
		
		return property;
	}
	
	/**
	 * @param fileName 指定文件的名称；
	 * @return String[]返回指定properties文件中所有的值；
	 * 描述：该方法用于返回指定properties文件中的所有值；
	 * */
	public static String[] getAllPropertis(String fileName){
		
		Set<String> keySet = getKeySet(fileName);
		Properties property = loadProperties(fileName);

		String tmp = "";
		int size = keySet.size();
		
		Iterator<String> iter = keySet.iterator();

		String[] props = new String[size];
		for(int i=0 ; i<size && iter.hasNext(); i++){
			tmp = property.getProperty(iter.next());
			if(tmp != null && !tmp.trim().equals("")){
				props[i] = tmp;
			}
		}

		return props;
	}
	/**
	 * @param fileName 指定文件的名称；
	 * @return Set<String>返回指定properties文件的键的集合；
	 * 描述：该方法用于取得指定properties文件中的键集合；
	 * */
	public static Set<String> getKeySet(String fileName){
		Properties property = loadProperties(fileName);

		Set<String> keys = property.stringPropertyNames();
		return keys;
	} 
	/**
	 * @param fileName 指定文件的名称；
	 * @param key 指定目标键名；
	 * @return String返回properties文件指定键名的值；
	 * 描述：该方法用于获取properties文件中指定键名的值；
	 * */
	public static String getProperty(String fileName,String key){
		Properties propertyFile = loadProperties(fileName);
		String value = null; 
		if(propertyFile != null){
			value = propertyFile.getProperty(key);
			if(value != null){
				return value;
			}
			return null;
		}else{
				return null;
		}
	}

	/**
	 * @param fileName 指定文件的名称；
	 * @param key 指定目标键名；
	 * @param value 指定目标键名的值；
	 * 描述：该方法用于保存指定properties文件的键和值；
	 * */
	public static void setProperty(String fileName,String key,String value){
		Properties prop = loadProperties(fileName);
		if(prop == null){
			boolean bln = FileUtils.isFileExist(fileName);
//			showInfo("fileName : "+fileName+" \n key : "+key+" \n value : "+value);
			if(!bln){	//if3
				try{
					throw new Exception("the "+fileName+" file doesn't exist!!");
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}else{
				setProperty(fileName,key,value);
			}
		}else{
//			showInfo("key : "+key+" \nvalue : "+value);
			prop.setProperty(key, value);
			String prjt = FileUtils.getProjectPath();
			String path = prjt+folderName+fileName;
//			showInfo("setProperty . path : "+path);
			output2File(prop,path);
		}
	}

	private static void output2File(Properties prop,String fileName){
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			prop.store(out, "4 saving password!!!");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//out = new BufferedOutputStream(prop.);
	}
	
	public static boolean createProperties(String fileName){
		boolean flag = createProperties(folderName,fileName);
		return flag;
	}
	
	public static boolean createProperties(String folderName,String fileName){
		String folderPath = FileUtils.getProjectPath()+folderName;
//		showInfo("createProperties . folderPath : "+folderName);
		boolean flag = FileUtils.createFile(folderPath, fileName);
		return flag;
	}
	
	/**
	 * @param fileName 指定文件的名称；
	 * @param props 键和值的映射表；
	 * */
	public static void setProperties(String fileName,Map<String,String> props){
		Iterator<String> it = props.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = props.get(key);
			setProperty(fileName,key,value);
		}
	}
}
