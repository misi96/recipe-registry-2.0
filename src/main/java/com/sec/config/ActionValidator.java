package com.sec.config;

public interface ActionValidator {
	
	 boolean ValidateActrionOnPost(long userID,long PostID,ActionOnPost action);
	 
}
