package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.ProfileDAO;
import br.unifesp.profetas.persistence.model.Profile;

@Repository("profileDAO")
@Transactional
public class ProfileDAOImpl extends AbstractHibernateDAO<Profile> implements ProfileDAO {	
	 
	public ProfileDAOImpl(){
		setClazz(Profile.class);
	}

	public List<Profile> profileList() {
		return findAll();
	}

	public Profile getProfileById(Integer id) {
		Criteria criteria = getCurrentSession().createCriteria(Profile.class);
		criteria.add(Restrictions.eq("id", id));
		return (Profile)criteria.uniqueResult();
	}
	
	public Profile getProfileByName(String name) {
		Criteria criteria = getCurrentSession().createCriteria(Profile.class);
		criteria.add(Restrictions.eq("name", name));
		return (Profile)criteria.uniqueResult();
	}

	public void saveProfile(Profile profile) {
		save(profile);
	}

	public void updateLocal(Profile profile) {
		update(profile);
	}

	public void deleteLocal(Profile profile) {
		delete(profile);
	}
}