package com.sec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sec.DTO.UserDTO;
import com.sec.entity.User;

public interface UserService {
	
	public String registerUser(User user);

	public User findByEmail(String email);

	public String userActivation(String code);

	public Page<UserDTO> ListUsers(Pageable pageable);
	
}
