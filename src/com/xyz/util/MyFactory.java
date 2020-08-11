package com.infodart.util;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Myfactory implements ServletContextListener {

	private static EntityManagerFactory manager;

	static {

		manager = Persistence.createEntityManagerFactory("first");
	}

	public static EntityManager getEntityManager() {
		return manager.createEntityManager();
	}

	
}
