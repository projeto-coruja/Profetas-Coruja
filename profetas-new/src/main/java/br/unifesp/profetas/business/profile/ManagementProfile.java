package br.unifesp.profetas.business.profile;

import java.util.List;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;

public interface ManagementProfile {
	
	public ProfileDTO getProfileById(Integer id);
	
	public MessageDTO createProfile(ProfileDTO profileDTO);
	
	public MessageDTO updateProfile(ProfileDTO profileDTO);
	
	public MessageDTO deleteProfile(ProfileDTO profileDTO);
	
	public WrapperGrid<ProfileDTO> getProfileList(String orderBy, 
			OrderType orderType, int page, int numRows);
	
	public List<RoleDTO> roleList();
}