package br.unifesp.profetas.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
}