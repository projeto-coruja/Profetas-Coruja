package br.unifesp.coruja.meta.web.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unifesp.coruja.meta.persistence.dto.DTO;
import br.unifesp.coruja.meta.persistence.util.PersistenceAccess;

@Service
public class HibernateUserDetailsService implements UserDetailsService {
	
	@Autowired
	private PersistenceAccess pa;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<DTO> resultSet = pa.findEntity("from UserMO where username = '" + username +"'");
		if(resultSet == null) throw new UsernameNotFoundException(username + " not found");
		
		UserDetails user = (UserDetails) resultSet.get(0);
		if(user.getAuthorities() == null || user.getAuthorities().isEmpty())
			throw new UsernameNotFoundException(username + " doesn't have any authority");
		else return user;
	}

}
