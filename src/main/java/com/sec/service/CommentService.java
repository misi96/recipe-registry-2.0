package com.sec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.DTO.CommentDTO;
import com.sec.config.ActionValidator;
import com.sec.entity.Comment;
import com.sec.entity.User;
import com.sec.repo.CommentRepository;
import com.sec.repo.EventRepository;
import com.sec.repo.UserRepository;

@Service
@Transactional
public class CommentService {
	
	
	@Autowired
	UserRepository UserRepo;
	
	@Autowired
	EventRepository EventRepo;
	
	@Autowired
	CommentRepository CommentRepo;
	
	@Autowired
	ActionValidator actionValidator;
	
	@Autowired
	MappingService mappingService;
	
	@Autowired
	TaggingService taggingService;

	
	
	
	

	
	
public CommentDTO AddCommentToPost(long PostID,CommentDTO commentDTO) {
	    
		taggingService.TagUser(PostID,commentDTO);
		
		
		
	 	
		Comment comment= CommentRepo.save(mappingService.MapElements(commentDTO, Comment.class));
		
	
		return mappingService.MapElements(comment, CommentDTO.class);
		 
		
		
		
	
	
	
	
	
}


public Page<CommentDTO> GetCommentList(int getCommentList, Pageable page) {
	
	Page<Comment> commentPage = CommentRepo.findAll(page);
	
	
	return mappingService.MapPages(Comment.class, CommentDTO.class, commentPage);
	
	
	
}


public int DeleteComment(long postID, long commentID, User user) {
	
	
	
	return CommentRepo.deleteByPost_PostIDAndIdAndCreatedBy(postID,commentID,user);
	
	
}

	

}
