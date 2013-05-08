package persistence.model;

import datatype.SimpleDate;

public class GrupoPersonagemMO extends GrupoMovimentoMO {

	private int id;

	private SimpleDate anoIngresso;

	private GrupoMovimentoMO grupoMovimento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SimpleDate getAnoIngresso() {
		return anoIngresso;
	}

	public void setAnoIngresso(SimpleDate anoIngresso) {
		this.anoIngresso = anoIngresso;
	}

	public GrupoMovimentoMO getGrupoMovimento() {
		return grupoMovimento;
	}

	public void setGrupoMovimento(GrupoMovimentoMO grupoMovimento) {
		this.grupoMovimento = grupoMovimento;
	}

}
