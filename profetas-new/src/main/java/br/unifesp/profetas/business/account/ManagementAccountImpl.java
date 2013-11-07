package br.unifesp.profetas.business.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.account.dto.UserDTO;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.persistence.domain.UserAccountDAO;
import br.unifesp.profetas.persistence.model.Local;
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

	private MessageDTO isNotValidUserData(UserDTO userDTO){
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
		return null;
	}
	
	private UserAccount getUserAccountFromDTO(UserDTO userDTO, Profile profile){
		UserAccount userAcc = new UserAccount();
		userAcc.setName(userDTO.getFullName());
		userAcc.setEmail(userDTO.getEmail());
		userAcc.setPassword(UtilCodification.encryptHex(userDTO.getPassword(), "MD5"));
		userAcc.setCreationDate(new Date());
		if(profile != null){
			userAcc.setProfile(profile);
		}
		return userAcc;
	}
	
	public MessageDTO saveUser(UserDTO userDTO) {
		MessageDTO isNotValid = isNotValidUserData(userDTO);
		if(isNotValid != null){
			return isNotValid;
		}
		Profile profile = new Profile(ProfetasConstants.ID_PROFILE_NEWUSER);
		UserAccount userAcc = getUserAccountFromDTO(userDTO, profile);
		userAccountDAO.saveUserAccount(userAcc);
		if(userAcc.getId() != null){
			return new MessageDTO(getText("msg_user_account_created"), MessageType.SUCCESS);
		} else {
			return new MessageDTO(getText("err_user_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO saveUserWithProfile(UserDTO userDTO) {
		MessageDTO isNotValid = isNotValidUserData(userDTO);
		if(isNotValid != null){
			return isNotValid;
		}
		if(userDTO.getIdProfile() == null){
			return new MessageDTO(getText("err_user_not_saved"), MessageType.ERROR);
		}
		Profile profile = new Profile(userDTO.getIdProfile());
		UserAccount userAcc = getUserAccountFromDTO(userDTO, profile);
		userAccountDAO.saveUserAccount(userAcc);
		if(userAcc.getId() != null){
			return new MessageDTO(getText("msg_user_account_created"), MessageType.SUCCESS);
		} else {
			return new MessageDTO(getText("err_user_not_saved"), MessageType.ERROR);
		}
	}
	
	public WrapperGrid<UserDTO> getUserList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<UserAccount> list = userAccountDAO.getUserList();
		int total = list == null ? 0 : list.size();//TODO: count
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		for(UserAccount u : list){
			UserDTO uDTO = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getProfile().getName());
			listDTO.add(uDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}

	public UserDTO getUserAccoutById(Long id) {
		UserDTO uDTO = null;
		UserAccount u = userAccountDAO.getUserById(id);
		if(u != null){
			uDTO = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getProfile().getId());
		}
		return uDTO;
	}

	public UserDTO getUserAccoutProfileById(Long id) {
		UserDTO uDTO = null;
		UserAccount u = userAccountDAO.getUserById(id);
		if(u != null){
			uDTO = new UserDTO(u.getId(), u.getProfile().getId());
		}
		return uDTO;
	}

	public MessageDTO updateUserProfile(UserDTO userDTO) {
		if(userDTO.getId() == null){
			return new MessageDTO(getText("err_profile_not_updated"), MessageType.ERROR);
		}
		if(userDTO.getIdProfile() == null){
			return new MessageDTO(getText("err_profile_not_updated"), MessageType.ERROR);
		}
		try{
			Profile profile = new Profile(userDTO.getIdProfile());
			UserAccount userAcc = userAccountDAO.getUserById(userDTO.getId());
			userAcc.setProfile(profile);
			userAccountDAO.updateUserAccount(userAcc);
			return new MessageDTO(getText("msg_profile_updated"), MessageType.SUCCESS);
		} catch(Exception e){
			return new MessageDTO(getText("err_profile_not_updated"), MessageType.ERROR);
		}
	}
}