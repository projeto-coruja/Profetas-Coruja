package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Classificacao;

public interface ClassificacaoDAO {
	
	public Classificacao getClassificacaoById(Long id);
	
	public List<Classificacao> listClassificacao();//TODO:
	
	public void saveClassificacao(Classificacao classificacao);
	
	public void updateClassificacao(Classificacao classificacao);
	
	public void deleteClassificacao(Classificacao classificacao);
}