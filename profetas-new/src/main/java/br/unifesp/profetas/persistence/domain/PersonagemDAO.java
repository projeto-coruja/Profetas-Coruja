package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Personagem;

public interface PersonagemDAO {

	public Personagem getPersonagemById(Long id);
	
	public List<Personagem> listPersonagem();
	
	public List<Personagem> listPersonagemWithLimit(Integer page, 
	        Integer numRows, String order, String field);
	
	public Long getTotalOfPersonagens();
	
	public List<Personagem> searchPersonagem(String prefix);
	
	public void savePersonagem(Personagem personagem);
	
	public void updatePersonagem(Personagem personagem);
	
	public void deletePersonagem(Personagem personagem);
}