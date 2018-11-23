package com.sec.repo;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sec.entity.Comment;
import com.sec.entity.User;
@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>{
	
	
	int deleteByPost_PostIDAndIdAndCreatedBy(long postID, long commentID, User user);

	/*@Modifying
	@Query("UPDATE Comment SET post = :bool WHERE postID = :postID")
	void setPost(Post findOne,Post post);
*/

	
	
	
}
