package com.dev4dan.model.sql;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.dev4dan.model.SQLProviderHelper;
import com.dev4dan.utils.CommonCache;

/**
 * @author daniel
 * @createTime:2016-9-30-上午11:55:14
 * description:sql基础类
 */
public class BaseSqlProvider {
	
	private static CommonCache cache = CommonCache.getInstance();

	/**
	 * 查询结果SQL
	 * 创建人：MoChunrun 
	 * 创建时间：2016年1月19日 上午9:13:41
	 * 描述：
	 * @version v1.0
	 * @param t
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getResultSetSQL(Class clazz){
		return getResultSetSQL(clazz,null);
	}
	@SuppressWarnings("rawtypes")
	public static String getResultSetSQL(Class clazz,String filters){
		try{
			String sql = "";
			Field [] fields = clazz.getDeclaredFields();
			for(Field f:fields){
				String name = f.getName();
				if(filters==null || filters.indexOf(","+name+",")==-1){
					sql += name+",";
				}
			}
			if(sql.length()>2){
				sql = sql.substring(0,sql.length()-1);
			}
			return sql;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("查询出错了");
		}
	}
	
	/**
	 * 获取批量插入值
	 * 创建人：MoChunrun <br>
	 * 创建时间：2016年1月25日 上午11:19:10<br>
	 * 描述：<br>
	 * @version v1.0<br>
	 * @param clazz
	 * @param filters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public SqlResult getBatchInsertValues(Class clazz,String paramName,String filters){
		SqlResult sql = new SqlResult();
		try{
			String insert = "";
			String values = "";
			Field [] fields = cache.getClzFields(clazz);
			for(Field f:fields){
				String name = f.getName();
				String className = f.getType().getSimpleName();
				if(filters==null || filters.indexOf(","+name+",")==-1){
					insert += name+",";
					if("Integer".equalsIgnoreCase(className) || "int".equalsIgnoreCase(className)){
						values += "#'{'"+paramName+"[{0}]."+name+",javaType=INTEGER,jdbcType=INTEGER},";
					}else if("double".equalsIgnoreCase(className)){
						values += "#'{'"+paramName+"[{0}]."+name+",javaType=DOUBLE,jdbcType=DOUBLE},";
					}else if("float".equalsIgnoreCase(className)){
						values += "#'{'"+paramName+"[{0}]."+name+",javaType=FLOAT,jdbcType=FLOAT},";
					}else if("string".equalsIgnoreCase(className)){
						values += "#'{'"+paramName+"[{0}]."+name+",javaType=string,jdbcType=VARCHAR},";
					}else if("date".equalsIgnoreCase(className)){
						values += "#'{'"+paramName+"[{0}]."+name+",javaType=java.util.Date,jdbcType=TIMESTAMP},";
					}else{
						values += "#'{'"+paramName+"[{0}]."+name+"},";
					}
				}
			}
			if(insert.length()>2){
				insert = insert.substring(0,insert.length()-1);
				values = values.substring(0,values.length()-1);
			}
			sql.setInsert(insert);
			sql.setValues(values);
			return sql;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("出错了");
		}
	}
	
	/**
	 * 获取批量更新
	 * 创建人：MoChunrun <br>
	 * 创建时间：2016年1月25日 下午2:49:26<br>
	 * 描述：<br>
	 * @version v1.0<br>
	 * @param clazz
	 * @param tableName 数据库表名
	 * @param filters 过滤器，用于过滤不需要的字段，格式为"field1,field2,field3"
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getBatchSets(Class clazz,String tableName,String filters){
		Field [] fields = clazz.getDeclaredFields();
		String sets = "";
		for(Field f:fields){
			String fieldName = f.getName();
			String className = f.getType().getSimpleName();
			if(filters==null || filters.indexOf(fieldName)==-1){
				if("Integer".equalsIgnoreCase(className) || "int".equalsIgnoreCase(className)){
					sets += fieldName+"=#'{'"+tableName+"[{0}]."+fieldName+",javaType=INTEGER,jdbcType=INTEGER},";
				}else if("double".equalsIgnoreCase(className)){
					sets += fieldName+"=#'{'"+tableName+"[{0}]."+fieldName+",javaType=DOUBLE,jdbcType=DOUBLE},";
				}else if("float".equalsIgnoreCase(className)){
					sets += fieldName+"=#'{'"+tableName+"[{0}]."+fieldName+",javaType=FLOAT,jdbcType=FLOAT},";
				}else if("string".equalsIgnoreCase(className)){
					sets += fieldName+"=#'{'"+tableName+"[{0}]."+fieldName+",javaType=string,jdbcType=VARCHAR},";
				}else if("date".equalsIgnoreCase(className)){
					sets += fieldName+"=#'{'"+tableName+"[{0}]."+fieldName+",javaType=java.util.Date,jdbcType=TIMESTAMP},";
				}else{
					sets += fieldName+"=#'{'"+tableName+"[{0}]."+fieldName+"},";
				}
			}
		}
		if(sets.length()>2){
			sets = sets.substring(0,sets.length()-1);
		}
		
		return sets;
	}
	
	/**
	 * 修改对象转为sql
	 * 创建人：MoChunrun <br>
	 * 创建时间：2016年1月25日 下午2:25:06<br>
	 * 描述：<br>
	 * @version v1.0<br>
	 * @param t
	 * @param beanName
	 */
	public static <T>void sets(T t,String beanName){
		try{
			Field [] fields = t.getClass().getDeclaredFields();
			for(Field f:fields){
				f.setAccessible(true);
				String name = f.getName();
				Object obj = f.get(t);
				if(obj!=null){
					if(obj instanceof Integer){
						SET(name+"=#{"+beanName+"."+name+",javaType=INTEGER,jdbcType=INTEGER}");
					}else if(obj instanceof Double){
						SET(name+"=#{"+beanName+"."+name+",javaType=DOUBLE,jdbcType=DOUBLE}");
					}else if(obj instanceof Float){
						SET(name+"=#{"+beanName+"."+name+",javaType=FLOAT,jdbcType=FLOAT}");
					}else if(obj instanceof String){
						SET(name+"=#{"+beanName+"."+name+",javaType=string,jdbcType=VARCHAR}");
					}else if(obj instanceof Date){
						SET(name+"=#{"+beanName+"."+name+",javaType=java.util.Date,jdbcType=TIMESTAMP}");
					}else{
						SET(name+"=#{"+beanName+"."+name+"}");
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("插入出错了");
		}
	}
	
	/**
	 * 清加values
	 * 创建人：MoChunrun 
	 * 创建时间：2016年1月15日 下午2:32:30
	 * 描述：
	 * @version v1.0
	 * @param t
	 * @param beanName
	 */
	public static <T>void addValue(T t,String beanName){
		try{
			Field [] fields = t.getClass().getDeclaredFields();
			for(Field f:fields){
				f.setAccessible(true);
				String name = f.getName();
				Object obj = f.get(t);
				if(obj!=null){
					if(obj instanceof Integer){
						VALUES(name,"#{"+beanName+"."+name+",javaType=INTEGER,jdbcType=INTEGER}");
					}else if(obj instanceof Double){
						VALUES(name,"#{"+beanName+"."+name+",javaType=DOUBLE,jdbcType=DOUBLE}");
					}else if(obj instanceof Float){
						VALUES(name,"#{"+beanName+"."+name+",javaType=FLOAT,jdbcType=FLOAT}");
					}else if(obj instanceof String){
						VALUES(name,"#{"+beanName+"."+name+",javaType=string,jdbcType=VARCHAR}");
					}else if(obj instanceof Date){
						VALUES(name,"#{"+beanName+"."+name+",javaType=java.util.Date,jdbcType=TIMESTAMP}");
					}else{
						VALUES(name,"#{"+beanName+"."+name+"}");
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("插入出错了");
		}
	}
	
	/**
	 * 根据给定的对象生成插入语句
	 * @param providerHelper
	 * @return
	 */
	public static String getUpdateSQL(SQLProviderHelper providerHelper) {
		Map<String, Object> params = providerHelper.getParams();

		Class<?> clazz = providerHelper.getClz();
		if (clazz == null) {
			try {
				throw new Exception("clazz is null...");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		if (params == null) {
			try {
				throw new Exception("params is null...");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		Field[] fields = cache.getClzFields(clazz);

		String sql = "UPDATE " + providerHelper.getTableName() + " SET ";

		List<String> filters = providerHelper.getFilters();

		int count = 0;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			String name = f.getName();
			if (params.containsKey(name)) {
				if(filters != null && filters.contains(name)){
					continue;
				}
				
				Object obj = params.get(name);
				sql += name + "=" + obj.toString();
				if (count < fields.length - 1) {
					sql += ", ";
				}
			}
			count ++;
		}

		String condition = getWhereSQL(providerHelper);

		if (condition != null) {
			sql += condition;
		}

		return sql;
	}
	
	/**
	 * 获取当前指定对象的SQL条件语句
	 * @param providerHelper
	 * @return
	 */
	public static String getWhereSQL(SQLProviderHelper providerHelper){
		Map<String, Object> conditionsMap = providerHelper.getConditions();
		
		if(conditionsMap != null){
			String whereSQL = " WHERE ";
			String conditions = getConditionSQL(providerHelper.getTableName(), conditionsMap);
			
			if(conditions != null){
				whereSQL += conditions;
			}
			return whereSQL;			
		}
		return null;
	}
	
	/**
	 * @param tableName 数据库表名
	 * @param conditions 该表的判断条件
	 * @return
	 */
	private static String getConditionSQL(String tableName, Map<String, Object> conditions){
		if(conditions == null){
			return null;
		}
		Map<String, Map<String, Object>> table_conditions = new HashMap<String, Map<String,Object>>();
		table_conditions.put(tableName, conditions);
		return getConditionSQL(table_conditions);
	}
	
	/**
	 * 该表用于拼接多个表的判断条件 
	 * @param table_conditions 该map格式为<数据库表名, <判断类型(OR,AND,HAVING,ORDER BY等), 字段和判断值>>
	 * @return
	 */
	private static String getConditionSQL(Map<String, Map<String, Object>> table_conditions){
		if(table_conditions == null){
			return null;
		}
		String conditionSQL = "";
		Iterator<String> tables = table_conditions.keySet().iterator();
		while(tables.hasNext()){
			String table = tables.next();
			Map<String, Object> conditions = table_conditions.get(table);
			Iterator<String> conditionKeys = conditions.keySet().iterator();
			while(conditionKeys.hasNext()){
				String key = conditionKeys.next();
				@SuppressWarnings("unchecked")
				List<String> conditionValue = (List<String>)conditions.get(key);
				int count = 0;
				
				for(int i=0 ; i<conditionValue.size() ; i++){
					// 判断当前是否为第一个条件或者倒数第二个条件，防止多加一个条件判断类型
					if(i>0 && count<(conditionValue.size() -1 )){
						conditionSQL += " " + key + " ";
					}
					// 获取判断条件语句
					conditionSQL += table+"." + conditionValue.get(i);
				}
			}
		}
		return conditionSQL;
	}
	
	public static String getBeanField2SQL(Object t, Field f, String beanName) throws IllegalArgumentException, IllegalAccessException{
		if(f != null && t != null){
			f.setAccessible(true);
			String name = f.getName();
			Object obj = f.get(t);
			String sql = "";
			if(obj!=null){
				if(obj instanceof Integer){
					sql += name +"#{"+beanName+"."+name+",javaType=INTEGER,jdbcType=INTEGER}";
				}else if(obj instanceof Double){
					sql += name +"#{"+beanName+"."+name+",javaType=INTEGER,jdbcType=DOUBLE}";
				}else if(obj instanceof Float){
					sql += name +"#{"+beanName+"."+name+",javaType=INTEGER,jdbcType=FLOAT}";
				}else if(obj instanceof String){
					sql += name +"#{"+beanName+"."+name+",javaType=INTEGER,jdbcType=VARCHAR}";
				}else if(obj instanceof Date){
					sql += name +"#{"+beanName+"."+name+",javaType=INTEGER,jdbcType=TIMESTAMP}";
				}else{
					sql  += name + "#{"+beanName+"."+name+"}";
				}
			}
			return sql;
		}
		return null;
	}
	
	/**
	 * 根据指定的list和表名生成SQL批量插入语句
	 * @param providerHelper
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String getBatchSQL4Insert( SQLProviderHelper providerHelper) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<?> list = (List<?>)providerHelper.getInstance();
		Class<?> clz = providerHelper.getClz();
		String tableName = providerHelper.getTableName();
		if(list!=null){			
			String insertSQL = "";
			insertSQL += "INSERT INTO " + tableName + "(";
			
			Field[] fields = cache.getClzFields(clz);
			if(fields == null){
				return null;
			}
			int count0 = 0;
			for(Field field : fields){
				
				String name = field.getName();
				if(name.equals("class") || providerHelper.isFilter(name)){
					continue;
				}
				
				insertSQL += name ;
				// 判断是否到了字段数组长度-1，防止多加一个","
				if(count0 < (fields.length-1 - providerHelper.getFilters().size())){
					insertSQL += ",";
					count0 ++;
				}
			}
			insertSQL += ") VALUES ";
			
			for(int i=0 ; i<list.size(); i++){
				Object obj = list.get(i);
				Map<String, String> describe = BeanUtilsBean.getInstance().describe(obj);
				insertSQL += "(";
				
				int count = 0;
				for( Field field: fields){
					String name = field.getName();
					// 如果获取到的字段名称为class就跳过
					if(name.equals("class")  || providerHelper.isFilter(name)){
						continue;
					}
					String value = describe.get(name);
					insertSQL += "'"+value +"'" ;
					if(count < (fields.length-1- providerHelper.getFilters().size())){
						insertSQL += ",";
						count ++;
					}
				}
				insertSQL += ")";
				// 判断是否到了list长度-1，防止多加一个","
				if(i < list.size()-1){
					insertSQL +=  ",";
				}
			}
			
			return insertSQL;
		}
		return null;
	}

	/**
	 * 根据给定的实例生成插入语句
	 * @param instance
	 * @return
	 */
	public static String getInstanceSQL4Insert(Object instance, String tableName){
		if(instance != null){
			BEGIN();
			INSERT_INTO(tableName);
			try {
				Map<String, String> describe = BeanUtilsBean.getInstance().describe(instance);
				Iterator<String> it = describe.keySet().iterator();
				while( it.hasNext() ){
					String prop = it.next();
					// 如果获取到的字段名称为class就跳过
					if(prop.equals("class")){
						continue;
					}
					
					String value = describe.get(prop);
					VALUES(prop, value);
				}
				return SQL();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	/**
	 * sql对象
	 * 创建人：MoChunrun <br>
	 * 创建时间：2016年1月25日 下午2:25:31<br>
	 * 描述：<br>
	 * @version v1.0<br>
	 */
	public static class SqlResult{
		private String insert;
		private String values;
		public String getInsert() {
			return insert;
		}
		public void setInsert(String insert) {
			this.insert = insert;
		}
		public String getValues() {
			return values;
		}
		public void setValues(String values) {
			this.values = values;
		}
	}
	

}
