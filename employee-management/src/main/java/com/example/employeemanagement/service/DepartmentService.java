package com.example.employeemanagement.service;

import java.util.List;

import com.example.employeemanagement.model.Department;

public interface DepartmentService {
	Department saveDepartment(Department department);
	List<Department> getAllDepartments();
	Department getDepartmentById(Long id);
	void deleteDepartment(Long id);
}
