package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import datatype.SimpleDate;

@Entity
public class GrupoPersonagem implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@Type(type="persistence.util.SimpleDateHibernateType")
	private SimpleDate anoIngresso;

	@ManyToOne
	private GrupoMovimento grupoMovimento;

	public GrupoPersonagem() {} // Hibernate

	public GrupoPersonagem(SimpleDate anoIngresso, GrupoMovimento grupoMovimento) {
		this.anoIngresso = anoIngresso;
		this.grupoMovimento = grupoMovimento;
	}
	
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
