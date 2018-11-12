package com.sec.repo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sec.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	//@Cacheable(key = "#userName", value="Users",condition = "#userName != null")
	User findByUserName(String userName);
	
	User findByEmail(String email);
	
	User findByActivation(String code);
    
	Page<User> findAll(Pageable pageable); 
	
	@Query("select p.createdBy from Post p Where p.postID = :postID")
	User findByPostID(@Param("postID") long postID);
	
	//@Query("UPDATE User SET event.Tagged = :bool WHERE id = :userID")
	//void SetTaggedFlag(@Param("bool")boolean bool,@Param("userID") int userID);

	
	
	
}