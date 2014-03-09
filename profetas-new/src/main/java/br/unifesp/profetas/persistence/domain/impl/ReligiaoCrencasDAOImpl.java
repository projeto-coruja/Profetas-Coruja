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
import br.unifesp.profetas.persistence.domain.ReligiaoCrencasDAO;
import br.unifesp.profetas.persistence.model.ReligiaoCrencas;

@Repository("religiaoCrencasDAO")
@Transactional
public class ReligiaoCrencasDAOImpl extends AbstractHibernateDAO<ReligiaoCrencas> implements ReligiaoCrencasDAO {	
	 
	public ReligiaoCrencasDAOImpl(){
		setClazz(ReligiaoCrencas.class);
	}

	public ReligiaoCrencas getReligiaoCrencasById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(ReligiaoCrencas.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		return (ReligiaoCrencas)criteria.uniqueResult();
	}
	
	public List<ReligiaoCrencas> listReligiaoCrencas(){
		Criteria criteria = getCurrentSession().createCriteria(ReligiaoCrencas.class);
		criteria.add(Restrictions.eq("active", true));
		return criteria.list();
	}
	
	public List<ReligiaoCrencas> listReligiaoCrencasWithLimit(Integer page,
			Integer numRows, String order, String field) {
		Criteria criteria = getCurrentSession().createCriteria(ReligiaoCrencas.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setFirstResult((page-1)*numRows);
		criteria.setMaxResults(numRows);
		if(order.equals(OrderType.DESC.getDescription())) { criteria.addOrder(Order.desc(field)); }
		else { criteria.addOrder(Order.asc(field)); }
		return criteria.list();
	}

	public Long getTotalOfReligiaoCrencas() {
		Criteria criteria = getCurrentSession().createCriteria(ReligiaoCrencas.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@Override
	public List<ReligiaoCrencas> searchReligioes(String prefix) {
		Criteria criteria = getCurrentSession().createCriteria(ReligiaoCrencas.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.like("nome", prefix, MatchMode.START).ignoreCase());
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
	}

	public void saveReligiaoCrencas(ReligiaoCrencas religiaoCrencas) {
		save(religiaoCrencas);
	}
	
	public void updateReligiaoCrencas(ReligiaoCrencas religiaoCrencas) {
		update(religiaoCrencas);
	}

	public void deleteReligiaoCrencas(ReligiaoCrencas religiaoCrencas) {
		delete(religiaoCrencas);
	}
}