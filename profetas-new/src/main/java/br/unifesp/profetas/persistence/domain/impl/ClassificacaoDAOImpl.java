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
	 
	public ClassificacaoDAOImpl(){
		setClazz(Classificacao.class);
	}

	public List<Classificacao> listClassificao() {
		return findAll();
	}
}