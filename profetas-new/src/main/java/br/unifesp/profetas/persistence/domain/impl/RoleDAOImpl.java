package br.unifesp.profetas.persistence.domain.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.profetas.persistence.AbstractHibernateDAO;
import br.unifesp.profetas.persistence.domain.RoleDAO;
import br.unifesp.profetas.persistence.model.Role;

@Repository("roleDAO")
@Transactional
public class RoleDAOImpl extends AbstractHibernateDAO<Role> implements RoleDAO {	
	 
	public RoleDAOImpl(){
		setClazz(Role.class);
	}

	public List<Role> roleList() {
		return findAll();
	}

	public Role getRoleById(Integer id) {
		Criteria criteria = getCurrentSession().createCriteria(Role.class);
		criteria.add(Restrictions.eq("id", id));
		return (Role)criteria.uniqueResult();
	}

	public void saveRole(Role role) {
		save(role);
	}

	public void updateRole(Role role) {
		update(role);
	}

	public void deleteRole(Role role) {
		delete(role);
	}
}