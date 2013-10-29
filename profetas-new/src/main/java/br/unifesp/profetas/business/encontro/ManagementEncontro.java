package br.unifesp.profetas.business.encontro;

import java.util.List;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.Local;

public interface ManagementEncontro {

	public EncontroDTO getEncontroById(Long id);
	
	public List<Local> getAllLocal();
	
	public MessageDTO createEncontro(EncontroDTO encontroDTO);
	
	public MessageDTO updateEncontro(EncontroDTO encontroDTO);
	
	public MessageDTO deleteEncontro(EncontroDTO encontroDTO);
	
	public WrapperGrid<Encontro> getEncontroList(String orderBy, 
			OrderType orderType, int page, int numRows);
}