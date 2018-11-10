package com.sec.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sec.DTO.LikeDTO;
import com.sec.entity.Like;
import com.sec.entity.User;
import com.sec.repo.LikeRepository;

@Service
@Transactional
public class LikeService {

	@Autowired
	LikeRepository likerepo;
	
	
	@Autowired
	MappingService mappingService;
	
	public LikeDTO LikePost(int postID) {
		
		Like like = likerepo.save(new Like());
		
		return mappingService.MapElements(like,LikeDTO.class);
		
		
	}

	public Page<LikeDTO> GetLikes(int postID,Pageable page) {
		
		
		Page<Like> likePage = likerepo.findAll(page);
		
		
		return mappingService.MapPages(Like.class, LikeDTO.class, likePage);
		
	}

	public int DeleteLike(int postID, User user) {
		
		return likerepo.deleteByPost_PostIDAndCreatedBy(postID, user);
		
	}

	
	
	
	
}
