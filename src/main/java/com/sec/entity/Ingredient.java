package com.sec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	@Column(nullable=false)
	String name;
	@Column(nullable=false)
	float quantity;
	@Column(nullable=false)
	String unit;
	
	@ManyToOne
	@JoinColumn(name="recipeDetails")
	@JsonBackReference
	RecipeDetails recipeDetails;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RecipeDetails getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(RecipeDetails recipeDetails) {
		this.recipeDetails = recipeDetails;
	}

	public String getName() {
		return name;
	}
	
	public Ingredient() {
		super();
	}

	public Ingredient(String name, float quantity, String unit) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}
	public Ingredient(String name, float quantity, String unit, RecipeDetails recipeDetails) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
		this.recipeDetails = recipeDetails;
	}

	public void setName(String name) {
		this.name = name;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
