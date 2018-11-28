package com.sec.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sec.DTO.LikeDTO;
import com.sec.entity.Like;
import com.sec.entity.Post;
import com.sec.entity.User;
import com.sec.repo.LikeRepository;
import com.sec.repo.PostRepository;

@Service
public class LikeService {

	@Autowired
	LikeRepository likerepo;
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	MappingService mappingService;
	
	public LikeDTO LikePost(long postID) throws Exception {
		
		Post likedPost = postRepository.findOne(postID);
		System.out.println(likedPost.getPostID() + " LIKE");
		Like like = null;
		try {
		like = likerepo.save(new Like(likedPost));
		}
		catch (Exception e) {
			throw new Exception("alreadyLiked");
		}
		
		return mappingService.MapElements(like,LikeDTO.class);
		
		
	}

	public Page<LikeDTO> GetLikes(long postID,Pageable page) {
		
		
		Page<Like> likePage = likerepo.findByPost_PostID(postID,page);
		
		
		return mappingService.MapPages(Like.class, LikeDTO.class, likePage);
		
	}
	@Transactional
	public int DeleteLike(long postID, User user) {
		
		return likerepo.deleteByPost_PostIDAndCreatedBy(postID, user);
		
	}

	
	
	
	
}
