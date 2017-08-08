package com.dev4dan.utils;
import java.lang.reflect.Method;

/**
 * @ClassName PropertyInfo.java
 *
 * @Description：TODO(这里用一句话描述这个类的作用)
 *
 * @author dan
 *
 * @date 2013-1-25
 */
public class PropertyInfo {
	private String propertyName = null;
	private Class<?> type = null;
	private Method readMethod = null;
	private Method writeMethod = null;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public Method getReadMethod() {
		return readMethod;
	}
	public void setReadMethod(Method readMethod) {
		this.readMethod = readMethod;
	}
	public Method getWriteMethod() {
		return writeMethod;
	}
	public void setWriteMethod(Method writeMethod) {
		this.writeMethod = writeMethod;
	}
	
}
