package edu.herguan.cs612.termproject.model;

import javax.persistence.Embeddable;

@Embeddable
public class Display {
	String screenResolution;
	String screenSize;
	Boolean touchScreen;
}
