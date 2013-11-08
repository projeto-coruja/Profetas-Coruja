package br.unifesp.profetas.business.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.account.UserDTO;
import br.unifesp.profetas.business.profile.RoleDTO;
import br.unifesp.profetas.persistence.domain.UserAccountDAO;
import br.unifesp.profetas.persistence.model.Role;
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
			
			if(!userAcc.getProfile().getRoles().isEmpty()){
				List<Role> roles = new ArrayList<Role>(userAcc.getProfile().getRoles());
				List<RoleDTO> listDTO = new ArrayList<RoleDTO>(userAcc.getProfile().getRoles().size());
				for(Role r : roles){
					RoleDTO rDTO = new RoleDTO();
					rDTO.setName(r.getName());
					listDTO.add(rDTO);
				}
				userDTO.setRoles(listDTO);
			}
		}		
		return userDTO;
	}

}