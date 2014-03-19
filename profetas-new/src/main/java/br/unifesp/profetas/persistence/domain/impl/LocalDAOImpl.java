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
		criteria.add(Restrictions.eq("active", true));
		return (Local)criteria.uniqueResult();
	}
	
	public Local getLocalByCountryAndNome(String country, String nome) {
		Criteria criteria = getCurrentSession().createCriteria(Local.class);
		criteria.add(Restrictions.eq("country", country));
		criteria.add(Restrictions.eq("nome", nome));
		criteria.add(Restrictions.eq("state", ""));
		criteria.add(Restrictions.eq("city", ""));
		criteria.add(Restrictions.eq("active", true));
		return (Local)criteria.uniqueResult();
	}	

	public List<Local> listLocal() {
		Criteria criteria = getCurrentSession().createCriteria(Local.class);
		criteria.add(Restrictions.eq("active", true));
		return criteria.list();
	}
	
	public List<Local> listLocalWithLimit(Integer page, Integer numRows,
			String order, String field) {
		Criteria criteria = getCurrentSession().createCriteria(Local.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setFirstResult((page-1)*numRows);
		criteria.setMaxResults(numRows);
		if(order.equals(OrderType.DESC.getDescription())) { criteria.addOrder(Order.desc(field)); }
		else { criteria.addOrder(Order.asc(field)); }
		return criteria.list();
	}

	public Long getTotalOfLocais() {
		Criteria criteria = getCurrentSession().createCriteria(Local.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	public List<Local> searchLocal(String prefix) {
        Criteria criteria = getCurrentSession().createCriteria(Local.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.like("nome", prefix, MatchMode.START).ignoreCase());
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
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