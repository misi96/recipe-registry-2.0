package com.sec.DTO;


import com.sec.entity.RecipeDetails;

public class RecipeDTO extends PostableDTO{
	
	String name;
	RecipeDetails recipeDetails;
	boolean vegetarian;
	int time;
	boolean hasMainPicFlag;
	String post_type;
	
	

	public boolean getHasMainPicFlag() {
		return hasMainPicFlag;
	}

	public void setHasMainPicFlag(boolean hasMainPicFlag) {
		this.hasMainPicFlag = hasMainPicFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RecipeDetails getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(RecipeDetails recipeDetails) {
		this.recipeDetails = recipeDetails;
	}

	

	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	

	

	public boolean getVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public RecipeDTO() {
		super();
		System.out.println("DEEEBUG!");
	}

	
	
	
}
