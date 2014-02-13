package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Encontro;

public interface EncontroDAO {
	
	public Encontro getEncontroById(Long id);
	
	public List<Encontro> listEncontro();
	
	public List<Encontro> listEncontroWithLimit(Integer page, 
	        Integer numRows, String order, String field);
	
	public Long getTotalOfEncontros();
	
	public void saveEncontro(Encontro encontro);
	
	public void updateEncontro(Encontro encontro);
	
	public void deleteEncontro(Encontro encontro);
}