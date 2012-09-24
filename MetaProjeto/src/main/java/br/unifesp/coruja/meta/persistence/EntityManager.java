package br.unifesp.coruja.meta.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.unifesp.coruja.meta.model.Entity;

public class EntityManager {
	
	private Session session;
	private Transaction transaction;
	private HibernateConfiguration config;
	
	public EntityManager() {
		config = new HibernateConfiguration();
	}
	
	public void save(Entity obj){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
