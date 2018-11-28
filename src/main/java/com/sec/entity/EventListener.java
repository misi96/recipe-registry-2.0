package com.sec.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;

import com.sec.config.AutowireHelper;
import com.sec.repo.UserRepository;
//esetleges konkurencia problémák az EventIndicator-al
public class EventListener {

	@Autowired
	UserRepository userRepository;
	
	@PrePersist
	void  BeforeEvent(Event event) {
		
		User targetUser = event.getTargetUser();
		
		if( targetUser != null) {
			if(targetUser.getEventIndicator()==false ) {	
			AutowireHelper.autowire(this, this.userRepository);
			userRepository.setEventIndicatorOfUser(true,event.getTargetUser().getId());
			userRepository.setEventIndicatorOfUserInMemory(event.getTargetUser().getId(), true);
		}
		
		}
		
	}
	
	
	@PreRemove 
	void  BeforeEventDelete(Event event) {
		
			User targetUser = event.getTargetUser();
			
			if(targetUser != null) {	
				if(targetUser.getEventIndicator()==true ) {
				AutowireHelper.autowire(this, this.userRepository);
				userRepository.setEventIndicatorOfUser(false,event.getTargetUser().getId()); //ha egyet törlök mindent törlök !!
				userRepository.setEventIndicatorOfUserInMemory(event.getTargetUser().getId(), false);
			}
			
			}
		
		
	}
	
	
	
	
}
