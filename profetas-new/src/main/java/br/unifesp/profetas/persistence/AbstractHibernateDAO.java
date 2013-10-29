package br.unifesp.profetas.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDAO<T extends Serializable>{
	
	private Class< T > clazz;
	 
	@Autowired private SessionFactory sessionFactory;
	 
	public void setClazz(final Class<T> clazzToSet){
		clazz = Preconditions.checkNotNull(clazzToSet);
	}
	   
	protected final Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	   
	public T findOne(final long id){
		return (T) getCurrentSession().get(clazz, id);
	}
	   
	public List<T> findAll(){
		return getCurrentSession().createQuery("FROM " + clazz.getName()).list();
	}
 
	public void save(final T entity){
		Preconditions.checkNotNull(entity);
		getCurrentSession().saveOrUpdate(entity);
	}
 
	public T update(final T entity){
		Preconditions.checkNotNull(entity);
		return (T) getCurrentSession().merge(entity);
	}
 
	public void delete(final T entity){
		Preconditions.checkNotNull(entity);
		getCurrentSession().delete(entity);
	}
	
	public void deleteById(final long id){
		final T entity = findOne(id);
		Preconditions.checkNotNull(entity);
		delete(entity);
	}
}