package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.business.common.OrderType;
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
	
	public List<Correspondencia> listCorrespondenciaWithLimit(Integer page,
			Integer numRows, String order, String field) {
		Criteria criteria = getCurrentSession().createCriteria(Correspondencia.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setFirstResult((page-1)*numRows);
		criteria.setMaxResults(numRows);
		if(order.equals(OrderType.DESC.getDescription())) { criteria.addOrder(Order.desc(field)); }
		else { criteria.addOrder(Order.asc(field)); }
		return criteria.list();
	}

	public Long getTotalOfCorrespondencias() {
		Criteria criteria = getCurrentSession().createCriteria(Correspondencia.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@Override
	public List<Correspondencia> searchCorrespondencia(String prefix) {
		Criteria criteria = getCurrentSession().createCriteria(Correspondencia.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.createAlias("remetente", "remetente");//TODO: remetente is active
        criteria.add(Restrictions.like("remetente.nome", prefix, MatchMode.START).ignoreCase());
        criteria.addOrder(Order.asc("remetente.nome"));
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