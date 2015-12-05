package edu.herguan.cs612.termproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Camera {
	@Column(name="pcamera")
	String primary;
	
//*
	@Transient
	List<String> features;
	
	
	@Access(AccessType.PROPERTY)
	@Column(length=1024)
	public String getFeaturesString() {
		if (features == null)
			return null;
		if (features.size()==1)
			return features.get(0);
		
		StringBuilder sb = new StringBuilder();
		sb.append(features.get(0));
		for (int i = 1; i < features.size(); i++) {
			sb.append(':').append(features.get(i));			
		}
		return sb.toString();
	}

	public void setFeaturesString(String featuresString) {
		String[] tokens = featuresString.split(":");
		features = new ArrayList<>(tokens.length);
		for (int i = 0; i < tokens.length; i++) {
			features.add(tokens[i]);
		}
	}
//*/	
}
