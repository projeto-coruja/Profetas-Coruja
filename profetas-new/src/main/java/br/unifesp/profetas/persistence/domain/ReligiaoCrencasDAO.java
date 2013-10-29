package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.ReligiaoCrencas;

public interface ReligiaoCrencasDAO {
	
	public ReligiaoCrencas getReligiaoCrencasById(Long id);
	
	public List<ReligiaoCrencas> listReligiaoCrencas();//TODO:
	
	public void saveReligiaoCrencas(ReligiaoCrencas religiaoCrencas);
	
	public void updateReligiaoCrencas(ReligiaoCrencas religiaoCrencas);
	
	public void deleteReligiaoCrencas(ReligiaoCrencas religiaoCrencas);

}