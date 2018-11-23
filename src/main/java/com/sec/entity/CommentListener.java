package com.sec.entity;





import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;

import com.sec.config.AutowireHelper;
import com.sec.repo.EventRepository;
import com.sec.repo.PostRepository;
import com.sec.service.TaggingService;


public class CommentListener {

	@Autowired
	TaggingService taggingService;
	
	@Autowired
	EventRepository eventRepo;
	
	@Autowired
	PostRepository postRepository;
	
	
	
	@PrePersist
	void  BeforeComment(Comment comment) {
		
		AutowireHelper.autowire(this, this.taggingService);
		AutowireHelper.autowire(this, this.eventRepo);
		AutowireHelper.autowire(this, this.postRepository);
		
		Post post =comment.getPost();
		postRepository.SetCommentCounter(post.getCommentCounter() + 1, post.getPostID());
			
		eventRepo.save(new CommentedEvent(post.getPostID(),post.getCreatedBy()));
		taggingService.CheckCommentForTags(comment);
		
		
	}
	@PreRemove
	void  BeforeCommentDelete(Comment comment) {
		
		AutowireHelper.autowire(this, this.postRepository);
		
		Post post =comment.getPost();
		postRepository.SetCommentCounter(post.getCommentCounter() - 1, post.getPostID());
			
		
		
	}
	
	
	
	
	
}
