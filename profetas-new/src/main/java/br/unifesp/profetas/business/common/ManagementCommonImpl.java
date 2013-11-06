package br.unifesp.profetas.business.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.persistence.domain.ProfileDAO;
import br.unifesp.profetas.persistence.model.Profile;

@Service("mCommon")
public class ManagementCommonImpl implements ManagementCommon {

	@Autowired private ProfileDAO profileDAO;

	public List<ProfileDTO> profileList() {
		List<Profile> profiles = profileDAO.profileList();
		List<ProfileDTO> listDTO = new ArrayList<ProfileDTO>();
		for(Profile p : profiles){
			ProfileDTO pDTO = new ProfileDTO(p.getId(), p.getName());
			listDTO.add(pDTO);
		}
		return listDTO;
	}
}