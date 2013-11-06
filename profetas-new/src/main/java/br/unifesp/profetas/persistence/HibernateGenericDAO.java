package br.unifesp.profetas.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

//@Repository
public class HibernateGenericDAO <T extends Serializable> implements GenericDAO<T> {
	
	protected SessionFactory sessionFactory;
	private Class<T> persistentClass;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public HibernateGenericDAO() {}
	
	public HibernateGenericDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public Class<T> getPersistentClass() {
        return persistentClass;
    }
	
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}
	
	
	public List<T> findAll() {
		return findByCriteria();
	}

	public T save(T entity) {
		Assert.notNull(entity);
		getSession().save(entity);
		return entity;
	}

	public Object update(Object entity) {
		Assert.notNull(entity);
		getSession().update(entity);
		return entity;
	}

	public Object saveOrUpdate(Object entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public Object merge(Object entity) {
		Assert.notNull(entity);
		return getSession().merge(entity);
	}

	public void delete(Object entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
	}
	
	public List<T> listByCriteria(DetachedCriteria c) {
		// TODO Auto-generated method stub
		return null;
	}
		
}