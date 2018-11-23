package com.sec.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RecipeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Column(length=512)
	String guide;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="recipeDetails")
	List<Ingredient> ingredients =new ArrayList<>();
	
	@Override
	public String toString() {
		return "RecipeDetails [id=" + id + ", guide=" + guide + ", ingredients=" + ingredients + ", recipe=" + recipe
				+ "]";
	}

	@OneToOne(mappedBy="recipeDetails")
	Recipe recipe;
	
	public RecipeDetails(String guide, List<Ingredient> ingredients) {
		super();
		this.guide = guide;
		this.ingredients = ingredients;
	}

	public RecipeDetails() {
		super();
	}

	public RecipeDetails(String guide) {
		
		super();
		this.guide = guide;
		
	}

	public long getPostID() {
		return id;
	}

	public void setPostID(long id) {
		this.id = id;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
	
}
