package edu.herguan.cs612.termproject.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class ItemDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long sid;
	
	@Column
    Double price;
	
	@Column
	public String id;
	@Column
	String name;
	@Column(name="aFeatures",length=1024)
	String additionalFeatures;
	@Column(length=1024)
	String description;
	
	@Embedded
	Android android;
	
	@Embedded
	Battery battery;
	
	@Embedded
	Connectivity connectivity;
	
	@Embedded
	Display display;
	
	@Embedded
	Hardware hardware;
	
	@Embedded
	Storage storage;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(
	        name="IMAGES",
	        joinColumns=@JoinColumn(name="sid")
	)
	@Column(name="image_path")
	List<String> images;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(
	        name="CELL_NETWORKS",
	        joinColumns=@JoinColumn(name="sid")
	)
	@Column()
	List<String> availability;
	
	@Embedded
	SizeAndWeight sizeAndWeight;
	
	@Embedded
	Camera camera;
	
	public Item toItem(){
		Item item = new Item();
		item.id = this.id;
		item.name = this.name;
		item.age = 0;
		if (this.images != null && this.images.size()>0)
			item.imageUrl = this.images.get(0);
		int indexOf = this.description.indexOf('.');
		if (indexOf > 0)
			item.snippet = this.description.substring(0,indexOf);
		else
			item.snippet = this.description;
		return item;
	}
	
	public ItemDetails sampleItemDetails(String id){
		ItemDetails sample = new ItemDetails();
		sample.id=id;
		
		return sample;
	}
}
