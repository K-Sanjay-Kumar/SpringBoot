package com.example.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return empService.saveEmployee(employee);
	}
	
	@GetMapping
	public List<Employee> getEmployees(){
		return empService.getAllEmplyees();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return empService.getEmployeeById(id);
	}
	
	@PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return empService.updateEmployee(id, employee);
    }
	
	@DeleteMapping("/{id}")
	public String delteEmplyeeById(@PathVariable Long id) {
		empService.deleteEmployee(id);
		return "Emplyee Deleted Successfully";
	}
	
}
