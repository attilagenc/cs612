package edu.herguan.cs612.termproject;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory emf = buildFactory();

	synchronized private static EntityManagerFactory buildFactory() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("aws-ds");
		return emf;
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null)
			emf = buildFactory();
		return emf;
	}
}
