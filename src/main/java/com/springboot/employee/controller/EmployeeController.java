package com.springboot.employee.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.employee.model.Employee;
import com.springboot.employee.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}

	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		return pagination(1,"firstName", "ASC",  model);
	}
	
	@GetMapping("/showemployeeform")
	public String addEmployee(Model model) {
		Employee empl=new Employee();
		model.addAttribute("employee", empl);
		return "create_employee";
	}
	
	@PostMapping("/save/employee")
	public String saveEmployee(@ModelAttribute(name="employee") Employee employee) {
		service.addEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/updateemployee/{id}")
	public String updateEmployee(@PathVariable long id ,Model model) {
		Employee employee=service.getEmpById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	@GetMapping("/deleteemployee/{id}")
	public String deleteEmployee(@PathVariable long id) {
		service.deleteEmployee(id);
		return "redirect:/employees";
	}
	
	@GetMapping("/page/{pageNo}")
	public String pagination(@PathVariable(value="pageNo") int pageNo,
			 @RequestParam(name="sortField") String sortField, 
			 @RequestParam(name="sortOrder") String sortOrder,
			 Model model) {
		int pageSize = 3;
		Page<Employee> page=service.findByPage(pageNo, pageSize, sortField, sortOrder);
		List<Employee> list=page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortOrder", sortOrder);
		model.addAttribute("reversesortOrder", sortOrder.equals("ASC")?"DESC":"ASC");
		
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("employeelist", list);
		return "index";
	}
	
}
