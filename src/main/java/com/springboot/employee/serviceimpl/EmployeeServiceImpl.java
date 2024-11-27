package com.springboot.employee.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepository;
import com.springboot.employee.service.EmployeeService;




@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;
	
	
	public EmployeeServiceImpl(EmployeeRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}


	@Override
	public void addEmployee(Employee employee) {
		repository.save(employee);
		
	}


	@Override
	public Employee getEmpById(long id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp=repository.findById(id);
		Employee employ = null;
		if(emp!=null) {
			employ = emp.get();
		return employ;
		}
		else {
			throw new RuntimeException("Record not found by id : "+id);
		}
			
	}


	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}


	@Override
	public Page<Employee> findByPage(int pageNo, int pageSize, String sortField, String sortOrder) {
		// TODO Auto-generated method stub
		Sort sort=sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		
		return this.repository.findAll(pageable);
	}

}
