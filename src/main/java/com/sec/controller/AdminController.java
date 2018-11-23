package com.sec.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sec.DTO.CircularEmail;
import com.sec.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	
	
	@DeleteMapping("/users/{userID}")
	int DeleteUser(@PathVariable int userID) {
		
		
		return adminService.deleteUser(userID); 
		
		
	}
	@DeleteMapping("/posts/{postID}")
	int DeletePost(@PathVariable int postID) {
		
		
		return adminService.deletePost(postID); 
		
		
		
	}
	@PostMapping("users/{userID}/lock")
	boolean LockUser(@PathVariable int userID) {
		
		
		return adminService.LockUser(userID); 
		
		
	}
	@DeleteMapping("users/{userID}/lock")
	boolean UnLockUser(@PathVariable int userID) {
		
		
		return adminService.UnLockUser(userID); 
		
		
	}
	
	@DeleteMapping("/pictures/{pictureID}")
	int DeletePicture(@PathVariable long pictureID) {
		
		
		return adminService.deletePicture(pictureID);
		
		
	}
	@PostMapping("/circularEmail")
	boolean SendCircularEmail(@RequestBody CircularEmail circularEmail ) throws MessagingException {
		
		
		return adminService.SendCircularEmail(circularEmail); 
		
		
	}
	
	
	
	
}
