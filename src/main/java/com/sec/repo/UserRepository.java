package com.sec.repo;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sec.entity.Role;
import com.sec.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	//@Cacheable(key = "#userName", value="Users",condition = "#userName != null")
	User findByUserName(String userName);
	
	User findByEmailOrUserName(String email,String userName);
	
	User findByEmail(String userName);
	
	User findByActivation(String code);
    
	Page<User> findAll(Pageable pageable); 
	
	@Query("select p.createdBy from Post p Where p.postID = :postID")
	User findByPostID(@Param("postID") long postID);
	
	int deleteByIdAndRolesNotContaining(long id,Role role);
	
	@Modifying
	@Query("UPDATE User SET locked = :locked WHERE id = :userID")
	void SetLockFlag(@Param("locked")boolean locked,@Param("userID") long userID);
	
	
	@Query("select user.email from User user")
	List<String> getEmails();
	
	@Modifying
	@Query("UPDATE User SET eventIndicator = :eventIndicator WHERE id = :targetUserID")
	void setEventIndicatorOfUser(@Param("eventIndicator")boolean eventIndicator,@Param("targetUserID")long targetUserID);
	
	
	
	
	
	//@Query("UPDATE User SET event.Tagged = :bool WHERE id = :userID")
	//void SetTaggedFlag(@Param("bool")boolean bool,@Param("userID") int userID);

	
	
	
}