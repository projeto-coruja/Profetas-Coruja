package br.unifesp.profetas.business.fontesobras;

import br.unifesp.profetas.business.common.CommonDTO;

public class FontesObrasDTO extends CommonDTO {

	private Long id;
	private String localizacao;
	private String autor;
	private String titulo;
	private String comentarios;
	private String referenciaCompleta;
	private String referenciasCirculacaoObra;
	private String url;
	private String copiasManuscritas;
	private String traducoes;
	private String editor;
	private String dataImpressao;
	private Long idLocalImpressao;
	private Integer idClassificacao;
	private Long idGruMovimento;
	private String[] idLeitores;
	private String strLeitores;
	private String[] idPersonagens;
	private String strPersonagens;
	private String[] idAutCitados;
	private String strAutCitados;
	private String[] idObrCitadas;
	private String strObrCitadas;
	private String[] palavrasChave;
	private String strPalChave;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
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
	public String getReferenciaCompleta() {
		return referenciaCompleta;
	}
	public void setReferenciaCompleta(String referenciaCompleta) {
		this.referenciaCompleta = referenciaCompleta;
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
	public Long getIdGruMovimento() {
		return idGruMovimento;
	}
	public void setIdGruMovimento(Long idGruMovimento) {
		this.idGruMovimento = idGruMovimento;
	}
	public String[] getIdLeitores() {
		return idLeitores;
	}
	public void setIdLeitores(String[] idLeitores) {
		this.idLeitores = idLeitores;
	}
	public String getStrLeitores() {
		return strLeitores;
	}
	public void setStrLeitores(String strLeitores) {
		this.strLeitores = strLeitores;
	}
	public String[] getIdPersonagens() {
		return idPersonagens;
	}
	public void setIdPersonagens(String[] idPersonagens) {
		this.idPersonagens = idPersonagens;
	}
	public String getStrPersonagens() {
		return strPersonagens;
	}
	public void setStrPersonagens(String strPersonagens) {
		this.strPersonagens = strPersonagens;
	}
	public String[] getIdAutCitados() {
		return idAutCitados;
	}
	public void setIdAutCitados(String[] idAutCitados) {
		this.idAutCitados = idAutCitados;
	}
	public String getStrAutCitados() {
		return strAutCitados;
	}
	public void setStrAutCitados(String strAutCitados) {
		this.strAutCitados = strAutCitados;
	}
	public String[] getIdObrCitadas() {
		return idObrCitadas;
	}
	public void setIdObrCitadas(String[] idObrCitadas) {
		this.idObrCitadas = idObrCitadas;
	}
	public String getStrObrCitadas() {
		return strObrCitadas;
	}
	public void setStrObrCitadas(String strObrCitadas) {
		this.strObrCitadas = strObrCitadas;
	}	
	public String[] getPalavrasChave() {
		return palavrasChave;
	}
	public void setPalavrasChave(String[] palavrasChave) {
		this.palavrasChave = palavrasChave;
	}
	public String getStrPalChave() {
		return strPalChave;
	}
	public void setStrPalChave(String strPalChave) {
		this.strPalChave = strPalChave;
	}
}