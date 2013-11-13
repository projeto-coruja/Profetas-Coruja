package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Correspondencia;

public interface CorrespondenciaDAO {
	
	public Correspondencia getCorrespondenciaById(Long id);
	
	public List<Correspondencia> listCorrespondencia();//TODO:
	
	public void saveCorrespondencia(Correspondencia correspondencia);
	
	public void updateCorrespondencia(Correspondencia correspondencia);
	
	public void deleteCorrespondencia(Correspondencia correspondencia);
}