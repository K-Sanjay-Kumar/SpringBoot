package com.example.ecommerce.mapper;

import org.springframework.stereotype.Component;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.Users;

@Component
public class UserMapper {
	public UserDTO convertToDTO(Users user) {
		UserDTO dto=new UserDTO();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole());
		
		return dto;
	}
}
