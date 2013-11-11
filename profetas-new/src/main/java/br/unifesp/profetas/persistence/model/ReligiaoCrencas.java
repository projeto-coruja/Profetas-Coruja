package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "religiao_crencas")
public class ReligiaoCrencas implements Serializable {
	
	@Id
    @Column(name="id_religiao", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recr_seq_name")
	@SequenceGenerator(name = "recr_seq_name", sequenceName = "recr_seq", allocationSize = 1)
	private Long id;
	
	@Column(name="r_name", nullable = false, length = 100)
	private String nome;
	
	@Column(name="r_ano_inicio", nullable = true)
	private Integer anoInicio;
	
	@Column(name="r_ano_fim", nullable = true)
	private Integer anoFim;
	
	@Column(name="r_descricao", columnDefinition="TEXT", nullable = true)
	private String descricao;
	
	@Type(type="yes_no")
	@Column(name = "active")
	private Boolean active;
	
	//
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "religioes")
	private Set<Personagem> rcPersonagem = new HashSet<Personagem>(0);

	public ReligiaoCrencas() {}

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
	public Set<Personagem> getRcPersonagem() {
		return rcPersonagem;
	}
	public void setRcPersonagem(Set<Personagem> rcPersonagem) {
		this.rcPersonagem = rcPersonagem;
	}

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}