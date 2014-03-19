package br.unifesp.profetas.business.correspondencia;

import java.util.List;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;

public interface ManagementCorrespondencia {
	
	public CorrespondenciaDTO getCorrespondenciaById(Long id);
	
	public MessageDTO createCorrespondencia(CorrespondenciaDTO correspondenciaDTO);
	
	public MessageDTO updateCorrespondencia(CorrespondenciaDTO correspondenciaDTO);
	
	public MessageDTO deleteCorrespondencia(CorrespondenciaDTO correspondenciaDTO);
	
	public WrapperGrid<CorrespondenciaDTO> getCorrespondenciaList(String orderBy, 
			OrderType orderType, int page, int numRows);

	public List searchCorrespondencia(String word);
}