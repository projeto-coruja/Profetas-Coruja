package br.unifesp.coruja.meta.persistence;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class PersistenceUtility {
	
	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;
	private Log log;
	
	public PersistenceUtility() {
		serviceRegistry = new ServiceRegistryBuilder();
		sessionFactory = configureSessionFactory();
		log = (Log) LogFactory.getLog(PersistenceUtility.class);
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		HibernateConfiguration config = new HibernateConfiguration();
		config.sessionFactoryBean();
		serviceRegistry = new ServiceRegistryBuilder().applySetting(config.sessionFactoryBean());        
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactory;
	}
	
}
