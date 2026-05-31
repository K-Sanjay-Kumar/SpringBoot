package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.Users;

public interface UserService {
	UserDTO saveUsers(Users users);
	List<UserDTO> getAllUsersDTO();
	Users getUserById(Long id);
	UserDTO updateUserById(Long id, Users users);
	void deleteUserById(Long id);
	Users searchUser(String name);
	UserDTO getUserByIdDTO(Long id);
}
