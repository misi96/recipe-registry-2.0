package com.sec.DTO;

import java.util.List;

import com.sec.entity.Ingredient;

public class RecipeDTO extends PostableDTO{
	
	String guide;
	List<Ingredient> ingredients;
	boolean vegetarian;
	int time;
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public RecipeDTO() {
		super();
		System.out.println("DEEEBUG!");
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public RecipeDTO(String guide) {
		super();
		this.guide = guide;
	}
	
	
}
