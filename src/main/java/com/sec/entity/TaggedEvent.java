package com.sec.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@DiscriminatorValue("tagged")
@Entity
public class TaggedEvent extends Event {
	
	
	public TaggedEvent(long postID, User findByUserName) {
		super(postID,findByUserName);
	}

	
	
}
