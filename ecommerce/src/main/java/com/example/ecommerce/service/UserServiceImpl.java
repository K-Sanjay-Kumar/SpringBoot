package com.example.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.UserRepository;

import com.example.ecommerce.exception.DuplicateResourceException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDTO saveUsers(Users user) {
		// TODO Auto-generated method stub
		user.setPassword(encoder.encode(user.getPassword()));
		if(userRepo.findByEmail(user.getEmail()) != null){
			throw new DuplicateResourceException("Email already exists");
		}
		userRepo.save(user);
		return userMapper.convertToDTO(user);
	}
	
	@Override
	public List<UserDTO> getAllUsersDTO() {
		List<Users> users = userRepo.findAll();
		List<UserDTO> dtoList = new ArrayList<>();

	    for (Users user : users) {
	        dtoList.add(userMapper.convertToDTO(user));
	    }

	    return dtoList;
	}

	@Override
	public Users getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
	
	@Override
	public UserDTO getUserByIdDTO(Long id) {
		Users user=userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return userMapper.convertToDTO(user);
	}

	@Override
	public UserDTO updateUserById(Long id, Users users) {
		// TODO Auto-generated method stub
		Users existing = getUserById(id);
		existing.setName(users.getName());
		existing.setEmail(users.getEmail());
		existing.setRole(users.getRole());
		existing.setPassword(encoder.encode(users.getPassword()));
		
		userRepo.save(existing);
		return userMapper.convertToDTO(existing);
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		Users user=getUserById(id);
		userRepo.delete(user);
	}

	@Override
	public Users searchUser(String name) {
		// TODO Auto-generated method stub
		return userRepo.findByName(name);
	}
	
}
