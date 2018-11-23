package com.sec.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "picture_type")
public abstract class Picture {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	
	long pictureID;
	
	@Lob
	byte[] picture;
	
	
	public long getPictureID() {
		return pictureID;
	}

	public void setPictureID(long pictureID) {
		this.pictureID = pictureID;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	

	

	public Picture(byte[] picture) {
		super();
		this.picture = picture;
	}

	public Picture() {
		super();
	}
	
	
	
	
	
}
