package br.unifesp.profetas.business.personagem;

import br.unifesp.profetas.business.common.CommonDTO;

public class PersonagemDTO extends CommonDTO {
	
	private Long id;
	private String sobrenome;
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
    
    private String refBibliografica;
    private Long[] idReligioes;
	private String strReligioes;
	private Long[] idObras;
	private String strObras;
	private Long[] idLocaisPers;
	private String strLocaisPers;
	private Long[] idCorrespondencias;
	private String strCorrespondencias;
	
	private Long[] idEncontros;
	private String[] nomeEncontros;
	private String[] dataEncontros;
	private Long[] idPersonagemEncontros;
	private Long[] idLocalEncontros;
	private Long numEncontros;
	
	private String divId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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
	public String getRefBibliografica() {
		return refBibliografica;
	}
	public void setRefBibliografica(String refBibliografica) {
		this.refBibliografica = refBibliografica;
	}
	public Long[] getIdReligioes() {
		return idReligioes;
	}
	public void setIdReligioes(Long[] idReligioes) {
		this.idReligioes = idReligioes;
	}
	public String getStrReligioes() {
		return strReligioes;
	}
	public void setStrReligioes(String strReligioes) {
		this.strReligioes = strReligioes;
	}
	public Long[] getIdEncontros() {
		return idEncontros;
	}
	public void setIdEncontros(Long[] idEncontros) {
		this.idEncontros = idEncontros;
	}
	public String[] getNomeEncontros() {
		return nomeEncontros;
	}
	public void setNomeEncontros(String[] nomeEncontros) {
		this.nomeEncontros = nomeEncontros;
	}
	public String[] getDataEncontros() {
		return dataEncontros;
	}
	public void setDataEncontros(String[] dataEncontros) {
		this.dataEncontros = dataEncontros;
	}
	public Long[] getIdPersonagemEncontros() {
		return idPersonagemEncontros;
	}
	public void setIdPersonagemEncontros(Long[] idPersonagemEncontros) {
		this.idPersonagemEncontros = idPersonagemEncontros;
	}
	public Long[] getIdLocalEncontros() {
		return idLocalEncontros;
	}
	public void setIdLocalEncontros(Long[] idLocalEncontros) {
		this.idLocalEncontros = idLocalEncontros;
	}
	public Long getNumEncontros() {
		return numEncontros;
	}
	public void setNumEncontros(Long numEncontros) {
		this.numEncontros = numEncontros;
	}
	public Long[] getIdObras() {
		return idObras;
	}
	public void setIdObras(Long[] idObras) {
		this.idObras = idObras;
	}
	public String getStrObras() {
		return strObras;
	}
	public void setStrObras(String strObras) {
		this.strObras = strObras;
	}
	public Long[] getIdLocaisPers() {
		return idLocaisPers;
	}
	public void setIdLocaisPers(Long[] idLocaisPers) {
		this.idLocaisPers = idLocaisPers;
	}
	public String getStrLocaisPers() {
		return strLocaisPers;
	}
	public void setStrLocaisPers(String strLocaisPers) {
		this.strLocaisPers = strLocaisPers;
	}
	public Long[] getIdCorrespondencias() {
		return idCorrespondencias;
	}
	public void setIdCorrespondencias(Long[] idCorrespondencias) {
		this.idCorrespondencias = idCorrespondencias;
	}
	public String getStrCorrespondencias() {
		return strCorrespondencias;
	}
	public void setStrCorrespondencias(String strCorrespondencias) {
		this.strCorrespondencias = strCorrespondencias;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}	
}