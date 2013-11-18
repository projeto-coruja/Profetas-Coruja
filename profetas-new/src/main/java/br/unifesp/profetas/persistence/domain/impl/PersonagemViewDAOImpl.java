package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.PersonagemViewDAO;
import br.unifesp.profetas.persistence.model.PersonagemView;

@Repository("personagemViewDAO")
@Transactional
public class PersonagemViewDAOImpl extends AbstractHibernateDAO<PersonagemView> implements PersonagemViewDAO {

	public PersonagemViewDAOImpl() {
		setClazz(PersonagemView.class);
	}

	public List<PersonagemView> listPersonagemView() {
		Criteria criteria = getCurrentSession().createCriteria(PersonagemView.class);
		return criteria.list();
	}
}