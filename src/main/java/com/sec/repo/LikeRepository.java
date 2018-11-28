package com.sec.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sec.entity.Like;
import com.sec.entity.User;


public interface LikeRepository extends PagingAndSortingRepository<Like, Long>{
	
	@Modifying
	int deleteByPost_PostIDAndCreatedBy(long postID, User user);
	
	
	Page<Like> findByPost_PostID(long postID,Pageable page);
	
}
