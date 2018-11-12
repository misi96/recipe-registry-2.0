package com.sec.entity;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;

import com.sec.config.AutowireHelper;
import com.sec.repo.EventRepository;

public class LikeListener {

	@Autowired
	EventRepository eventRepo;
	
	
	
	
	
	@PrePersist
	void  AfterComment(Like like) {
		
		AutowireHelper.autowire(this, this.eventRepo);
		
		
		Post post =	like.getPost();
	
			
		eventRepo.save(new LikedEvent(post.getPostID(),post.getCreatedBy()));
		
	}
	
}
