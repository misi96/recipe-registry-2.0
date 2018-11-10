package com.sec.repo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sec.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUserName(String userName);
	
	User findByEmail(String email);
	
	User findByActivation(String code);
    
	Page<User> findAll(Pageable pageable); 
	
	//@Query("UPDATE User SET event.Tagged = :bool WHERE id = :userID")
	//void SetTaggedFlag(@Param("bool")boolean bool,@Param("userID") int userID);

	
	
	
}