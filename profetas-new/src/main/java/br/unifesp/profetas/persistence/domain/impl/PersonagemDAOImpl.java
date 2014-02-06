package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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
		criteria.add(Restrictions.eq("active", true));
		return (Personagem)criteria.uniqueResult();
	}

	public List<Personagem> listPersonagem() {
		Criteria criteria = getCurrentSession().createCriteria(Personagem.class);
		criteria.add(Restrictions.eq("active", true));
		return criteria.list();
	}
	
	public List<Personagem> searchPersonagem(String prefix) {
        Criteria criteria = getCurrentSession().createCriteria(Personagem.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.like("nome", prefix, MatchMode.START).ignoreCase());
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
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