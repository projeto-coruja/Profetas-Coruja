package br.unifesp.profetas.business.classificacao;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.model.Classificacao;

public interface ManagementClassificacao {

	public ClassificacaoDTO getClassificacaoById(Long id);
	
	public MessageDTO createClassificacao(ClassificacaoDTO classificacaoDTO);
	
	public MessageDTO updateClassificacao(ClassificacaoDTO classificacaoDTO);
	
	public MessageDTO deleteClassificacao(ClassificacaoDTO classificacaoDTO);
	
	public WrapperGrid<Classificacao> getClassificacaoList(String orderBy, 
			OrderType orderType, int page, int numRows);
}