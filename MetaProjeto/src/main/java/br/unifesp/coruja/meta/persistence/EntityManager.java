package br.unifesp.coruja.meta.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.unifesp.coruja.meta.model.Entity;

public class EntityManager {
	
	private Session session;
	private Transaction transaction;
	
	/**********************************************************
	 * 						CONSTRUCTOR
	 **********************************************************/
	public EntityManager() {
		PersistenceUtility.buildIfNeeded();
	}
	
	/**********************************************************
	 * 					BASIC OPERATIONS
	 **********************************************************/
	private void startOperation() {
		session = PersistenceUtility.openSession();
		transaction = session.beginTransaction();
	}
	
	public void finishOperation() {
		PersistenceUtility.close(session);
	}
	
	/**********************************************************
	 * 					DATABASE OPERATIONS
	 **********************************************************/
	public void save(Entity obj) throws DataAccessLayerException{
		try {
			startOperation();
			session.save(obj);
			transaction.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			finishOperation();
		}
	}
	
	public void update(Entity obj) throws DataAccessLayerException{
		try{
			startOperation();
			session.update(obj);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
	}

	public void delete(Entity obj) throws DataAccessLayerException{
		try{
			startOperation();
			session.delete(obj);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Entity find(Class table, long id) throws DataAccessLayerException{
		Entity obj = null;
		try{
			startOperation();
			obj = (Entity) session.get(table, id);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
		return obj;
	}


	@SuppressWarnings({ "unchecked"})
	public List<Entity> find(String criteria) throws DataAccessLayerException{
		List<Entity> obj = null;
		try{
			startOperation();
			Query query = session.createQuery(criteria);
			obj = query.list();
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}
		return obj;
	}

	/**********************************************************
	 * 						EXCEPTIONS
	 **********************************************************/
	private void handleException(HibernateException e) throws DataAccessLayerException {
		e.printStackTrace();
		PersistenceUtility.rollback(transaction);
		throw new DataAccessLayerException(e);
	}

}
