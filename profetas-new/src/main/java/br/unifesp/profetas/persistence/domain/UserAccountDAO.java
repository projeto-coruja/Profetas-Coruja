package br.unifesp.profetas.persistence.domain;

import br.unifesp.profetas.persistence.model.UserAccount;

public interface UserAccountDAO {
	
	public void saveUserAccount(UserAccount user);
	
	public UserAccount getUserByUsername(String username);
}