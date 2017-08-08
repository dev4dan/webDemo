package com.dev4dan.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ClassName BeanInfoCollector.java
 *
 * @Description：该类用于处理Java bean字段相关的内容；
 *
 * @author dan
 *
 * @date 2013-1-25
 */
public class BeanInfoCollector {
	private static Logger logger = Logger.getLogger(BeanInfoCollector.class);
	/**
	 * List<String>
	 * 描述：该变量用于保存基类型的的名称；
	 */
	private static final List<String> primitiveList = new ArrayList<String>();
	static{
		primitiveList.add("int");
		primitiveList.add("long");
		primitiveList.add("double");
		primitiveList.add("float");
		primitiveList.add("bigInteger");
		primitiveList.add(Integer.class.getName());
		primitiveList.add(Long.class.getName());
		primitiveList.add(Double.class.getName());
		primitiveList.add(Float.class.getName());
		primitiveList.add("java.sql.Date");
		primitiveList.add("java.util.Date");
		primitiveList.add(String.class.getName());
		primitiveList.add(Boolean.class.getName());
		primitiveList.add(Short.class.getName());
		primitiveList.add(Character.class.getName());
		primitiveList.add(Byte.class.getName());
	}

	private static final ThreadLocal<BeanInfoCollector> instance = new ThreadLocal<>();
	
	private BeanInfoCollector(){}
	public static BeanInfoCollector getInstance(){
		if(instance.get() == null){
			instance.set(new BeanInfoCollector());
		}
		return instance.get();
	}
	
	/**
	 * @Description：该方法通过接收一个实例，返回该实例类的所有属性信息；
	 * @param t
	 * 指定一个实例；
	 * @return BeanFields
	 */
	public <T> BeanFields getBeanFields(T t) throws IntrospectionException{
		BeanFields beanFields = getBeanFields(t.getClass());
		return beanFields;
	}
	
	/**
	 * Map<String,BeanFields>
	 * 描述：改变量用于保存已处理过的bean的属性信息；
	 */
	private Map<String,BeanFields> beanInfoStore = new ConcurrentHashMap<String,BeanFields>();
	
	/**
	 * @Description：该方法用于获取一个指定bean的属性；
	 * @param clazz
	 * 指定bean的class；
	 * @return BeanFields
	 */
	public BeanFields getBeanFields(Class<?> clazz) throws IntrospectionException{
		String clzName = clazz.getName();
		if(exist(clzName)){
			return beanInfoStore.get(clzName);
		}
		synchronized(beanInfoStore){
			BeanInfo bi = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] descriptors = bi.getPropertyDescriptors();

			List<String> propNames = new ArrayList<String>();
			Map<String,PropertyInfo> fields = new HashMap<String,PropertyInfo>();

			BeanFields beanFields = new BeanFields();

			for(int i=0 ; i<descriptors.length ; i++){
				String name = descriptors[i].getName();
				PropertyInfo propInfo = new PropertyInfo();
				if(name.equals("class")){
					continue;
				}

				Class<?> clz = descriptors[i].getPropertyType();
				logger.info(clz.getName());
				if(!isPrimitive(clz.getName())){
					continue;
				}
				propInfo.setType(clz);

				propNames.add(name);

				Method rmethod = descriptors[i].getReadMethod();
				logger.info(rmethod.getName());
				propInfo.setReadMethod(rmethod);

				Method wmethod = descriptors[i].getWriteMethod();
				logger.info(wmethod.getName());
				propInfo.setWriteMethod(wmethod);

				fields.put(name, propInfo);

			}

			beanFields.setFeildsName(propNames);
			beanFields.setFields(fields);
//		beanFields.setBeanName(getClassName(clazz.getName()));
			beanFields.setBeanName(clazz.getName());

			beanInfoStore.put(clzName, beanFields);
			return beanFields;
		}
	}
	
	/**
	 * @Description：通过bean的名称获取属性；
	 * @param beanName
	 * 指定bean的名称；
	 * @return BeanFields
	 */
	public BeanFields getBeanFields(String beanName){
		BeanFields fields = null;
		try {
			Object obj = Class.forName(beanName).newInstance();
			fields = getBeanFields(obj);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return fields;
	}
	
	/**
	 * @Description：该方法用于获取除ID属性外的所有属性的值；
	 * @param t
	 * 需要获取全部属性值的bean实例；
	 * @return Object[]
	 */
	public <T> Object[] getBeanValuesWithoutID(T t){
		Object[] objs = null;
		try {
			BeanFields fields = getBeanFields(t);
			List<String> names = BeanInfoAssistant.removeBeanIDField(fields.getFeildsName());
			int size = names.size();
			objs = new Object[size];
			int pos = -1;
			for(int i=0 ; i<size ; i++){
				String name = names.get(i);
				if(BeanInfoAssistant.isID(name)){
					pos = i;
					showInfo("getBeanValue . pos : "+pos);
					showInfo("getBeanValue . name : "+name);
					continue;
				}
				PropertyInfo info = fields.getFields().get(name);
				objs[i] = info.getReadMethod().invoke(t, new Object[]{});
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return objs;
	}
	
	/**
	 * @Description：该方法用于获取指定bean实例所有属性的值；
	 * @param t
	 * 需要获取全部属性值的bean实例；
	 * @return Object[]
	 */
	public <T> Object[] getBeanValues(T t){
		Object[] objs = null;
		try {
			BeanFields fields = getBeanFields(t);
			List<String> names = fields.getFeildsName();
			int size = names.size();
			objs = new Object[size];
			int pos = -1;
			for(int i=0 ; i<size ; i++){
				String name = names.get(i);
				if(BeanInfoAssistant.isID(name)){
					pos = i;
					logger.info("pos : "+pos + ", name : "+name);
				}
				PropertyInfo info = fields.getFields().get(name);
				objs[i] = info.getReadMethod().invoke(t, new Object[]{});
			}
			
			Object tmp = new Object();
			tmp = objs[pos];
			for(int i=pos ; i<objs.length-1 ; i++){
				objs[i] = objs[i+1];
			}
			objs[size-1] = tmp;
			
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return objs;
	}
	
	/**
	 * @Description：该方法用于获取指定bean实例的指定属性的值；
	 * @param t
	 * 需要获取指定属性名称的值的bean实例
	 * @param fieldName
	 * 需要获取值的属性名称；
	 * @return Object
	 */
	public <T> Object getBeanSpecifiedVal(T t,String fieldName){
		BeanFields bf = null;
		Object obj = null;
		try {
			bf = getBeanFields(t.getClass());
			Method m = bf.getFields().get(fieldName).getReadMethod();
			obj = m.invoke(t, new Object[]{});
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * @Description：该方法用于把一个bean实例的值复制到另外一个bean之中；
	 * @param dest
	 * 属性需要被赋值的bean实例
	 * @param  orig
	 * 属性拥有值的bean实例
	 * @return boolean
	 */
	public <T> boolean copyBeanValue(T dest,T orig){
		boolean flag = false;
			BeanUtils.copyProperties(dest, orig);
			flag = true;
		return flag;
	}
	
	/**
	 * @Description：该方法用于获取指定bean实例的所有属性名称；
	 * @param t
	 * 需要获取所有属性名称的bean实例；
	 * @return List<String>
	 */
	public <T> List<String> getBeanFieldsName(T t){
		List<String> names = null;
		try {
			names = getBeanFields(t).getFeildsName();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return names;
	}
	
	/**
	 * @Description：该方法用于判断指定类的名称的类型是否为基类型；
	 * @param clazzName
	 * 类的名称；
	 * @return boolean
	 */
	private boolean isPrimitive(String clazzName){
		boolean flag = false;
		for(int i=0 ; i<primitiveList.size() ; i++){
			String permi = primitiveList.get(i);
			if(permi.equals(clazzName)){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * @Description：该方法用于去掉类的包名仅保留类名称；
	 * @param fullName
	 * 类的完整名称；
	 * @return String
	 */
	public String getClassName(String fullName){
		logger.info("getClassName . fullName : "+fullName);
		String[] tmps = fullName.split("\\.");
		int length = tmps.length;
		showInfo("getClassName . length : "+length);
		for(int i=0 ; i<tmps.length ; i++){
			showInfo("getClassName . tmps["+i+"] : "+tmps[i]);	
		}
		return tmps[length-1];
	}
	
	/**
	 * @Description：该方法用于判断指定名称的类是否已处理过
	 * @param clzName
	 * 指定类的名称；
	 * @return boolean
	 */
	private boolean exist(String clzName){
		return beanInfoStore.containsKey(clzName);
	}
	
	private void showInfo(String info){
//		System.out.println("BeanInfoCollector . "+info);
	}
}
