package com.sec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.DTO.PostDTO;
import com.sec.DTO.PostableDTO;
import com.sec.repo.PostRepository;

@Service
public class PostService {
@Autowired
PostRepository postRepo;

public PostDTO Post(PostableDTO postable){
	
	
	
	return null;
	
	
	
}

}
