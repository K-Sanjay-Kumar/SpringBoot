package com.example.employeemanagement.service;

import java.util.List;

import com.example.employeemanagement.model.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmplyees();
	Employee getEmployeeById(Long id);
	Employee updateEmployee(Long id, Employee employee);
	void deleteEmployee(Long id);
}
