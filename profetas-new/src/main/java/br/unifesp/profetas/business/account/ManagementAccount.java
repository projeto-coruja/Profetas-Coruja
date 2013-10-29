package br.unifesp.profetas.business.account;

import br.unifesp.profetas.business.account.dto.UserDTO;
import br.unifesp.profetas.business.common.MessageDTO;

public interface ManagementAccount {
	
	public boolean userExists(String username);

	public MessageDTO saveUser(UserDTO userDTO);
}