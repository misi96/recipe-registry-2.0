package com.sec.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sec.DTO.PostDTO;
import com.sec.DTO.PostableDTO;
import com.sec.entity.RecipeDetails;
import com.sec.entity.User;
import com.sec.repo.PostFilter;
import com.sec.repo.RecipeDetailsRepository;
import com.sec.service.PostService;

@RequestMapping("/")
@RestController
public class PostController {
	@Autowired
	PostService postService;
	
	
	@Autowired
	RecipeDetailsRepository recipeDetailsRepository;
	

	@PostMapping
	PostDTO Post(@RequestBody PostableDTO postableDTO) {
		
		
		return postService.Post(postableDTO);
		
		
		
	}
	@GetMapping("/posts")
	Page<PostDTO> ListPosts(@RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue="20") int size,
			@SortDefault(sort="likeCounter",direction = Sort.Direction.DESC)Sort sort){

		return postService.ListPosts(new PageRequest(page, size,sort));
		
		
		
		
	}
	
	
	@GetMapping("/recipes/names")
	Map<String, Long> NamesStartingWith(@RequestParam String startingWith){
		return postService.GetRecipeNamesStartingWith(startingWith);
		
		
		
		
	}
	
	@GetMapping("/recipes/ingredientsfilter")
	Page<PostDTO> GetRecipesWithIngredients(@RequestParam(value = "ingr") List<String> ingredients,@RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue="20") int size,
			@SortDefault(sort="likeCounter",direction = Sort.Direction.DESC)Sort sort){
		
		return postService.GetRecipesWithIngredients(ingredients,new PageRequest(page, size,sort));
		
		
		
		
	}
	
	
	@DeleteMapping("/posts/{postID}")			
	int DeletePost(@PathVariable int postID,@AuthenticationPrincipal User user){
		
		
		
		return postService.DeletePost(postID,user);
		
	}
	
	
	
	@GetMapping("/posts/filter")
	Page<PostDTO> ListFilteredPost(@RequestBody PostFilter postFilter,@RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue="20" ) int size){
		
		
		return null;
		
	}
	
	@GetMapping("recipes/recipedetails/{recipeID}")
	RecipeDetails GetDetails(@PathVariable int recipeID){
		
		
		return recipeDetailsRepository.findByRecipe_postableID(recipeID);
		
		
		
	}
	
	
}
