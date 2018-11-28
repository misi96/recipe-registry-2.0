package com.sec.repo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom{
	
	
	@PersistenceContext
    EntityManager entityManager;
	
	static ConcurrentMap<Long,Boolean> usersAndEvents = new ConcurrentHashMap<>(); //kell e szállbiztosnak lennie?
	
	@PostConstruct
	void init() {
		
		 List<Long> eventedUsers = entityManager.createQuery("SELECT user.id FROM User user WHERE user.eventIndicator = true", Long.class).getResultList();
		
		 for(Long id : eventedUsers) {
			 
			 usersAndEvents.put( id, true);
		 }
		 
	}
	
	
	@Override
	public boolean UserHasEvents(long userID) {
		
	Boolean bool =	usersAndEvents.get(userID);
		
		return bool == null ? false:bool ;
		
	}
	@Override
	public void setEventIndicatorOfUserInMemory(long userID,boolean eventIndicator) {
		
		usersAndEvents.put(userID, eventIndicator);
		
	}
	
	

}
