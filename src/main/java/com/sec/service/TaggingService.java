package com.sec.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sec.DTO.CommentDTO;
import com.sec.entity.TaggedEvent;
import com.sec.entity.User;
import com.sec.repo.EventRepository;
import com.sec.repo.UserRepository;

@Service
public class TaggingService {

	@Autowired
	EventRepository EventRepo;
	
	@Autowired
	UserRepository UserRepo;
	
	@Async	
	public void TagUser(long PostID,CommentDTO comment)	{
		
		String text = comment.getContent();
		
		String regex = "(^|\\W)@([-a-zA-Z0-9._]*)\\b";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		
		while(matcher.find()) {
			
			String userName = matcher.group();
			userName = userName.substring(userName.indexOf("@")+1);
			
		 System.out.println("found: " + " : "+ userName);
		       
		 User user = UserRepo.findByUserName(userName);
		 if(user != null) {
			 EventRepo.save(new TaggedEvent(PostID,user));
		 }
		 
		 }
		
		
	}
	}

