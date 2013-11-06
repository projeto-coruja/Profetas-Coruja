package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.PalavraChave;

public interface PalavraChaveDAO {
	
	public PalavraChave getPalavraChaveById(Long id);
	
	public List<PalavraChave> listPalavraChave();//TODO:
	
	public void savePalavraChave(PalavraChave palavraChave);
	
	public void updatePalavraChave(PalavraChave palavraChave);
	
	public void deletePalavraChave(PalavraChave palavraChave);
}