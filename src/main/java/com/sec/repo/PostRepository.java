package com.sec.repo;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sec.entity.Post;
import com.sec.entity.User;
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>{
	
	/*@Query("SELECT new com.sec.entity.Post(p.id) FROM Post p ") 
    Page<Post> findPosts(Pageable pageable);
	*/
	
	int deleteByPostID(long postID);	//adminoknak
	int deleteByPostIDAndCreatedBy(long postID,User user);
	
	@Modifying
	@Query("Update Post Set likeCounter =:likeCounter where postID = :postID") 
	void SetLikeCounter(@Param("likeCounter")long likeCounter,@Param("postID")long postID);
	
	@Modifying
	@Query("Update Post Set commentCounter =:commentCounter where postID = :postID") 
	void SetCommentCounter(@Param("commentCounter")long commentCounter,@Param("postID")long postID);
	
	@Query("SELECT post FROM Post post WHERE post.postable.post_type = 'recipe' AND post.postable.postableID NOT IN "
			+ "(SELECT ingr.recipeDetails.recipe.postableID FROM Ingredient ingr WHERE ingr.id IN"
			+ " (SELECT ingr.id FROM Ingredient ingr WHERE ingr.name NOT IN :ingredients)) ")
	Page<Post> GetRecipesWithIngredients(@Param("ingredients")List<String> ingredients,Pageable pageable);
	
	
	
}
