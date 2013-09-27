package persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import datatype.SimpleDate;

@Entity
public class ReligiaoCrencas implements IdentifiedEntity {

	@Id
	private Long id;
	
	@NaturalId
	@NotEmpty
	private String nome;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate anoInicio;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate anoFim;

	private String descricao;

	public ReligiaoCrencas() {} // Hibernate

	public ReligiaoCrencas(String nome, SimpleDate anoInicio, SimpleDate anoFim, String descricao) {
		this.nome = nome;
		this.anoInicio = anoInicio;
		this.anoFim = anoFim;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SimpleDate getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(SimpleDate anoInicio) {
		this.anoInicio = anoInicio;
	}

	public SimpleDate getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(SimpleDate anoFim) {
		this.anoFim = anoFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
