package br.unifesp.profetas.business.account;

import org.springframework.security.access.prepost.PreAuthorize;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;

public interface ManagementAccount {
	
	public boolean userExists(String username);

	public MessageDTO saveUser(UserDTO userDTO);
	
	public MessageDTO recoveryPassStepOne(String username);
	
	public MessageDTO recoveryPassStepTwo(UserDTO userDTO);
	
	@PreAuthorize("hasRole('ADMIN')")
	public WrapperGrid<UserDTO> getUserList(String orderBy, 
			OrderType orderType, int page, int numRows);
	
	@PreAuthorize("hasRole('ADMIN')")
	public UserDTO getUserAccoutById(Long id);

	@PreAuthorize("hasRole('ADMIN')")
	public UserDTO getUserAccoutProfileById(Long id);
	
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO updateUserProfile(UserDTO userDTO);
}