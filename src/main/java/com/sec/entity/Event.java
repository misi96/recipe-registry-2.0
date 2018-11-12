package com.sec.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type")
@Entity
@EntityListeners(AuditingEntityListener.class)
public abstract class Event {
	
	@Id
	@GeneratedValue
	private Long eventID;
	
    
	@Column(length=30,nullable=true)
	String message;
	
	
	long relatedPostID;
	
	
	@ManyToOne
	User targetUser;
	
	
	@CreatedBy
	User sourceUser;
	
	@CreatedDate
	Date date;
	
	
	public Event(long relatedPostID, User targetUser) {
		
		this.relatedPostID = relatedPostID;
		this.targetUser = targetUser;
		
		
		
	}

	

	public Event( long relatedPostID, User targetUser,String message) {
		
		this.message = message;
		this.relatedPostID = relatedPostID;
		this.targetUser = targetUser;
		
	}



	public Event() {}
	

}
