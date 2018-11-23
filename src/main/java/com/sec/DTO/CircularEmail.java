package com.sec.DTO;

import java.util.List;

public class CircularEmail {

	List<String> targets;
	String subject;
	String body;
	
	
	
	
	
	public CircularEmail(List<String> targets, String subject, String body) {
		super();
		this.targets = targets;
		this.subject = subject;
		this.body = body;
	}
	public CircularEmail() {
		
		
		
	}
	public List<String> getTargets() {
		return targets;
	}
	public void setTargets(List<String> targets) {
		this.targets = targets;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}

