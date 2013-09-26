package persistence.dto;

import java.util.List;

import org.jdto.annotation.DTOCascade;

import datatype.SimpleDate;

public class GrupoMovimento implements DTO {

	private Long id;

	private String nome;

	private SimpleDate anoInicio;

	private SimpleDate anoFim;

	private String descricao;

	@DTOCascade
	private List<Local> local;

	public GrupoMovimento() {} // JDTO

	public GrupoMovimento(String nome, SimpleDate anoInicio, SimpleDate anoFim, String descricao, List<Local> local) {
		this.nome = nome;
		this.anoInicio = anoInicio;
		this.anoFim = anoFim;
		this.descricao = descricao;
		this.local = local;
	}

	@Override
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
