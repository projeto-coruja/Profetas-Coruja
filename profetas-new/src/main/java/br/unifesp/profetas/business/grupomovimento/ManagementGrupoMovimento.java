package br.unifesp.profetas.business.grupomovimento;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;

public interface ManagementGrupoMovimento {
	
	public GrupoMovimentoDTO getGrupoMovimentoById(Long id);
	
	public MessageDTO createGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO);
	
	public MessageDTO updateGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO);
	
	public MessageDTO deleteGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO);
	
	public WrapperGrid<GrupoMovimentoDTO> getGrupoMovimentoList(String orderBy, 
			OrderType orderType, int page, int numRows);
}