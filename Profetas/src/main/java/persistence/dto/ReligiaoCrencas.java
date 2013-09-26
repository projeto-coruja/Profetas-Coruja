package persistence.dto;

import datatype.SimpleDate;

public class ReligiaoCrencas implements DTO {

	private Long id;

	private String nome;

	private SimpleDate anoInicio;

	private SimpleDate anoFim;

	private String descricao;

	public ReligiaoCrencas() {} // JDTO

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
