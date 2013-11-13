package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.CorrespondenciaDAO;
import br.unifesp.profetas.persistence.model.Correspondencia;

@Repository("correspondenciaDAO")
@Transactional
public class CorrespondenciaDAOImpl extends AbstractHibernateDAO<Correspondencia> implements CorrespondenciaDAO {	
	 
	public CorrespondenciaDAOImpl(){
		setClazz(Correspondencia.class);
	}

	public Correspondencia getCorrespondenciaById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(Correspondencia.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		return (Correspondencia)criteria.uniqueResult();
	}

	public List<Correspondencia> listCorrespondencia() {
		Criteria criteria = getCurrentSession().createCriteria(Correspondencia.class);
		criteria.add(Restrictions.eq("active", true));
		return criteria.list();
	}

	public void saveCorrespondencia(Correspondencia correspondencia) {
		save(correspondencia);
	}

	public void updateCorrespondencia(Correspondencia correspondencia) {
		update(correspondencia);
	}

	public void deleteCorrespondencia(Correspondencia correspondencia) {
		delete(correspondencia);
	}
}