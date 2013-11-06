package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.PalavraChaveDAO;
import br.unifesp.profetas.persistence.model.PalavraChave;

@Repository("palavrachaveDAO")
@Transactional
public class PalavraChaveDAOImpl extends AbstractHibernateDAO<PalavraChave> implements PalavraChaveDAO {

	public PalavraChaveDAOImpl() {
		setClazz(PalavraChave.class);
	}
	
	public PalavraChave getPalavraChaveById(Long id) {
		return findOne(id);
	}

	public List<PalavraChave> listPalavraChave() {
		return findAll();
	}

	public void savePalavraChave(PalavraChave palavraChave) {
		save(palavraChave);
	}

	public void updatePalavraChave(PalavraChave palavraChave) {
		update(palavraChave);
	}

	public void deletePalavraChave(PalavraChave palavraChave) {
		delete(palavraChave);
	}

}
