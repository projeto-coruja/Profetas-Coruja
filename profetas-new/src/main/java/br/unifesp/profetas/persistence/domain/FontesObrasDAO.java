package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.FontesObras;

public interface FontesObrasDAO {
	
	public FontesObras getFontesObrasById(Long id);
	
	public List<FontesObras> listFontesObras();
	
	public List<FontesObras> listFontesObrasWithLimit(Integer page, 
	        Integer numRows, String order, String field);
	
	public Long getTotalOfFontesObras();
	
	public List<FontesObras> searchFontesObras(String prefix);
	
	public void saveFontesObras(FontesObras fontesObras);
	
	public void updateFontesObras(FontesObras fontesObras);
	
	public void deleteFontesObras(FontesObras fontesObras);
}