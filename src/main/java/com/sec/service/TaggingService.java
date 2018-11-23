package com.sec.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sec.entity.Comment;
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
	public void CheckCommentForTags(Comment comment) {
		Set<Long> tagged = new HashSet<>();
		
		String text = comment.getContent();
		
		String regex = "(^|\\W)@([-a-zA-Z0-9._áéíóú]*)\\b";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			
			String userName = matcher.group();
			userName = userName.substring(userName.indexOf("@")+1);
			
		 System.out.println("found: " + " : "+ userName);
		       
		 User user = UserRepo.findByUserName(userName);
		
		 
		 if(user != null && !tagged.contains(user.getId()) ){
			 EventRepo.save(new TaggedEvent(comment.getPost().getPostID(),user));
			 tagged.add(user.getId());
		 }
		 
		 }
		
	}
	
	
	}

