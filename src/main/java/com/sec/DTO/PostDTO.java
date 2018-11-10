package com.sec.DTO;

import java.util.Date;

public class PostDTO {

	Long postID;
	
	PostableDTO postable;
	
	Date CreationDate;
	
	
	public Long getPostID() {
		return postID;
	}
	public void setPostID(Long postID) {
		this.postID = postID;
	}
	public PostableDTO getPostable() {
		return postable;
	}
	public void setPostable(PostableDTO postable) {
		this.postable = postable;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	
}
