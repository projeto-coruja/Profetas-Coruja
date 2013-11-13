package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
		Criteria criteria = getCurrentSession().createCriteria(Classificacao.class);
		criteria.add(Restrictions.eq("id", id));
		return (Classificacao)criteria.uniqueResult();
	}

	public List<Classificacao> listClassificacao() {
		Criteria criteria = getCurrentSession().createCriteria(Classificacao.class);
		return criteria.list();
	}

	public Classificacao getClassificacaoByNome(String classificacao) {
		Criteria criteria = getCurrentSession().createCriteria(Classificacao.class);
		criteria.add(Restrictions.eq("tipo", classificacao));
		return (Classificacao)criteria.uniqueResult();
	}

	public void saveClassificacao(Classificacao classificacao) {
		save(classificacao);
	}
}