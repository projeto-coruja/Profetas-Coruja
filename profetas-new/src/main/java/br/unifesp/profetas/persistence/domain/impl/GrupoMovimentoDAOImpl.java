package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.GrupoMovimentoDAO;
import br.unifesp.profetas.persistence.model.GrupoMovimento;

@Repository("grupoMovimentoDAO")
@Transactional
public class GrupoMovimentoDAOImpl extends AbstractHibernateDAO<GrupoMovimento> implements GrupoMovimentoDAO {	
	 
	public GrupoMovimentoDAOImpl(){
		setClazz(GrupoMovimento.class);
	}

	public GrupoMovimento getGrupoMovimentoById(Long id) {
		return findOne(id);
	}

	public List<GrupoMovimento> listGrupoMovimento() {
		return findAll();
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