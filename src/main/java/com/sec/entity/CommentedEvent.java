package com.sec.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("comment")
@Entity
public class CommentedEvent extends Event{

	public CommentedEvent(long postID, User findByUserName) {
		super(postID,findByUserName);
		
	}
	

}
