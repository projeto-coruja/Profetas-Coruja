package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_movimento")
public class GrupoMovimento implements Serializable {
	
	@Id
	@Column(name="id_gru_movimento", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grmo_seq_name")
	@SequenceGenerator(name = "grmo_seq_name", sequenceName = "grmo_seq", allocationSize = 1)
	private Long id;
	
	@Column(name="gm_name", nullable = false, length = 100)
	private String nome;
	
	@Column(name="gm_ano_inicio", nullable = true)
	private Integer anoInicio;
	
	@Column(name="gm_ano_fim", nullable = true)
	private Integer anoFim;
	
	@Column(name="gm_descricao", columnDefinition="TEXT", nullable = true)
	private String descricao;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "gmmov_local", joinColumns = { 
			@JoinColumn(name = "id_gru_movimento", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_local", 
			nullable = false, updatable = false) })
	private Set<Local> locais = new HashSet<Local>(0);

	public GrupoMovimento() {	}

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
	public Integer getAnoInicio() {
		return anoInicio;
	}
	public void setAnoInicio(Integer anoInicio) {
		this.anoInicio = anoInicio;
	}
	public Integer getAnoFim() {
		return anoFim;
	}
	public void setAnoFim(Integer anoFim) {
		this.anoFim = anoFim;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Set<Local> getLocais() {
		return locais;
	}
	public void setLocais(Set<Local> locais) {
		this.locais = locais;
	}
}