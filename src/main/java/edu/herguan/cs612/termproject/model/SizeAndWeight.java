package edu.herguan.cs612.termproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class SizeAndWeight {
	@Transient
	List<String> dimensions;
			
	@Access(AccessType.FIELD)
	@Column
	String weight;
	
	@Access(AccessType.PROPERTY)
	@Column
	public String getWidth() {
		if (dimensions == null)
			return null;
		return dimensions.get(0);
	}
	public void setWidth(String width) {
		if (dimensions == null)
			dimensions = new ArrayList<String>(3);
		if (dimensions.size()<3)
			for (int i = dimensions.size(); i < 3; i++) {
				dimensions.add(null);
			}
		dimensions.set(0,width);
	}
	
	@Access(AccessType.PROPERTY)
	@Column
	public String getHeight() {
		if (dimensions == null)
			return null;
		return dimensions.get(1);
	}
	public void setHeight(String height) {
		if (dimensions == null)
			dimensions = new ArrayList<String>(3);
		if (dimensions.size()<3)
			for (int i = dimensions.size(); i < 3; i++) {
				dimensions.add(null);
			}
		dimensions.set(1,height);
	}
	
	@Access(AccessType.PROPERTY)
	@Column
	public String getDepth() {
		if (dimensions == null)
			return null;
		return dimensions.get(2);
	}
	public void setDepth(String depth) {
		if (dimensions == null)
			dimensions = new ArrayList<String>(3);
		if (dimensions.size()<3)
			for (int i = dimensions.size(); i < 3; i++) {
				dimensions.add(null);
			}
		dimensions.set(2,depth);
	}
	
}
