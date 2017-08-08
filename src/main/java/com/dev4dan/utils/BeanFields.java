package com.dev4dan.utils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName FeildSetterGetter.java
 *
 * @Description：TODO(这里用一句话描述这个类的作用)
 *
 * @author dan
 *
 * @date 2013-1-25
 */
public class BeanFields {
	private String beanName = null;
	private List<String> feildsName = null;
	private Map<String,PropertyInfo> fields = null;
	public List<String> getFeildsName() {
		return feildsName;
	}
	public void setFeildsName(List<String> feildsName) {
		this.feildsName = feildsName;
	}
	public Map<String, PropertyInfo> getFields() {
		return fields;
	}
	public void setFields(Map<String, PropertyInfo> fields) {
		this.fields = fields;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
}
