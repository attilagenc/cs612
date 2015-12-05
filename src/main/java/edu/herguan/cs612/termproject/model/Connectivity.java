package edu.herguan.cs612.termproject.model;

import javax.persistence.Embeddable;

@Embeddable
public class Connectivity {
	String bluetooth;
	String cell;
	Boolean gps;
	Boolean infrared;
	String wifi;	
}
