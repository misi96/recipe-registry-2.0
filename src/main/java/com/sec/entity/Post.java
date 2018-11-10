package com.sec.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;




@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="posts")
public class Post {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
long postID;

@OneToOne
Postable postable;

@CreatedDate
Date creationDate;

@CreatedBy
User createdBy;

@OneToMany(orphanRemoval=true,fetch=FetchType.LAZY)
List<Like> likes;

@OneToMany(orphanRemoval=true,fetch=FetchType.LAZY)
List<Comment> comments;

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
	return creationDate;
}

public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
}

public User getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
}

public List<Like> getLikes() {
	return likes;
}

public void setLikes(List<Like> likes) {
	this.likes = likes;
}

public List<Comment> getComments() {
	return comments;
}

public void setComments(List<Comment> comments) {
	this.comments = comments;
}







}
