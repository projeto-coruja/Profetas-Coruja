package br.unifesp.profetas.business.fontesobras;

import br.unifesp.profetas.business.common.CommonDTO;

public class FontesObrasDTO extends CommonDTO {

	private Long id;
	private String titulo;
	private String comentarios;
	private String referenciasCirculacaoObra;
	private String url;
	private String copiasManuscritas;
	private String traducoes;
	private String editor;
	private String dataImpressao;
	private Long idLocalImpressao;
	private Integer idClassificacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getReferenciasCirculacaoObra() {
		return referenciasCirculacaoObra;
	}
	public void setReferenciasCirculacaoObra(String referenciasCirculacaoObra) {
		this.referenciasCirculacaoObra = referenciasCirculacaoObra;
	}	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCopiasManuscritas() {
		return copiasManuscritas;
	}
	public void setCopiasManuscritas(String copiasManuscritas) {
		this.copiasManuscritas = copiasManuscritas;
	}
	public String getTraducoes() {
		return traducoes;
	}
	public void setTraducoes(String traducoes) {
		this.traducoes = traducoes;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getDataImpressao() {
		return dataImpressao;
	}
	public void setDataImpressao(String dataImpressao) {
		this.dataImpressao = dataImpressao;
	}
	public Long getIdLocalImpressao() {
		return idLocalImpressao;
	}
	public void setIdLocalImpressao(Long idLocalImpressao) {
		this.idLocalImpressao = idLocalImpressao;
	}
	public Integer getIdClassificacao() {
		return idClassificacao;
	}
	public void setIdClassificacao(Integer idClassificacao) {
		this.idClassificacao = idClassificacao;
	}
}