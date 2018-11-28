package com.sec.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sec.DTO.EventDTO;
import com.sec.DTO.UserDTO;
import com.sec.entity.User;



import com.sec.service.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	
	
	
	
	@PostMapping("/registration")
    public String reg(@RequestBody User user,HttpServletRequest request) throws Exception {
		
		
        return userService.registerUser(user,request);
        
        
        
        
    }
	@GetMapping("/users")
	Page<UserDTO> ListUsers(@RequestParam(value = "page", defaultValue="0") int page, @RequestParam( value = "size", defaultValue="20") int size,
			@SortDefault(sort="userName",direction = Sort.Direction.ASC)Sort sort	)
	{
		
		return userService.ListUsers(new PageRequest(page,size,sort));
		
	}
	@GetMapping("/events")
	List<EventDTO> GetAndDeleteEventsOfUser(@AuthenticationPrincipal User user) {
		
		
		return userService.GetAndDeleteEventsOfUser(user);
		
	}
	@GetMapping("/activation/{activationKey}")
	ResponseEntity<String> ActivateUser(@PathVariable String activationKey){
		
		
		return userService.userActivation(activationKey);
		
		
	}
	
	
	
	
}
