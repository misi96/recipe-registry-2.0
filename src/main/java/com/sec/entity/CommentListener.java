package com.sec.entity;





import javax.annotation.PostConstruct;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.sec.config.AutowireHelper;
import com.sec.repo.EventRepository;
import com.sec.repo.UserRepository;
import com.sec.service.TaggingService;
import com.sec.service.UserService;


public class CommentListener {

	@Autowired
	TaggingService taggingService;
	
	@Autowired
	EventRepository eventRepo;
	
	
	
	
	
	@PrePersist
	void  AfterComment(Comment comment) {
		
		AutowireHelper.autowire(this, this.taggingService);
		AutowireHelper.autowire(this, this.eventRepo);
		
		
		Post post =comment.getPost();
	
			
		eventRepo.save(new CommentedEvent(post.getPostID(),post.getCreatedBy()));
		taggingService.CheckCommentForTags(comment);
		
		
	}
	
	
	
	
}
