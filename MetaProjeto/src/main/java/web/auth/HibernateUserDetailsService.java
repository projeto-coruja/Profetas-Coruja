package web.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import persistence.dto.DTO;
import persistence.util.PersistenceAccess;

/**
 * Service used by Spring Security to retrive data from database using our persistence architecture.
 * 
 * @author Daniel Gracia
 * @see org.springframework.security.core.userdetails.UserDetailsService;
 *
 */

@Service
public class HibernateUserDetailsService implements UserDetailsService {
	
	@Autowired
	private PersistenceAccess pa;

	/**
	 * Loads an user data querying the database by username (email).
	 * Fairly straightforward.
	 *  
	 *  @param username the desired user "username" (email)
	 *  @throws UsernameNotFoundException either when the user isn't found or if the user doesn't have any kind of authority
	 *  @return an {@code User} object
	 */
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
