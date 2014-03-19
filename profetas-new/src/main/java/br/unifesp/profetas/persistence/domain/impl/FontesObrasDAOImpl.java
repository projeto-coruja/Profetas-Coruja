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
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.model.Correspondencia;
import br.unifesp.profetas.persistence.model.FontesObras;

@Repository("fontesObrasDAO")
@Transactional
public class FontesObrasDAOImpl extends AbstractHibernateDAO<FontesObras> implements FontesObrasDAO {	
	 
	public FontesObrasDAOImpl(){
		setClazz(FontesObras.class);
	}

	public FontesObras getFontesObrasById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(FontesObras.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		return (FontesObras)criteria.uniqueResult();
	}

	public List<FontesObras> listFontesObras() {
		Criteria criteria = getCurrentSession().createCriteria(FontesObras.class);
		criteria.add(Restrictions.eq("active", true));
		return criteria.list();
	}
	
	public List<FontesObras> listFontesObrasWithLimit(Integer page,
			Integer numRows, String order, String field) {
		Criteria criteria = getCurrentSession().createCriteria(FontesObras.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setFirstResult((page-1)*numRows);
		criteria.setMaxResults(numRows);
		if(order.equals(OrderType.DESC.getDescription())) { criteria.addOrder(Order.desc(field)); }
		else { criteria.addOrder(Order.asc(field)); }
		return criteria.list();
	}

	public Long getTotalOfFontesObras() {
		Criteria criteria = getCurrentSession().createCriteria(FontesObras.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@Override
	public List<FontesObras> searchFontesObras(String prefix) {
		Criteria criteria = getCurrentSession().createCriteria(FontesObras.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.like("titulo", prefix, MatchMode.START).ignoreCase());
        criteria.addOrder(Order.asc("titulo"));
        return criteria.list();
	}

	public void saveFontesObras(FontesObras fontesObras) {
		save(fontesObras);
	}

	public void updateFontesObras(FontesObras fontesObras) {
		update(fontesObras);
	}

	public void deleteFontesObras(FontesObras fontesObras) {
		delete(fontesObras);
	}
}