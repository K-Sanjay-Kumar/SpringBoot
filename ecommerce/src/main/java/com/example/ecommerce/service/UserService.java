package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.Users;

public interface UserService {
	Users saveUsers(Users users);
	List<Users> getAllUsers();
	Users getUserById(Long id);
	Users updateUserById(Long id, Users users);
	void deleteUserById(Long id);
	Users searchUser(String name);
	UserDTO getUserByIdDTO(Long id);
	List<UserDTO> getAllUsersDTO();
}
