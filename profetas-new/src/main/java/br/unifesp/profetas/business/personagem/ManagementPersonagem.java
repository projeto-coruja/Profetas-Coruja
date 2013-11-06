package br.unifesp.profetas.business.personagem;

import java.util.List;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.persistence.model.Profile;

public interface ManagementPersonagem {
	
	public PersonagemDTO getPersonagemById(Long id);
	
	public MessageDTO createPersonagem(PersonagemDTO personagemDTO);
	
	public MessageDTO updatePersonagem(PersonagemDTO personagemDTO);
	
	public MessageDTO deletePersonagem(PersonagemDTO personagemDTO);
	
	public WrapperGrid<PersonagemDTO> getPersonagemList(String orderBy, 
			OrderType orderType, int page, int numRows);
	
	public List<LocalDTO> getLocals();
	public List<ProfileDTO> getProfiles();
}