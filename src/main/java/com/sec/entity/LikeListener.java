package com.sec.entity;

import javax.annotation.PostConstruct;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sec.config.AutowireHelper;
import com.sec.repo.EventRepository;
import com.sec.repo.PostRepository;


@Component
public class LikeListener {

	@Autowired
	EventRepository eventRepo;
	
	@Autowired
	PostRepository postRepository;
	
	@PostConstruct 
	void init () {
		
		
		
	}
	
	@PrePersist
	void  BeforeLike(Like like) {
		AutowireHelper.autowire(this, this.eventRepo);
		AutowireHelper.autowire(this, this.postRepository);
		
		
		Post post =	like.getPost();
		System.out.println("blaaa" + post.getCreatedBy());
		
		postRepository.SetLikeCounter(post.getLikeCounter() + 1, post.getPostID());
		eventRepo.save(new LikedEvent(post.getPostID(),post.getCreatedBy()));
		
		
	}
	@PreRemove
	void  BeforeLikeDelete(Like like) {
		AutowireHelper.autowire(this, this.postRepository);
		
		Post post =	like.getPost();
		
		postRepository.SetLikeCounter(post.getLikeCounter() - 1, post.getPostID());
			
		
	}
	
}
