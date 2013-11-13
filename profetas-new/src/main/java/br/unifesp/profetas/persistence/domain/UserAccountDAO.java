package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.UserAccount;

public interface UserAccountDAO {
	
	public void saveUserAccount(UserAccount user);
	
	public void updateUserAccount(UserAccount user);
	
	public UserAccount getUserByUsername(String username);
	
	public UserAccount getUserById(Long id);
	
	public List<UserAccount> getUserList();

	public UserAccount getUserByUsernameAndCode(String username, String activationCode);
}