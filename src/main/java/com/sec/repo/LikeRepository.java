package com.sec.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sec.entity.Like;
import com.sec.entity.User;


public interface LikeRepository extends PagingAndSortingRepository<Like, Long>{
	
	int deleteByPost_PostIDAndCreatedBy(long postID, User user);

}
