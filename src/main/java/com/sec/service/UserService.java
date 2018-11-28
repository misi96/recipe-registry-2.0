package com.sec.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.sec.DTO.EventDTO;
import com.sec.DTO.UserDTO;
import com.sec.entity.User;

public interface UserService {
	
	public String registerUser(User user, HttpServletRequest request) throws Exception;

	public User findByEmail(String email);

	public ResponseEntity<String> userActivation(String code);

	public Page<UserDTO> ListUsers(Pageable pageable);

	List<EventDTO> GetAndDeleteEventsOfUser(User user);
	
}
