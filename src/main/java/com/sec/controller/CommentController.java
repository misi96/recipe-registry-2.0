package com.sec.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sec.DTO.CommentDTO;
import com.sec.entity.User;
import com.sec.repo.CommentRepository;
import com.sec.service.CommentService;


@RestController
@RequestMapping("/posts/{PostID}/comments")

public class CommentController {

	@Autowired
	CommentService commentService;
	
	
	@Autowired
	CommentRepository commentrepo;
	
	
	
	
@PostMapping
CommentDTO CommentPost(@PathVariable int PostID,@RequestBody CommentDTO commentDTO) {
	
	
	return commentService.AddCommentToPost(PostID,commentDTO);
	
	
	
}

@GetMapping
Page<CommentDTO> GetCommentList(@PathVariable int PostID,@RequestParam(value = "page", defaultValue="0") int page, @RequestParam( value = "size", defaultValue="30") int size,
		@SortDefault(sort="creationDate",direction = Sort.Direction.DESC)Sort sort) {
	
	
	return commentService.GetCommentList(PostID,new PageRequest(page, size, sort));
	
	
	
}

@DeleteMapping("/{commentID}")
int DeleteComment(@PathVariable("PostID") long PostID,@AuthenticationPrincipal User user,@PathVariable("commentID") long commentID) {
	
	
	return commentService.DeleteComment(PostID,commentID,user);
	
	
	
}
	
	
	
}
