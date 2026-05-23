package com.example.employeemanagement.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@GetMapping("/home")
	public String homePage() {
		return "welcome to home page";
	}
	
	@PostMapping
	public Department addDepartment(@Valid @RequestBody Department department) {
		return service.saveDepartment(department);
	}
	
	@GetMapping
	public List<Department> getAllDepartments(){
		return service.getAllDepartments();
	}
	
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable Long id) {
		return service.getDepartmentById(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepartmentById(Long id) {
		service.deleteDepartment(id);
		return "Department deleted successfully";
	}
	
}
