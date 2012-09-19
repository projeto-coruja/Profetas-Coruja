package br.unifesp.maritaca.dataaccess.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.maritaca.dataaccess.domain.UserVO;

@Repository("userDAO")
public class HibernateUserDAO implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
	public UserVO findUserByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserVO.class)
        		.add(Restrictions.eq("email", email));
        List userLst = criteria.list();
        if(userLst != null && !userLst.isEmpty())
        	return (UserVO)userLst.get(0);
        return null;
	}
    
    @Transactional
    public void saveUser(UserVO user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
    
    @Transactional(readOnly = true)
    public List<UserVO> findAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserVO");
        return query.list();
    }
}