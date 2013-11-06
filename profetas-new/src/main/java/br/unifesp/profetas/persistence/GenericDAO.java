package br.unifesp.profetas.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface GenericDAO<T extends Serializable> {
	
	public List<T> listByCriteria(DetachedCriteria c);
	
	public List<T> findAll();

	public T save(T entity);
	
	public Object update(Object entity);
	
	public Object saveOrUpdate(Object entity);
	
	public Object merge(Object entity);
	
	public void delete(Object entity);
}