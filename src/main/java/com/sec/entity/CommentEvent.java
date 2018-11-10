package com.sec.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("comment")
@Entity
public class CommentEvent extends Event{

	public CommentEvent(long postID, User findByUserName) {
		super(postID,findByUserName);
	}
}
