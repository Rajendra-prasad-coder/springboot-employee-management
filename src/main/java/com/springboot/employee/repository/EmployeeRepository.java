package com.springboot.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
