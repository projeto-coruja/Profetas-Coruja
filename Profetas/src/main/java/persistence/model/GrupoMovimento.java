package persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import datatype.SimpleDate;

@Entity
public class GrupoMovimento implements IdentifiedEntity {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String nome;

	@Type(type="persistence.util.SimpleDateHibernateType")
	private SimpleDate anoInicio;

	@Type(type="persistence.util.SimpleDateHibernateType")
	private SimpleDate anoFim;

	private String descricao;

	@ManyToMany
	private List<Local> local;

	public GrupoMovimento() {} // Hibernate

	public GrupoMovimento(String nome, SimpleDate anoInicio, SimpleDate anoFim, String descricao, List<Local> local) {
		this.nome = nome;
		this.anoInicio = anoInicio;
		this.anoFim = anoFim;
		this.descricao = descricao;
		this.local = local;
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

	public List<Local> getLocal() {
		return local;
	}

	public void setLocal(List<Local> local) {
		this.local = local;
	}

}
