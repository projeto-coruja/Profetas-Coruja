package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.UserAccount;

/*public interface UserAccountDAO extends GenericDAO<UserAccount> {
	
	public UserAccount getUserByUsername(String username);
}*/
public interface UserAccountDAO {
	
	public void saveUserAccount(UserAccount user);
	
	public UserAccount getUserByUsername(String username);
	
	public UserAccount getUserById(Long id);
	
	public List<UserAccount> getUserList();
}