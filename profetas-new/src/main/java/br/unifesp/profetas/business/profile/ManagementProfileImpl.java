package br.unifesp.profetas.business.profile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.ProfileDAO;
import br.unifesp.profetas.persistence.domain.RoleDAO;
import br.unifesp.profetas.persistence.model.Profile;
import br.unifesp.profetas.persistence.model.Role;
import br.unifesp.profetas.util.UtilValidator;

@Service("mProfile")
public class ManagementProfileImpl extends AbstractBusiness implements ManagementProfile {
	
	@Autowired private ProfileDAO profileDAO;
	@Autowired private RoleDAO roleDAO;

	public ProfileDTO getProfileById(Integer id) {
		Profile profile = profileDAO.getProfileById(id);
		if(profile != null){
			ProfileDTO pDTO = new ProfileDTO(profile.getId(), profile.getName());
			
			List<Role> roles = new ArrayList<Role>(profile.getRoles());
			Integer[] idRoles = new Integer[roles.size()];
			for(int i = 0; i < roles.size(); i++){
				idRoles[i] = roles.get(i).getId();
			}
			
			pDTO.setIdRoles(idRoles);
			return pDTO;
		}
		return null;
	}

	public MessageDTO createProfile(ProfileDTO profileDTO) {
		if(!UtilValidator.validateNotEmptyField(profileDTO.getName())){
			return new MessageDTO(getText("err_profile_nome_required"), MessageType.ERROR);
		}
		if(profileDTO.getIdRoles() == null || profileDTO.getIdRoles().length == 0){
			return new MessageDTO(getText("err_role_required"), MessageType.ERROR);
		}
		try{
			Profile profile = new Profile();
			profile.setName(profileDTO.getName());
			List<Role> roles = new ArrayList<Role>();
			for(Integer i : profileDTO.getIdRoles()){
				Role role = roleDAO.getRoleById(i);
				if(role != null){
					roles.add(role);					
				} else {
					throw new RuntimeException("This rol does not exist");
				}
			}
			profile.setRoles(new HashSet<Role>(roles));
			
			profileDAO.saveProfile(profile);
			if(profile.getId() != null){
				return new MessageDTO(getText("msg_profile_saved"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_profile_not_saved"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_profile_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO updateProfile(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public MessageDTO deleteProfile(ProfileDTO profileDTO) {
		try{
			Profile profile = profileDAO.getProfileById(profileDTO.getId());
			if(profile != null){
				profileDAO.deleteLocal(profile);
				return new MessageDTO(getText("msg_profile_deleted"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_profile_not_deleted"), MessageType.ERROR);		
		}
		catch(Exception e){
			return new MessageDTO(getText("err_profile_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<ProfileDTO> getProfileList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<Profile> list = profileDAO.profileList();
		int total = list == null ? 0 : list.size();
		List<ProfileDTO> listDTO = new ArrayList<ProfileDTO>();
		for(Profile p : list){
			ProfileDTO pDTO = new ProfileDTO(p.getId(), p.getName());
			listDTO.add(pDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}

	public List<RoleDTO> roleList() {
		List<Role> roles = roleDAO.getRoles();
		List<RoleDTO> listDTO = new ArrayList<RoleDTO>();
		for(Role r : roles){
			RoleDTO rDTO = new RoleDTO(r.getId(), r.getName());
			listDTO.add(rDTO);
		}
		return listDTO;
	}
}