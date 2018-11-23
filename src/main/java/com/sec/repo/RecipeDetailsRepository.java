package com.sec.repo;

import org.springframework.data.repository.CrudRepository;


import com.sec.entity.RecipeDetails;

public interface RecipeDetailsRepository extends CrudRepository<RecipeDetails, Long> {

	RecipeDetails findByRecipe_postableID(long postableID);
	
}
