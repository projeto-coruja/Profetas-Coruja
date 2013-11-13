package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.UserAccountDAO;
import br.unifesp.profetas.persistence.model.UserAccount;

@Repository("userAccountDAO")
@Transactional
public class UserAccountDAOImpl extends AbstractHibernateDAO<UserAccount> implements UserAccountDAO {	
		 
	public UserAccountDAOImpl(){
		setClazz(UserAccount.class);
	}
	
	@Autowired SessionFactory sessionFactory;

	public void saveUserAccount(UserAccount user) {
		save(user);		
	}
	
	public void updateUserAccount(UserAccount user) {
		update(user);		
	}
	
	public UserAccount getUserByUsername(String username) {	
		Criteria criteria = getCurrentSession().createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("email", username));
		return (UserAccount)criteria.uniqueResult();
	}
	
	public UserAccount getUserById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("id", id));
		return (UserAccount)criteria.uniqueResult();
	}

	public List<UserAccount> getUserList() {
		return findAll();
	}

	public UserAccount getUserByUsernameAndCode(String username, String activationCode) {
		Criteria criteria = getCurrentSession().createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("email", username));
		criteria.add(Restrictions.eq("activationCode", activationCode));
		return (UserAccount)criteria.uniqueResult();
	}		
}