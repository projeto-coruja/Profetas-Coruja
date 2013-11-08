package br.unifesp.profetas.business.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.account.UserDTO;
import br.unifesp.profetas.business.profile.RoleDTO;

@Service("profetasAuthProvider")
public class ProfetasAuthenticationProvider implements UserDetailsService {
	
	@Autowired private Login login;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails user = null;
        try {
            UserDTO userDTO = login.getUserByUsername(username);
            System.out.println("user::: " + userDTO.getEmail());
            user =  new User(
                    userDTO.getEmail(), 
                    userDTO.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    getAuthorities(userDTO.getRoles()));

        } 
        catch (Exception e) {
            //logger.error ... 
            //TODO: sec
            throw new UsernameNotFoundException("Error ...");
        }
        return user;
	}
	
	public Collection<GrantedAuthority> getAuthorities(final List<RoleDTO> roles) {
        @SuppressWarnings("serial")
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(roles.size()) {
			{
                for(RoleDTO r : roles) {
                    add(new GrantedAuthorityImpl(r.getName()));
                }
            }
        };        
        return authList;
    }
}