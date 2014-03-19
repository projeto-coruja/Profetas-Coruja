package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Local;

public interface LocalDAO {

	public Local getLocalById(Long id);
	
	public Local getLocalByCountryAndNome(String country, String nome);
	
	public List<Local> listLocal();
	
	public List<Local> listLocalWithLimit(Integer page, 
	        Integer numRows, String order, String field);
	
	public Long getTotalOfLocais();
	
	public List<Local> searchLocal(String prefix);
	
	public void saveLocal(Local local);
	
	public void updateLocal(Local local);
	
	public void deleteLocal(Local local);
}