package br.unifesp.profetas.business.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.account.dto.RoleDTO;
import br.unifesp.profetas.business.account.dto.UserDTO;
import br.unifesp.profetas.persistence.domain.UserAccountDAO;
import br.unifesp.profetas.persistence.model.UserAccount;

@Service("login")
public class LoginImpl implements Login {
	
	@Autowired private UserAccountDAO userAccountDAO;

	public UserDTO getUserByUsername(String username) {
		UserDTO userDTO = new UserDTO();
		UserAccount userAcc = userAccountDAO.getUserByUsername(username);
		if(userAcc != null){
			userDTO.setEmail(userAcc.getEmail());
			userDTO.setPassword(userAcc.getPassword());
			List<RoleDTO> roles = new ArrayList<RoleDTO>();
				RoleDTO role = new RoleDTO();
				role.setName("ADMIN");
			roles.add(role);
			userDTO.setRoles(roles);
		}		
		return userDTO;
	}

}