package persistence.dto;

import java.util.List;

import org.jdto.annotation.DTOCascade;

import datatype.SimpleDate;

public class FontesObras implements DTO {

	private Long id;

	private String titulo;

	private String comentarios;

	private String refverenciasirCulacaoObra;

	private String URL;

	private String copiasManuscritas;

	private String traducoes;

	private SimpleDate dataImpressao;

	private String editor;

	@DTOCascade
	private GrupoMovimento grupoMovimento;

	@DTOCascade
	private Local localImpressao;

	@DTOCascade
	private Classificacao classificao;

	@DTOCascade
	private List<PalavraChave> palavraChave;

	@DTOCascade
	private List<FontesObras> obrasCitadas;

	@DTOCascade
	private List<Personagem> leitores;

	@DTOCascade
	private List<Personagem> personagens;

	@DTOCascade
	private List<Personagem> autoresCitados;

	public FontesObras() {
	}

	public FontesObras(String titulo, String comentarios,
			String refverenciasirCulacaoObra, String uRL,
			String copiasManuscritas, String traducoes,
			SimpleDate dataImpressao, String editor,
			GrupoMovimento grupoMovimento, Local localImpressao,
			Classificacao classificao, List<PalavraChave> palavraChave,
			List<FontesObras> obrasCitadas, List<Personagem> leitores,
			List<Personagem> personagens, List<Personagem> autoresCitados) {
		this.titulo = titulo;
		this.comentarios = comentarios;
		this.refverenciasirCulacaoObra = refverenciasirCulacaoObra;
		URL = uRL;
		this.copiasManuscritas = copiasManuscritas;
		this.traducoes = traducoes;
		this.dataImpressao = dataImpressao;
		this.editor = editor;
		this.grupoMovimento = grupoMovimento;
		this.localImpressao = localImpressao;
		this.classificao = classificao;
		this.palavraChave = palavraChave;
		this.obrasCitadas = obrasCitadas;
		this.leitores = leitores;
		this.personagens = personagens;
		this.autoresCitados = autoresCitados;
	}

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

	public String getRefverenciasirCulacaoObra() {
		return refverenciasirCulacaoObra;
	}

	public void setRefverenciasirCulacaoObra(String refverenciasirCulacaoObra) {
		this.refverenciasirCulacaoObra = refverenciasirCulacaoObra;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
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

	public SimpleDate getDataImpressao() {
		return dataImpressao;
	}

	public void setDataImpressao(SimpleDate dataImpressao) {
		this.dataImpressao = dataImpressao;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public GrupoMovimento getGrupoMovimento() {
		return grupoMovimento;
	}

	public void setGrupoMovimento(GrupoMovimento grupoMovimento) {
		this.grupoMovimento = grupoMovimento;
	}

	public Local getLocalImpressao() {
		return localImpressao;
	}

	public void setLocalImpressao(Local localImpressao) {
		this.localImpressao = localImpressao;
	}

	public Classificacao getClassificao() {
		return classificao;
	}

	public void setClassificao(Classificacao classificao) {
		this.classificao = classificao;
	}

	public List<PalavraChave> getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(List<PalavraChave> palavraChave) {
		this.palavraChave = palavraChave;
	}

	public List<FontesObras> getObrasCitadas() {
		return obrasCitadas;
	}

	public void setObrasCitadas(List<FontesObras> obrasCitadas) {
		this.obrasCitadas = obrasCitadas;
	}

	public List<Personagem> getLeitores() {
		return leitores;
	}

	public void setLeitores(List<Personagem> leitores) {
		this.leitores = leitores;
	}

	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

	public List<Personagem> getAutoresCitados() {
		return autoresCitados;
	}

	public void setAutoresCitados(List<Personagem> autoresCitados) {
		this.autoresCitados = autoresCitados;
	}

}
