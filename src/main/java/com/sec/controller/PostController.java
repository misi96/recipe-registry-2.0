package com.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sec.DTO.PostDTO;
import com.sec.DTO.PostableDTO;
import com.sec.repo.PostFilter;
import com.sec.service.PostService;

@RequestMapping("/")
@RestController
public class PostController {
	@Autowired
	PostService postService;
	
	@PostMapping
	PostDTO Post(@RequestBody PostableDTO postableDTO) {
		
		
		return postService.Post(postableDTO);
		
		
		
	}
	@GetMapping("/posts")
	Page<PostDTO> ListPost(@RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue="20") int size){
		return null;
		
		
		
		
	}
	@DeleteMapping("/posts")			
	int DeletePost(){
		
		
		
		return 0;
		
	}
	
	
	
	@GetMapping("/posts/filter")
	Page<PostDTO> ListFilteredPost(@RequestBody PostFilter postFilter,@RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue="20" ) int size){
		
		
		return null;
		
	}
	
	
	
	
	
	
	
}
