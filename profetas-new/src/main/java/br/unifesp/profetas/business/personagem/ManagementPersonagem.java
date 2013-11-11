package br.unifesp.profetas.business.personagem;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;

public interface ManagementPersonagem {
	
	public PersonagemDTO getPersonagemById(Long id);
	
	public MessageDTO createPersonagem(PersonagemDTO personagemDTO);
	
	public MessageDTO updatePersonagem(PersonagemDTO personagemDTO);
	
	public MessageDTO deletePersonagem(PersonagemDTO personagemDTO);
	
	public WrapperGrid<PersonagemDTO> getPersonagemList(String orderBy, 
			OrderType orderType, int page, int numRows);
}