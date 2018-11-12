package com.sec.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.DTO.CommentDTO;
import com.sec.config.ActionValidator;
import com.sec.entity.Comment;
import com.sec.entity.Post;
import com.sec.entity.User;
import com.sec.repo.CommentRepository;
import com.sec.repo.EventRepository;
import com.sec.repo.PostRepository;
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
	 PostRepository Postrepo;
	
	@PostConstruct
	void init() {
		User user= new User();
		user.setEmail("ff");
		user.setPassword("dsds");
		user.setUserName("faaa");
		Postrepo.save(new Post());
		
	}
	
	
	

	
@Transactional(readOnly=false)
public CommentDTO AddCommentToPost(long PostID,CommentDTO commentDTO) {
	    

	Comment comment= mappingService.MapElements(commentDTO, Comment.class);
	comment.setPost(Postrepo.findOne(PostID));
	CommentRepo.save(comment);
		
	System.out.println("DEBUG");
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
