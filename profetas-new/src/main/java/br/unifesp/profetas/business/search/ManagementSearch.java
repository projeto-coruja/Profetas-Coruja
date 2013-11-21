package br.unifesp.profetas.business.search;

import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.personagem.PersonagemDTO;

public interface ManagementSearch {
	
	public WrapperGrid<PersonagemDTO> searchByPersonagens(OrderType orderType, int page, int numRows, String words);
	
	public WrapperGrid<FontesObrasDTO> searchByFontesObras(OrderType orderType, int page, int numRows, String words);
}