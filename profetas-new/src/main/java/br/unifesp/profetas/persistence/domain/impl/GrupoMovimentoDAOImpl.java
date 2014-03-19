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
import br.unifesp.profetas.persistence.domain.GrupoMovimentoDAO;
import br.unifesp.profetas.persistence.model.GrupoMovimento;
import br.unifesp.profetas.persistence.model.Local;

@Repository("grupoMovimentoDAO")
@Transactional
public class GrupoMovimentoDAOImpl extends AbstractHibernateDAO<GrupoMovimento> implements GrupoMovimentoDAO {	
	 
	public GrupoMovimentoDAOImpl(){
		setClazz(GrupoMovimento.class);
	}

	public GrupoMovimento getGrupoMovimentoById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(GrupoMovimento.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		return (GrupoMovimento)criteria.uniqueResult();
	}

	public List<GrupoMovimento> listGrupoMovimento() {
		Criteria criteria = getCurrentSession().createCriteria(GrupoMovimento.class);
		criteria.add(Restrictions.eq("active", true));
		return criteria.list();
	}
	
	public List<GrupoMovimento> listGrupoMovimentoWithLimit(Integer page,
			Integer numRows, String order, String field) {
		Criteria criteria = getCurrentSession().createCriteria(GrupoMovimento.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setFirstResult((page-1)*numRows);
		criteria.setMaxResults(numRows);
		if(order.equals(OrderType.DESC.getDescription())) { criteria.addOrder(Order.desc(field)); }
		else { criteria.addOrder(Order.asc(field)); }
		return criteria.list();
	}

	public Long getTotalOfGrupoMovimentos() {
		Criteria criteria = getCurrentSession().createCriteria(GrupoMovimento.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@Override
	public List<GrupoMovimento> searchGrupoMovimento(String prefix) {
		Criteria criteria = getCurrentSession().createCriteria(GrupoMovimento.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.like("nome", prefix, MatchMode.START).ignoreCase());
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
	}

	public void saveGrupoMovimento(GrupoMovimento grupoMovimento) {
		save(grupoMovimento);
	}

	public void updateGrupoMovimento(GrupoMovimento grupoMovimento) {
		update(grupoMovimento);
	}

	public void deleteGrupoMovimento(GrupoMovimento grupoMovimento) {
		delete(grupoMovimento);
	}
}