package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Encontro;

public interface EncontroDAO {
	
	public Encontro getEncontroById(Long id);
	
	public List<Encontro> listEncontro();//TODO:
	
	public void saveEncontro(Encontro encontro);
	
	public void updateEncontro(Encontro encontro);
	
	public void deleteEncontro(Encontro encontro);
}