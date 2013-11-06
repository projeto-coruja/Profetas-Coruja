package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.model.Personagem;

@Repository("personagemDAO")
@Transactional
public class PersonagemDAOImpl extends AbstractHibernateDAO<Personagem> implements PersonagemDAO {	
	 
	public PersonagemDAOImpl(){
		setClazz(Personagem.class);
	}

	public Personagem getPersonagemById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(Personagem.class);
		criteria.add(Restrictions.eq("id", id));
		return (Personagem)criteria.uniqueResult();
	}

	public List<Personagem> listPersonagem() {
		return findAll();
	}

	public void savePersonagem(Personagem personagem) {
		save(personagem);
	}

	public void updatePersonagem(Personagem personagem) {
		update(personagem);
	}

	public void deletePersonagem(Personagem personagem) {
		delete(personagem);
	}
}