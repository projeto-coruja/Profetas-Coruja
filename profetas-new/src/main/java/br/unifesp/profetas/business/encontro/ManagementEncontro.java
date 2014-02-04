package br.unifesp.profetas.business.encontro;

import java.util.Set;

import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.Personagem;

public interface ManagementEncontro {

	public Set<Encontro> getEncontrosByPersonagem(Personagem personagem);
}