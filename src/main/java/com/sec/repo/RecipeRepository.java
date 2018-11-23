package com.sec.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sec.entity.Recipe;

import com.sec.entity.User;

public interface RecipeRepository  extends PagingAndSortingRepository<Recipe, Long>,RecipeRepositoryCustom{
	
	
	Recipe findByPostableIDAndCreatedBy(long postableID,User createdBy);
	
	@Modifying
	@Query("UPDATE Recipe SET hasMainPicFlag = :bool WHERE postableID = :postableID")
	void SetMainPicFlag(@Param("bool")boolean bool,@Param("postableID")long postableID);

	@Query("SELECT rec.createdBy.id FROM Recipe rec WHERE postableID = :postableID")
	long getCreatedById(@Param("postableID")long postableID);
	
	@Query("SELECT rec FROM Recipe rec WHERE rec.postableID NOT IN "
			+ "(SELECT ingr.recipeDetails.recipe.postableID FROM Ingredient ingr WHERE ingr.id IN"
			+ " (SELECT ingr.id FROM Ingredient ingr WHERE ingr.name NOT IN :ingredients)) ")
	Page<Recipe> GetRecipesWithIngredients(@Param("ingredients")List<String> ingredients,Pageable pageable);
	
	
	
	
	
}
