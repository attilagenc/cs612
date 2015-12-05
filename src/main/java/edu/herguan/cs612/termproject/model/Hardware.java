package edu.herguan.cs612.termproject.model;

import javax.persistence.Embeddable;

@Embeddable
public class Hardware {
	Boolean accelerometer;
	String audioJack;
	String cpu;
	Boolean fmRadio;
	Boolean physicalKeyboard;
	String usb;
}
