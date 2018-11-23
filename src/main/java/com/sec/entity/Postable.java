package com.sec.entity;



import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.modelmapper.internal.asm.Type;



@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type",discriminatorType=DiscriminatorType.STRING)
@Entity
public class Postable {
	
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
long postableID;



public long getPostableID() {
	return postableID;
}

public void setPostableID(long postableID) {
	this.postableID = postableID;
}

public Postable() {
	super();
}





}
