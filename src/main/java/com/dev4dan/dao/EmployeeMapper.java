package com.dev4dan.dao;

import java.util.List;

import com.dev4dan.model.Employee;

public interface EmployeeMapper {
	public List<Employee> getEmployeeByName(String name);
	public Employee getEmployeeById(int id);
}
