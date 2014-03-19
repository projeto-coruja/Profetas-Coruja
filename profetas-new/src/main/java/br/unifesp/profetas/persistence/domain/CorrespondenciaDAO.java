package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Correspondencia;

public interface CorrespondenciaDAO {
	
	public Correspondencia getCorrespondenciaById(Long id);
	
	public List<Correspondencia> listCorrespondencia();
	
	public List<Correspondencia> listCorrespondenciaWithLimit(Integer page, 
	        Integer numRows, String order, String field);
	
	public Long getTotalOfCorrespondencias();
	
	public List<Correspondencia> searchCorrespondencia(String prefix);
	
	public void saveCorrespondencia(Correspondencia correspondencia);
	
	public void updateCorrespondencia(Correspondencia correspondencia);
	
	public void deleteCorrespondencia(Correspondencia correspondencia);
}