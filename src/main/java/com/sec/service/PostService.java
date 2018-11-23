package com.sec.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.DTO.PostDTO;
import com.sec.DTO.PostableDTO;
import com.sec.repo.PostRepository;
import com.sec.repo.PostableRepository;
import com.sec.repo.RecipeDetailsRepository;
import com.sec.repo.RecipeRepository;
import com.sec.entity.Post;
import com.sec.entity.Postable;
import com.sec.entity.User;
@Service
public class PostService {
@Autowired
PostRepository postRepo;
@Autowired
MappingService mapper;
@Autowired
RecipeRepository recipeRepository;

@Autowired
RecipeDetailsRepository recipeDetailsRepository;
@Autowired
PostableRepository postableRepository;

@Transactional
public PostDTO Post(PostableDTO postableDTO){
	
	
	Postable postable =mapper.MapElements(postableDTO, Postable.class);
	
	
	postableRepository.save(postable);
	
	Post post = new Post(postable);
	
	
	postRepo.save(post);
	
	return null;
	
	
	
}
public Page<PostDTO> ListPosts(Pageable pageable) {
	
	
	
	return mapper.MapPages(Post.class, PostDTO.class,postRepo.findAll(pageable));
	
}


public Map<String, Long> GetRecipeNamesStartingWith(String startingWith) {
	
	return recipeRepository.GetNamesStartingWith(startingWith);
	
	
}

public int DeletePost(int postID, User user) {
	
	
	return postRepo.deleteByPostIDAndCreatedBy(postID, user);
	
	
}

public Page<PostDTO> GetRecipesWithIngredients(List<String> ingredients, PageRequest pageRequest) {
	
	Page<Post> recipePage = postRepo.GetRecipesWithIngredients(ingredients, pageRequest);
	
	return mapper.MapPages(Post.class, PostDTO.class, recipePage);
	
	
}


}
