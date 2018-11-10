package com.sec.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sec.entity.Post;
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>{
	
	

}
