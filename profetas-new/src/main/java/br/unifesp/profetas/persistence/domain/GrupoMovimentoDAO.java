package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.GrupoMovimento;

public interface GrupoMovimentoDAO {
	
	public GrupoMovimento getGrupoMovimentoById(Long id);
	
	public List<GrupoMovimento> listGrupoMovimento();//TODO:
	
	public void saveGrupoMovimento(GrupoMovimento grupoMovimento);
	
	public void updateGrupoMovimento(GrupoMovimento grupoMovimento);
	
	public void deleteGrupoMovimento(GrupoMovimento grupoMovimento);
}