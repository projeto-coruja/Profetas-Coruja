package br.unifesp.profetas.business.palavrachave;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.model.PalavraChave;

public interface ManagementPalavraChave {

	public PalavraChaveDTO getPalavraChaveById(Long id);
	
	public MessageDTO createPalavraChave(PalavraChaveDTO palavraChaveDTO);
	
	public MessageDTO updatePalavraChave(PalavraChaveDTO palavraChaveDTO);
	
	public MessageDTO deletePalavraChave(PalavraChaveDTO palavraChaveDTO);
	
	public WrapperGrid<PalavraChave> getPalavraChaveList(String orderBy, 
			OrderType orderType, int page, int numRows);
}