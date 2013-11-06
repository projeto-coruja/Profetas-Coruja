package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Personagem;

public interface PersonagemDAO {

	public Personagem getPersonagemById(Long id);
	
	public List<Personagem> listPersonagem();//TODO:
	
	public void savePersonagem(Personagem personagem);
	
	public void updatePersonagem(Personagem personagem);
	
	public void deletePersonagem(Personagem personagem);
}