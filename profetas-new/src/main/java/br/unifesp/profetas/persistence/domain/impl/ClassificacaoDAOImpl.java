package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.ClassificacaoDAO;
import br.unifesp.profetas.persistence.model.Classificacao;

@Repository("classificacaoDAO")
@Transactional
public class ClassificacaoDAOImpl extends AbstractHibernateDAO<Classificacao> implements ClassificacaoDAO {

	public ClassificacaoDAOImpl() {
		setClazz(Classificacao.class);
	}
	
	public Classificacao getClassificacaoById(Long id) {
		return findOne(id);
	}

	public List<Classificacao> listClassificacao() {
		return findAll();
	}

	public void saveClassificacao(Classificacao classificacao){
		save(classificacao);
	}

	public void updateClassificacao(Classificacao classificacao) {
		update(classificacao);
	}

	public void deleteClassificacao(Classificacao classificacao) {
		delete(classificacao);
	}

}
