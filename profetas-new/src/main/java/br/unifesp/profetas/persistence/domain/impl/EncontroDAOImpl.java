package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.model.Encontro;

@Repository("encontroDAO")
@Transactional
public class EncontroDAOImpl extends AbstractHibernateDAO<Encontro> implements
		EncontroDAO {

	public EncontroDAOImpl() {
		setClazz(Encontro.class);
	}

	public Encontro getEncontroById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(Encontro.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		return (Encontro)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Encontro> listEncontro() {
		Criteria criteria = getCurrentSession().createCriteria(Encontro.class);
		criteria.add(Restrictions.eq("active", true));
		return criteria.list();
	}
	
	public List<Encontro> listEncontroWithLimit(Integer page, Integer numRows,
			String order, String field) {
		Criteria criteria = getCurrentSession().createCriteria(Encontro.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setFirstResult((page-1)*numRows);
		criteria.setMaxResults(numRows);
		if(order.equals(OrderType.DESC.getDescription())) { criteria.addOrder(Order.desc(field)); }
		else { criteria.addOrder(Order.asc(field)); }
		return criteria.list();
	}

	public Long getTotalOfEncontros() {
		Criteria criteria = getCurrentSession().createCriteria(Encontro.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}

	public void saveEncontro(Encontro encontro) {
		save(encontro);
	}

	public void updateEncontro(Encontro encontro) {
		update(encontro);
	}

	public void deleteEncontro(Encontro encontro) {
		delete(encontro);
	}
}