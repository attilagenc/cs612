package edu.herguan.cs612.termproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long sid;
	
	@Column
    public String id;

	@Column
    Integer age;
	
	@Column
    String imageUrl;
	
	@Column
    String name;
	
	@Column
    String snippet;
		
}
