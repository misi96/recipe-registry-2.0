package com.sec.DTO;

import java.util.Date;

import com.sec.entity.Postable;

public class PostDTO {

	Long postID;
	
	Postable postable;
	
	Date CreationDate;
	
	UserDTO createdBy;
		
	long likeCounter;
	long commentCounter;
	
	
	public long getLikeCounter() {
		return likeCounter;
	}
	public void setLikeCounter(long likeCounter) {
		this.likeCounter = likeCounter;
	}
	public long getCommentCounter() {
		return commentCounter;
	}
	public void setCommentCounter(long commentCounter) {
		this.commentCounter = commentCounter;
	}
	public UserDTO getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDTO createdBy) {
		this.createdBy = createdBy;
	}
	public Long getPostID() {
		return postID;
	}
	public void setPostID(Long postID) {
		this.postID = postID;
	}
	public Postable getPostable() {
		return postable;
	}
	public void setPostable(Postable postable) {
		this.postable = postable;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	
}
