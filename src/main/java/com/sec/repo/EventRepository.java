package com.sec.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Event;
import com.sec.entity.User;

public interface EventRepository extends CrudRepository<Event, Long> {

	
	List<Event> findByTargetUser(User user);
	
	@Modifying
	int deleteByTargetUser(User user);
	
	
}
