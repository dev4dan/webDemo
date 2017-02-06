package com.dev4dan.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dev4dan.utils.CommonCache;

public class SQLProviderHelper {
	
	private Object instance; //需要生成sql的实例，可能是list,Map,set 或者其他的类型
	private Class<?> clz; //对应实例的类型，例如List的元素类型，map的value类型等
	private List<String> filters; //不需要生成的字段
	private String tableName; //对应sql插入表名
	private Map<String, Object> params; //生成SQL的字段值
	private Map<String, Object> conditions; //判断条件，添加规则为，key的选项为OR，AND，GROUP_BY，HAVING，ORDER_BY；value的为List，该list的element格式为condition=param
	
	public Object getInstance() {
		return instance;
	}
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	public Class<?> getClz() {
		return clz;
	}
	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
	public List<String> getFilters() {
		return filters;
	}
	public void setFilters(List<String> filters) {
		this.filters = filters;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public Field[] getFields(){
		Field[] fields = CommonCache.getInstance().getClzFields(clz);
		return fields;
	}
	
	public void addFilter(String filter){
		if(getFilters() == null){
			setFilters(new ArrayList<String>());
		}
		getFilters().add(filter);
	}
	public boolean isFilter(String filter){
		return getFilters().contains(filter);
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public Map<String, Object> getConditions() {
		return conditions;
	}
	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}
}
