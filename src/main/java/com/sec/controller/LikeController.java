package com.sec.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sec.DTO.LikeDTO;
import com.sec.entity.User;
import com.sec.service.LikeService;

@RestController
@RequestMapping("/posts/{PostID}/like")
public class LikeController {
	
    
	@Autowired
	LikeService likeService;
	
	@PostMapping
	LikeDTO LikePost(@PathVariable long PostID) throws Exception {
		
		
		
		return likeService.LikePost(PostID);
		
		
		
	}
	
	@GetMapping
	Page<LikeDTO> GetLikes(@PathVariable int PostID,@RequestParam(value = "page", defaultValue="0") int page, @RequestParam( value = "size", defaultValue="40") int size) throws Exception {
		
		
		
		return likeService.GetLikes(PostID,new PageRequest(page, size));
		
		
		
		
	}
	@DeleteMapping
	int DeleteLike(@PathVariable long PostID,@AuthenticationPrincipal User user) {
		
		
		return likeService.DeleteLike(PostID,user);
		
		
		
	}	
	
	
	
}
