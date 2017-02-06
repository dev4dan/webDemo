package com.dev4dan.model.mapper;

import java.util.List;
import java.util.Map;

import com.dev4dan.model.Employee;

public interface EmployeeMapper {

	public List<Employee> getEmployeesById(Map<String, Object> params);
}
