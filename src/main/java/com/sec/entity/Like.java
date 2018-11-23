package com.sec.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners({AuditingEntityListener.class,LikeListener.class})
@Table( name="likes" ,uniqueConstraints={@UniqueConstraint(columnNames = {"createdBy", "post"})})
public class Like {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@CreatedBy
	@JoinColumn(name="createdBy")
	User createdBy;
	
	
	@CreatedDate
	Date creationDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="post")
	Post post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Like(Post post) {
		super();
		this.post = post;
	}

	public Like() {
		super();
	}
	

}
