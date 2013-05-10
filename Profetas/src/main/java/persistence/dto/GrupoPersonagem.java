package persistence.dto;

import org.jdto.annotation.DTOCascade;

import datatype.SimpleDate;

public class GrupoPersonagem implements DTO {

	private Long id;

	private SimpleDate anoIngresso;

	public GrupoPersonagem() {
	}

	public GrupoPersonagem(Long id, SimpleDate anoIngresso,
			GrupoMovimento grupoMovimento) {
		this.id = id;
		this.anoIngresso = anoIngresso;
		this.grupoMovimento = grupoMovimento;
	}

	@DTOCascade
	private GrupoMovimento grupoMovimento;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SimpleDate getAnoIngresso() {
		return anoIngresso;
	}

	public void setAnoIngresso(SimpleDate anoIngresso) {
		this.anoIngresso = anoIngresso;
	}

	public GrupoMovimento getGrupoMovimento() {
		return grupoMovimento;
	}

	public void setGrupoMovimento(GrupoMovimento grupoMovimento) {
		this.grupoMovimento = grupoMovimento;
	}

}
