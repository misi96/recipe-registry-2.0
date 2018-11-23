package com.sec.DTO;

import java.util.Date;

public class EventDTO {
	
	long relatedPostID;
	String message;
	UserDTO sourceUser;
	Date date;
	
	public long getRelatedPostID() {
		return relatedPostID;
	}
	public void setRelatedPostID(long relatedPostID) {
		this.relatedPostID = relatedPostID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserDTO getSourceUser() {
		return sourceUser;
	}
	public void setSourceUser(UserDTO sourceUser) {
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
		return "EventDTO [relatedPostID=" + relatedPostID + ", message=" + message + ", sourceUser=" + sourceUser
				+ ", date=" + date + "]";
	}
	
	
	
}
