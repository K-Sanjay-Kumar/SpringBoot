package com.example.ecommerce.controller;

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

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.Users;
import com.example.ecommerce.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ecommerce/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String homePage() {
		return "Welcome to E-Commerse Users Page";
	}
	
	@PostMapping("/register")
	public Users addUsers(@Valid @RequestBody Users users) {
		return userService.saveUsers(users);
	}
	
	@GetMapping
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/dto")
	public List<UserDTO> getAllUsersDTO() {
		return userService.getAllUsersDTO();
	}
	
	@GetMapping("/{id}")
	public Users getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/dto/{id}")
	public UserDTO getUserDTO(@PathVariable Long id) {
	    return userService.getUserByIdDTO(id);
	}
	
	@PutMapping("/{id}")
	public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
		return userService.updateUserById(id, user);
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return "User deleted successfully";
	}
}
