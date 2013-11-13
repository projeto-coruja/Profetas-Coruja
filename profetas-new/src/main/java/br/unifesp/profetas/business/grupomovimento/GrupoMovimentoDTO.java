package br.unifesp.profetas.business.grupomovimento;

import br.unifesp.profetas.business.common.CommonDTO;

public class GrupoMovimentoDTO extends CommonDTO {
	
	private Long id;
	private String nome;
	private String anoInicio;
	private String anoFim;
	private String descricao;
	
	private Long[] idLocais;
	private String strLocais;
	
	public GrupoMovimentoDTO() {}
	
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
	public Long[] getIdLocais() {
		return idLocais;
	}
	public void setIdLocais(Long[] idLocais) {
		this.idLocais = idLocais;
	}
	public String getStrLocais() {
		return strLocais;
	}
	public void setStrLocais(String strLocais) {
		this.strLocais = strLocais;
	}	
}