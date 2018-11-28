package com.sec.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sec.DTO.RecipeDTO;
import com.sec.repo.RecipeRepositoryImpl;

@DiscriminatorValue("recipe")
@Entity
@EntityListeners({RecipeRepositoryImpl.class,AuditingEntityListener.class})
public class Recipe extends Postable {
	
	@Column(name="post_type",insertable = false, updatable = false)
	String post_type = "recipe";

	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonProperty(access = Access.WRITE_ONLY)
	RecipeDetails recipeDetails;
	
	@Column
	String name;
	
	
	boolean vegetarian;
	
	int time;
	
	
	@JsonIgnore
	@CreatedBy
	@ManyToOne
	User createdBy; 	//redundáns,de main pic. upload-nál jól jön
	
	
	
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="recipe")
	@JsonIgnore
	List<AttachedRecipePicture> attachedRecipePictures;
	
	
	
	@JsonProperty(access = Access.READ_ONLY)
	boolean hasMainPicFlag = false;
	
	
	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	
	
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	



	public List<AttachedRecipePicture> getAttachedRecipePictures() {
		return attachedRecipePictures;
	}

	public void setAttachedRecipePictures(List<AttachedRecipePicture> attachedRecipePictures) {
		this.attachedRecipePictures = attachedRecipePictures;
	}

	public Recipe(RecipeDetails recipeDetails, String name) {
		super();
		this.recipeDetails = recipeDetails;
		this.name = name;
	}

	public Recipe(RecipeDetails recipeDetails, String name, boolean vegetarian, int time) {
		super();
		this.recipeDetails = recipeDetails;
		this.name = name;
		this.vegetarian = vegetarian;
		this.time = time;
	}

	public RecipeDetails getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(RecipeDetails recipeDetails) {
		this.recipeDetails = recipeDetails;
	}

	public Recipe() {
		super();
	}
	

	
	public Recipe(RecipeDTO recipeDTO) {
		
		super();
		this.recipeDetails = recipeDTO.getRecipeDetails();
		this.name = recipeDTO.getName();
		this.vegetarian = recipeDTO.getVegetarian();
		this.time = recipeDTO.getTime();
		
		
	}
	
	
	



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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



	public boolean getHasMainPicFlag() {
		return hasMainPicFlag;
	}

	public void setHasMainPicFlag(boolean hasMainPicFlag) {
		this.hasMainPicFlag = hasMainPicFlag;
	}
	
	@Override
	public String toString() {
		return "Recipe [post_type=" + post_type + ", recipeDetails=" + recipeDetails + ", createdBy=" + createdBy
				+ ", name=" + name + ", vegetarian=" + vegetarian + ", time=" + time + ", hasMainPicFlag="
				+ hasMainPicFlag + "]";
	}

	
	
	
	
}
