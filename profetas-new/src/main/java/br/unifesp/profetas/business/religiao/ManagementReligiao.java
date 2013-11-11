package br.unifesp.profetas.business.religiao;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;

public interface ManagementReligiao {
	
	public ReligiaoCrencasDTO getReligiaoCrencasById(Long id);
	
	public MessageDTO createReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO);
	
	public MessageDTO updateReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO);
	
	public MessageDTO deleteReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO);
	
	public WrapperGrid<ReligiaoCrencasDTO> getReligiaoCrencasList(String orderBy, 
			OrderType orderType, int page, int numRows);
}