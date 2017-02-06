package com.dev4dan.model.sql;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.dev4dan.model.Employee;

/**
 * 
 * 创建人：MoChunrun <br>
 * 创建时间：2016年1月25日 上午10:15:02<br>
 * 描述：<br>
 * 
 * @version v1.0<br>
 */

public class SqlProvider extends BaseSqlProvider {

	public static final DateFormat DF = new SimpleDateFormat("yyyyMM");

	public int insertEmployee(Map<String, Object> params){
		Employee employee = (Employee ) params.get("employees");
		BEGIN();
		
		return -1;
	}
	
	public int deleteEmployeeByIds(List<Integer> ids){
		return -1;
	}
	
	public int updateEmployees(List<Employee> employees){
		return -1;
	}

	public List<Employee> queryEmployeByName(){
		return null;
	}
	
	public List<Employee> queryEmployeByIds(Map<String, Object> params){
		
		
		
		
		
		return null;
	}
	
}