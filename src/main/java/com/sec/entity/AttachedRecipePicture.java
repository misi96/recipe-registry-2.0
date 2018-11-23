package com.sec.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@DiscriminatorValue("AttachedRecipe")
@Entity
public class AttachedRecipePicture extends Picture{

	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	Recipe recipe;


	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public AttachedRecipePicture(byte[] picture, Recipe recipe) {
		super(picture);
		this.recipe=recipe;
	}
	public AttachedRecipePicture(byte[] picture) {
		super(picture);
		
	}

	public AttachedRecipePicture() {
		super();
	}

}
