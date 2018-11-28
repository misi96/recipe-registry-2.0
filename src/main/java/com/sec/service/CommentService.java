package com.sec.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.DTO.CommentDTO;
import com.sec.config.ActionValidator;
import com.sec.entity.Comment;
import com.sec.entity.Ingredient;
import com.sec.entity.Post;
import com.sec.entity.Recipe;
import com.sec.entity.RecipeDetails;
import com.sec.entity.User;
import com.sec.repo.CommentRepository;
import com.sec.repo.EventRepository;
import com.sec.repo.PostRepository;
import com.sec.repo.UserRepository;

@Service
@Transactional
public class CommentService {
	
	
	@Autowired
	UserRepository UserRepo;
	
	@Autowired
	EventRepository EventRepo;
	
	@Autowired
	CommentRepository CommentRepo;
	
	@Autowired
	ActionValidator actionValidator;
	
	@Autowired
	MappingService mappingService;
	
	@Autowired
	 PostRepository Postrepo;
	
	
	@PostConstruct
	void init() {
		
		
		
		
		RecipeDetails recDetails1 = new RecipeDetails("igy csináld ugy csináld");
		RecipeDetails recDetails2 = new RecipeDetails("igy csináld ugy csináld");
		RecipeDetails recDetails3 = new RecipeDetails("igy csináld ugy csináld");
		RecipeDetails recDetails4 = new RecipeDetails("igy csináld ugy csináld");
		recDetails1.setForHowManyPeople(2);
		
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("paprika",2,"db",recDetails1));
		ingredients1.add(new Ingredient("krumpli",3,"db",recDetails1));
		
		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("vmi1",6,"db",recDetails2));
		ingredients2.add(new Ingredient("vmi2",5,"db",recDetails2));
		
		List<Ingredient> ingredients3 = new ArrayList<>();
		ingredients3.add(new Ingredient("vmi1",6,"db",recDetails3));
		ingredients3.add(new Ingredient("vmi2",5,"db",recDetails3));
		
		recDetails1.setIngredients(ingredients1);
		recDetails2.setIngredients(ingredients2);
		recDetails3.setIngredients(ingredients3);
		
		
		
		Postrepo.save(new Post(new Recipe(recDetails1,"mákos galuska")));
		Postrepo.save(new Post(new Recipe(recDetails2,"teszt")));
		Postrepo.save(new Post(new Recipe(recDetails3 ,"teknős")));
		Postrepo.save(new Post(new Recipe(recDetails4,"teknős")));
		Postrepo.save(new Post(new Recipe(null,"teknős")));
		Postrepo.save(new Post(new Recipe(null,"mákos galuska")));
	}
	
	
	

	
@Transactional(readOnly=false)
public CommentDTO AddCommentToPost(long PostID,CommentDTO commentDTO) {
	    

	Comment comment	= mappingService.MapElements(commentDTO, Comment.class);
	
	
	comment.setPost(Postrepo.findOne(PostID));
	Comment savedComment = CommentRepo.save(comment);
	
	
	return mappingService.MapElements(savedComment, CommentDTO.class);
		 
		
		
		
	
	
	
	
	
}


public Page<CommentDTO> GetCommentList(int getCommentList, Pageable page) {
	
	Page<Comment> commentPage = CommentRepo.findAll(page);
	
	
	return mappingService.MapPages(Comment.class, CommentDTO.class, commentPage);
	
	
	
}

@Transactional
public int DeleteComment(long postID, long commentID, User user) {
	
	
	
	return CommentRepo.deleteByPost_PostIDAndIdAndCreatedBy(postID,commentID,user);
	
	
}

	

}
