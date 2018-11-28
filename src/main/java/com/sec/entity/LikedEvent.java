package com.sec.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("Like")
@Entity
public class LikedEvent extends Event{

	public LikedEvent(long postID, User findByUserName) {
		super(postID,findByUserName);
	}
	
	public LikedEvent() {};
}
