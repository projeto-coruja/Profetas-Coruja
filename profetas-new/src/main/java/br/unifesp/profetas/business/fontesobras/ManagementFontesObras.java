package br.unifesp.profetas.business.fontesobras;

import java.util.List;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.persistence.model.Classificacao;

public interface ManagementFontesObras {
	
	public FontesObrasDTO getFontesObrasById(Long id);
	
	public MessageDTO createFontesObras(FontesObrasDTO fontesObrasDTO);
	
	public MessageDTO updateFontesObras(FontesObrasDTO fontesObrasDTO);
	
	public MessageDTO deleteFontesObras(FontesObrasDTO fontesObrasDTO);
	
	public WrapperGrid<FontesObrasDTO> getFontesObrasList(String orderBy, 
			OrderType orderType, int page, int numRows);
	
	public List<LocalDTO> getLocals();

	public List<Classificacao> getClassificacoes();
}