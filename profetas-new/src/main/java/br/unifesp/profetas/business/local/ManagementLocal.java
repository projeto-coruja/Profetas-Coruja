package br.unifesp.profetas.business.local;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.model.Local;

public interface ManagementLocal {
	
	public LocalDTO getLocalById(Long id);
	
	public MessageDTO createLocal(LocalDTO localDTO);
	
	public MessageDTO updateLocal(LocalDTO localDTO);
	
	public MessageDTO deleteLocal(LocalDTO localDTO);
	
	public WrapperGrid<Local> getLocalList(String orderBy, 
			OrderType orderType, int page, int numRows);

}