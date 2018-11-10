package com.sec.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@DiscriminatorValue("recipe")
@Entity
public class Recipe extends Postable {
	
	@Column(nullable=false)
	String name;
	@Column(nullable=false,length=512)
	String guide;
	
	@OneToMany(fetch=FetchType.LAZY)
	List<Ingredient> ingredients =new ArrayList<>();
	
	
	boolean vegetarian;
	
	
	int time;
	
	
}
