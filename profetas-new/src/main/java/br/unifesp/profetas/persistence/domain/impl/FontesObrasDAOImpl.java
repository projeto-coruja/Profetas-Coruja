package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
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