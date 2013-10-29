package br.unifesp.profetas.business.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.account.dto.UserDTO;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.persistence.domain.UserAccountDAO;
import br.unifesp.profetas.persistence.model.Profile;
import br.unifesp.profetas.persistence.model.UserAccount;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilCodification;
import br.unifesp.profetas.util.UtilValidator;

@Service("account")
public class ManagementAccountImpl extends AbstractBusiness implements ManagementAccount {
	
	@Autowired UserAccountDAO userAccountDAO;
	
	public boolean userExists(String username) {
		if(userAccountDAO.getUserByUsername(username) != null){
			return true;
		}
		return false;
	}

	public MessageDTO saveUser(UserDTO userDTO) {
		
		if(userExists(userDTO.getEmail())){
			return new MessageDTO(getText("err_user_exists"), MessageType.ERROR);
		}
		if(!UtilValidator.validateField(userDTO.getFullName(), ProfetasConstants.MIN_FULLNAME_SIZE, ProfetasConstants.MAX_FULLNAME_SIZE)){
			return new MessageDTO(getText("err_user_name_invalid"), MessageType.ERROR);
		}
		if(!UtilValidator.validateField(userDTO.getPassword(), ProfetasConstants.MIN_PASSWORD_SIZE, ProfetasConstants.MAX_PASSWORD_SIZE)){
			return new MessageDTO(getText("err_user_pass_invalid"), MessageType.ERROR);
		}
		if(!UtilValidator.validateEmail(userDTO.getEmail())){
			return new MessageDTO(getText("err_user_email_invalid"), MessageType.ERROR);
		}
		
		UserAccount userAcc = new UserAccount();
		userAcc.setName(userDTO.getFullName());
		userAcc.setEmail(userDTO.getEmail());
		userAcc.setPassword(UtilCodification.encryptHex(userDTO.getPassword(), "MD5"));
		userAcc.setCreationDate(new Date());
		userAcc.setProfile(new Profile(1));//TODO:
		
		userAccountDAO.saveUserAccount(userAcc);
		if(userAcc.getId() != null){
			return new MessageDTO(getText("msg_user_account_created"), MessageType.SUCCESS);
		} else {
			return new MessageDTO(getText("err_user_not_saved"), MessageType.ERROR);
		}
	}
}