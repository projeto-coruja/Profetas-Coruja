package br.unifesp.profetas.business.search;

import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.personagem.PersonagemDTO;

public class ManagementSearchImpl implements ManagementSearch {

	public WrapperGrid<PersonagemDTO> searchByPersonagens(String orderBy,
			OrderType orderType, int page, int numRows, String text) {
		return null;
	}

	public WrapperGrid<FontesObrasDTO> searchByFontesObras(String orderBy,
			OrderType orderType, int page, int numRows, String text) {
		return null;
	}
}