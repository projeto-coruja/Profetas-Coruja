package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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

	public List<PersonagemView> search(int page, int numRows, String words) {
		Criteria criteria = getCurrentSession().createCriteria(PersonagemView.class);
		String[] tokens = words.split(" ");
		int tokensLength = tokens.length;
		Criterion c = null;
		for(int i = 0; i < tokensLength; i++){
			if(c == null)
				c = Restrictions.like("texto", "%"+tokens[i].trim()+"%").ignoreCase();
			else
				c = Restrictions.or(c, Restrictions.like("texto", "%"+tokens[i].trim()+"%").ignoreCase());
		}
		if(c != null){
			criteria.add(c);
			return criteria.list();
		}
		return null;
	}
}