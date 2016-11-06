package com.ustodoFromV.domain;

import com.google.code.morphia.annotations.Entity;

@Entity
public class EntityFLR extends EntityBase {
	private String date;
	private String type;
	private String instance;

	public EntityFLR() {
	}
	
	public EntityFLR(String filelineraw) {
	}
	

}
