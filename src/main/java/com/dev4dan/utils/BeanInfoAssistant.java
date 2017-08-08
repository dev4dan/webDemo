package com.dev4dan.utils;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BeanInfoAssistant {
	private static BeanInfoCollector collector = BeanInfoCollector.getInstance();
	
	/**
	 * @Description：该方法用于查看指定字段的名称是否带id
	 * @param name
	 * 指定字段名称；
	 * @return boolean
	 */
	public static boolean isID(String name){
		if(name.startsWith("PK_") || name.endsWith("ID") || name.endsWith("IDPK")){
			return true;
		}
		return false;
	}
	
	/**
	 * @Description：该方法用于移除名称带ID的属性；
	 * @param names
	 * 需要移除属性的列表；
	 * @return List<String>
	 */
	public static List<String> removeBeanIDField(List<String> names){
		List<String> withoutID = new ArrayList<String>();
		for(int i=0 ; i<names.size() ; i++){
			String name = names.get(i);
			if(BeanInfoAssistant.isID(name)){
				continue;
			}
			withoutID.add(name);
		}
		
		return withoutID;
	}
	
	/**
	 * @Description：该方法用于处理两个有相同字段的pojo属性值互相复制；
	 * @param fields
	 * 需要复制值相同的字段名称
	 * @param source
	 * 值来源的pojo
	 * @param target
	 * 值设置的目标
	 * @return boolean
	 */
	public static <T,V> boolean setBeanValueByRules(List<String> fields,T source,V target){
		Map<String,String> rl = new HashMap<String,String>();
		for(int i=0 ; i<fields.size() ; i++){
			rl.put(fields.get(i), fields.get(i));
		}
		return setBeanValueByRules(rl,source,target);
	}
	
	/**
	 * @Description：该方法用于处理两个有相同字段的pojo属性值互相复制；
	 * @param rules
	 * 需要复制值相同的字段名称(左边是值来源的pojo字段，右边是设置值的目标pojo。);
	 * @param source
	 * 值来源的pojo
	 * @param target
	 * 值设置的目标
	 * @return boolean
	 */
	public static <T,V> boolean setBeanValueByRules(Map<String,String> rules,T source,V target){
		boolean flag = false;
		try {
			BeanFields sourceInfo = collector.getBeanFields(source);
			BeanFields targetInfo = collector.getBeanFields(target);
			Map<String,PropertyInfo> sourceFields = sourceInfo.getFields();
			Map<String,PropertyInfo> targetFields = targetInfo.getFields();
			Iterator<String> names = rules.keySet().iterator();
			while(names.hasNext()){
				String name = names.next();
				PropertyInfo propInfo = sourceFields.get(name);
				Method rm = propInfo.getReadMethod();
				Object value = rm.invoke(source, null);
				PropertyInfo tgtInfo = targetFields.get(rules.get(name));
				if(tgtInfo == null){
					throw new Exception(target.getClass().getName()+" field : "+name+" doesn't exist!");
				}
				Method wm = tgtInfo.getWriteMethod();
				wm.invoke(target, value);
			}
			flag = true;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @Description：该方法用于复制一个带值的pojo对象；
	 * @param source
	 * 需要copy值的对象；
	 * @return Object
	 */
	public static <T> Object setBeanValueByRules(T source){
		Class<?> clz = source.getClass();
		Object target = null;
		try {
			 target = clz.newInstance();
			 
			List<String> rules = getBeanFieldsList(source);
			setBeanValueByRules(rules,source,target);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return target;
	}
	
	/**
	 * @Description：该方法用于获取一个指定对象的类所有属性名称；
	 * @param source
	 * 想要获取属性名称的对象；
	 * @return List<String>
	 */
	public static <T> List<String> getBeanFieldsList(T source){
		BeanFields fields = null;
		List<String> rules = null;
		try {
			fields = collector.getBeanFields(source);
			rules = fields.getFeildsName();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return rules;
		
	}
	
	public static <T> void setValues2Target(Map<String,Object> values,T t){
		String methodName = "setValues2Target . ";
		BeanFields fields = null;
		try {
			fields = collector.getBeanFields(t);
			if(fields != null){
				Map<String,PropertyInfo> properties =  fields.getFields();
				Iterator<String> keys = values.keySet().iterator();
				while(keys.hasNext()){
					String key = keys.next();
					showInfo(methodName+"key : "+key);
					PropertyInfo prop = properties.get(key);
					Method method = prop.getWriteMethod();
					method.invoke(t, values.get(key));
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}
	
	public static <T> void setValues2Bean(Map<String,String> values,T t){
		String methodName = "setValues2Target . ";
		BeanFields fields = null;
		try {
			fields = collector.getBeanFields(t);
			if(fields != null){
				Map<String,PropertyInfo> properties =  fields.getFields();
				Map<String,Object> vals = new HashMap<String,Object>();
				Iterator<String> keys = values.keySet().iterator();
				while(keys.hasNext()){
					String key = keys.next();
					PropertyInfo prop = properties.get(key);
					Method method = prop.getWriteMethod();
					Class<?>[] clz = method.getParameterTypes();
					Object obj = convertString(clz[0].getName(),values.get(key));
					if(obj != null){
						vals.put(key, obj);
					}
					showInfo(methodName + "method Type : "+clz[0].getName());
				}
				setValues2Target(vals,t);
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}				
	}

	public static <T> Map<String, Object> setBean2Map(T target) throws InvocationTargetException, IllegalAccessException {
		String methodName = "setValues2Target . ";
		BeanFields fields = null;
		Map<String,Object> vals = new HashMap<String,Object>();
		try {
			fields = collector.getBeanFields(target);
			if(fields != null){
				Map<String,PropertyInfo> properties =  fields.getFields();
				Iterator<String> keys = properties.keySet().iterator();
				while(keys.hasNext()){
					String key = keys.next();
					PropertyInfo prop = properties.get(key);
					Method method = prop.getReadMethod();
					Class<?>[] clz = method.getParameterTypes();
					Object val = method.invoke(target, null);
					vals.put(key, val);
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return vals;
	}

	private static String longType = "java.lang.Long";
	private static String doubleType = "java.lang.Double";
	private static String integerType = "java.lang.Integer";
	private static String dateType = "java.util.Date";
	private static String stringType = "java.lang.String";
	private static String timestampType = "java.sql.Timestamp";
	private static String booleanType = "java.lang.Boolean";
	
	private static Object convertString(String type,String data){
		
		if(longType.equals(type)){
			showInfo("type : "+type);
			return GeekStringUtils.toLong(data);
		}
		if(doubleType.equals(type)){
			showInfo("type : "+type);
			return GeekStringUtils.toDouble(data);
		}
		if(integerType.equals(type)){
			showInfo("type : "+type);
			return GeekStringUtils.toInt(data);
		}
		if(dateType.equals(type)){
			showInfo("type : "+type);
			return GeekStringUtils.toDate(data);
		}
		if(stringType.equals(type)){
			return data;
		}
		if(timestampType.equals(type)){
			return GeekStringUtils.toTimestamp(data);
		}
		if(booleanType.equals(type)){
			return GeekStringUtils.toBoolean(data);
		}
		return null;
	}
	
	private static void showInfo(String info){
//		System.out.println("BeanInfoAssistant . "+info);
	}
}
