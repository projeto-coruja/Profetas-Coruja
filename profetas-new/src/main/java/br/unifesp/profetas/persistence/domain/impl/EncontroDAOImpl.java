package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		return findOne(id);
	}

	public List<Encontro> listEncontro() {
		return findAll();
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