package br.unifesp.profetas.business.authentication;

import br.unifesp.profetas.business.account.dto.UserDTO;

public interface Login {

	public UserDTO getUserByUsername(String username);
}