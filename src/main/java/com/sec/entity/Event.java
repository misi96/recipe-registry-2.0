package com.sec.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type")
@Entity
@EntityListeners({AuditingEntityListener.class,EventListener.class})
public abstract class Event {
	
	@Id
	@GeneratedValue
	private long eventID;
	
    
	@Column(length=30,nullable=true)
	String message;
	
	
	long relatedPostID;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	User targetUser;
	
	@ManyToOne
	@CreatedBy
	User sourceUser;
	
	
	@CreatedDate
	Date date;
	
	
	
	



	public long getEventID() {
		return eventID;
	}



	public void setEventID(long eventID) {
		this.eventID = eventID;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public long getRelatedPostID() {
		return relatedPostID;
	}



	public void setRelatedPostID(long relatedPostID) {
		this.relatedPostID = relatedPostID;
	}



	public User getTargetUser() {
		return targetUser;
	}



	public void setTargetUser(User targetUser) {
		this.targetUser = targetUser;
	}



	public User getSourceUser() {
		return sourceUser;
	}



	public void setSourceUser(User sourceUser) {
		this.sourceUser = sourceUser;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", message=" + message + ", relatedPostID=" + relatedPostID
				+ ", targetUser=" + targetUser + ", sourceUser=" + sourceUser + ", date=" + date + "]";
	}



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
