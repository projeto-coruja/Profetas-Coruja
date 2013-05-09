package persistence.dto;

import java.util.List;

import org.jdto.annotation.DTOCascade;

import datatype.SimpleDate;

public class Personagem implements DTO {

	private Long id;

	private String nome;

	private String apelido;

	@DTOCascade
	private Local localNascimento;

	private SimpleDate dataNascimento;

	@DTOCascade
	private Local localMorte;

	private SimpleDate dataMorte;

	private String biografia;

	private String ocupacao;

	private String formacao;

	@DTOCascade
	private FontesObras referencia_bibliografica;

	@DTOCascade
	private List<ReligiaoCrencas> religião;

	@DTOCascade
	private List<GrupoPersonagem> grupo;

	@DTOCascade
	private List<LocaisPersonagens> locaisVisitados;

	@DTOCascade
	private List<Encontro> encontro;

	@DTOCascade
	private List<FontesObras> Obras;

	public Personagem() {
	}

	public Personagem(Long id, String nome, String apelido,
			Local localNascimento, SimpleDate dataNascimento, Local localMorte,
			SimpleDate dataMorte, String biografia, String ocupacao,
			String formacao, FontesObras referencia_bibliografica,
			List<ReligiaoCrencas> religião, List<GrupoPersonagem> grupo,
			List<LocaisPersonagens> locaisVisitados, List<Encontro> encontro,
			List<FontesObras> obras) {
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.localNascimento = localNascimento;
		this.dataNascimento = dataNascimento;
		this.localMorte = localMorte;
		this.dataMorte = dataMorte;
		this.biografia = biografia;
		this.ocupacao = ocupacao;
		this.formacao = formacao;
		this.referencia_bibliografica = referencia_bibliografica;
		this.religião = religião;
		this.grupo = grupo;
		this.locaisVisitados = locaisVisitados;
		this.encontro = encontro;
		Obras = obras;
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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Local getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(Local localNascimento) {
		this.localNascimento = localNascimento;
	}

	public SimpleDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(SimpleDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Local getLocalMorte() {
		return localMorte;
	}

	public void setLocalMorte(Local localMorte) {
		this.localMorte = localMorte;
	}

	public SimpleDate getDataMorte() {
		return dataMorte;
	}

	public void setDataMorte(SimpleDate dataMorte) {
		this.dataMorte = dataMorte;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public FontesObras getReferencia_bibliografica() {
		return referencia_bibliografica;
	}

	public void setReferencia_bibliografica(FontesObras referencia_bibliografica) {
		this.referencia_bibliografica = referencia_bibliografica;
	}

	public List<ReligiaoCrencas> getReligião() {
		return religião;
	}

	public void setReligião(List<ReligiaoCrencas> religião) {
		this.religião = religião;
	}

	public List<GrupoPersonagem> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<GrupoPersonagem> grupo) {
		this.grupo = grupo;
	}

	public List<LocaisPersonagens> getLocaisVisitados() {
		return locaisVisitados;
	}

	public void setLocaisVisitados(List<LocaisPersonagens> locaisVisitados) {
		this.locaisVisitados = locaisVisitados;
	}

	public List<Encontro> getEncontro() {
		return encontro;
	}

	public void setEncontro(List<Encontro> encontro) {
		this.encontro = encontro;
	}

	public List<FontesObras> getObras() {
		return Obras;
	}

	public void setObras(List<FontesObras> obras) {
		Obras = obras;
	}

}
