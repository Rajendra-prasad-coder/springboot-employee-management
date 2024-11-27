package com.springboot.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.employee.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	void addEmployee(Employee employee);
	Employee getEmpById(long id);
	void deleteEmployee(long id);
	Page<Employee> findByPage(int pageNo, int pageSize, String sortField, String sortOrder);
}
