package br.unifesp.coruja.meta.persistence;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PersistenceUtility {

	private static SessionFactory sessionFactory = configureSessionFactory();
	//private static ServiceRegistry serviceRegistry;
	private static Log log = LogFactory.getLog(PersistenceUtility.class);
	private static HibernateConfiguration hibernateConfig = new HibernateConfiguration();
	
	/**********************************************************
	 * 						SESSION
	 **********************************************************/

	public static Session openSession() throws HibernateException {
		buildIfNeeded();
		return sessionFactory.openSession();
	}
	
	public static void close(Session session) {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException ignored) {
				log.error("Couldn't close Session", ignored);
			}
		}
	}
	
	/**********************************************************
	 * 					SESSION FACTORY
	 **********************************************************/
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void closeSessionFactory() {
		sessionFactory.close();	
	}

	public SessionFactory buildSessionFactory() throws HibernateException{
		if(sessionFactory != null){
			sessionFactory.close();
		}
		return sessionFactory;
	}

	private static SessionFactory configureSessionFactory() throws HibernateException{
		try {
			sessionFactory = (SessionFactory) hibernateConfig.sessionFactoryBean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionFactory;
	}
	
	public static SessionFactory buildIfNeeded() throws DataAccessLayerException{
		if (sessionFactory != null) {
			return sessionFactory;
		}
		try {
			return configureSessionFactory();
		} catch (HibernateException e) {
			throw new DataAccessLayerException(e);
		}
	}
	
	/**********************************************************
	 * 						OTHERS
	 **********************************************************/
	public static void rollback(Transaction tx) {
		try {
			if (tx != null) {
				tx.rollback();
			}
		} catch (HibernateException ignored) {
			log.error("Couldn't rollback Transaction", ignored);
		}
	}

}
