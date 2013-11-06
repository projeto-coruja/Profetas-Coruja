package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		return findOne(id);
	}
	
	public List<ReligiaoCrencas> listReligiaoCrencas(){
		return findAll();
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