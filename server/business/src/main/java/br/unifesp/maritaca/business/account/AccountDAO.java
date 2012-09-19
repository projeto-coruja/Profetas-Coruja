package br.unifesp.maritaca.business.account;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.unifesp.maritaca.business.BaseDAO;

@Service("accountDAO")
public class AccountDAO extends BaseDAO {
	
	/**
	 * Saves the user passed as parameter in the database.
	 * If the user has no MaritacaList set it also creates
	 * one.
	 * 
	 * @param user
	 */
	public void saveUser() {
					
	}
	
	public String findUserByKey(UUID userKey){
		return null;
	}

}