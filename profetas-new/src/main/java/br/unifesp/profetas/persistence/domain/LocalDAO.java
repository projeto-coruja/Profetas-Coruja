package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Local;

public interface LocalDAO {

	public Local getLocalById(Long id);
	
	public List<Local> listLocal();//TODO:
	
	public List<Local> searchLocal(String prefix);
	
	public void saveLocal(Local local);
	
	public void updateLocal(Local local);
	
	public void deleteLocal(Local local);
}