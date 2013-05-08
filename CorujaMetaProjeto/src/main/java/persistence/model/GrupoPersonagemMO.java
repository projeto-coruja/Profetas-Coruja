package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import datatype.SimpleDate;

@Entity
public class GrupoPersonagemMO extends GrupoMovimentoMO {
	@Id
	@GeneratedValue
	private Long id;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate anoIngresso;

	private GrupoMovimentoMO grupoMovimento;

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

	public GrupoMovimentoMO getGrupoMovimento() {
		return grupoMovimento;
	}

	public void setGrupoMovimento(GrupoMovimentoMO grupoMovimento) {
		this.grupoMovimento = grupoMovimento;
	}

}
