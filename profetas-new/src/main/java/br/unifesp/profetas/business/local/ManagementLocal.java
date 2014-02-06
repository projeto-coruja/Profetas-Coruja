package br.unifesp.profetas.business.local;

import java.util.List;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;

public interface ManagementLocal {
	
	public LocalDTO getLocalById(Long id);
	
	public MessageDTO createLocal(LocalDTO localDTO);
	
	public MessageDTO updateLocal(LocalDTO localDTO);
	
	public MessageDTO deleteLocal(LocalDTO localDTO);
	
	public WrapperGrid<LocalDTO> getLocalList(String orderBy, 
			OrderType orderType, int page, int numRows);
	
	public List searchLocal(String word);
}