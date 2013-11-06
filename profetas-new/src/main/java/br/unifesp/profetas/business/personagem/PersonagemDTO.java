package br.unifesp.profetas.business.personagem;

import br.unifesp.profetas.business.common.CommonDTO;

public class PersonagemDTO extends CommonDTO {
	
	private Long id;
	private String nome;
	private String apelido;
	private Long idNascimento;
	private String locNascimento;
	private String dataNascimento;
	private Long idMorte;
	private String locMorte;
	private String dataMorte;
	private String biografia;
    private String ocupacao;
    private String formacao;
    
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
	public Long getIdNascimento() {
		return idNascimento;
	}
	public void setIdNascimento(Long idNascimento) {
		this.idNascimento = idNascimento;
	}
	public String getLocNascimento() {
		return locNascimento;
	}
	public void setLocNascimento(String locNascimento) {
		this.locNascimento = locNascimento;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getIdMorte() {
		return idMorte;
	}
	public void setIdMorte(Long idMorte) {
		this.idMorte = idMorte;
	}
	public String getLocMorte() {
		return locMorte;
	}
	public void setLocMorte(String locMorte) {
		this.locMorte = locMorte;
	}
	public String getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(String dataMorte) {
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
}