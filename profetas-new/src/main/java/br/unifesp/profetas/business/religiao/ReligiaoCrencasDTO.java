package br.unifesp.profetas.business.religiao;

import br.unifesp.profetas.business.common.CommonDTO;

public class ReligiaoCrencasDTO extends CommonDTO {
	
	private Long id;
	private String nome;
	private String anoInicio;
	private String anoFim;
	private String descricao;
	
	public ReligiaoCrencasDTO(){}

	public ReligiaoCrencasDTO(Long id, String nome, String anoInicio,
			String anoFim, String descricao) {
		this.id = id;
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
	public String getAnoInicio() {
		return anoInicio;
	}
	public void setAnoInicio(String anoInicio) {
		this.anoInicio = anoInicio;
	}
	public String getAnoFim() {
		return anoFim;
	}
	public void setAnoFim(String anoFim) {
		this.anoFim = anoFim;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}