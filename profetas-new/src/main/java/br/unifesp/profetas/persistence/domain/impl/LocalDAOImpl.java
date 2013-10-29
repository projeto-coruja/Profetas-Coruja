package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.model.Local;

@Repository("localDAO")
@Transactional
public class LocalDAOImpl extends AbstractHibernateDAO<Local> implements LocalDAO {	
	 
	public LocalDAOImpl(){
		setClazz(Local.class);
	}

	public Local getLocalById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(Local.class);
		criteria.add(Restrictions.eq("id", id));
		return (Local)criteria.uniqueResult();
	}

	public List<Local> listLocal() {
		return findAll();
	}

	public void saveLocal(Local local) {
		save(local);
	}

	public void updateLocal(Local local) {
		update(local);
	}

	public void deleteLocal(Local local) {
		delete(local);
	}
}