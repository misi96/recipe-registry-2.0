package com.sec.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@DeleteMapping("/users/{userID}")
	int DeleteUser(@PathVariable int userID) {
		
		
		return 0; 
		
		
	}
	@DeleteMapping("/posts/{postID}")
	int DeleteRecipe(@PathVariable int postID) {
		
		
		return 0; 
		
		
	}
	@PostMapping("users/{userID}/lock")
	boolean EnableUser(@PathVariable int userID) {
		
		
		return false; 
		
		
	}
	@DeleteMapping("users/{userID}/lock")
	boolean DisableUser(@PathVariable int userID) {
		
		
		return false; 
		
		
	}
	
}
