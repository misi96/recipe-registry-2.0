package com.sec.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@DiscriminatorValue("tagged")
@Entity
public class TaggedEvent extends Event {
	
	
	public TaggedEvent(long postID, User findByUserName) {
		super(postID,findByUserName);
	}
	
	
	public TaggedEvent() {};
	
	
}
