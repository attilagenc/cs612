package edu.herguan.cs612.termproject.model;

import javax.persistence.Embeddable;

@Embeddable
public class Battery {
	String standbyTime;
	String talkTime;
	String type;
}
