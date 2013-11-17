package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

/*
+ Sobrenome,
+ Nome,
+ Apelido/Conhecido Como,
+ Local Nascimento,
+ Data Nascimento,
+ Local Morte,
+ Data Morte,
+ Religião/Crença,
+? Grupo/Movimento profético,
+ Biografia,
+ Ocupação,
+ Formação,
+ Obras,
+ Locais por onde passou,
+? Encontros,
+ Correspondências,
- Leituras,
+ Referências Bibliográficas
 */
@Entity
@Table(name = "personagem")
public class Personagem implements Serializable {
	
	@Id
    @Column(name="id_personagem", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pers_seq_name")
	@SequenceGenerator(name = "pers_seq_name", sequenceName = "pers_seq", allocationSize = 1)
	private Long id;
	
	@Column(name="p_sobrenome", nullable = false, length = 100)
	private String sobrenome;
	
	@Column(name="p_nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name="p_apelido", nullable = false, length = 100)
	private String apelido;
	
	@ManyToOne
	@JoinColumn(name = "id_loc_nascimento")
	@ForeignKey(name = "fk_per_loc_nasc")
	private Local localNascimento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "p_data_nasc", nullable = true, length = 10)
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "id_loc_morte")
	@ForeignKey(name = "fk_per_loc_mort")
	private Local localMorte;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "p_data_morte", nullable = true, length = 10)
	private Date dataMorte;
	
	@Column(name="p_biografia", columnDefinition="TEXT", nullable = true)
    private String biografia;
	
	@Column(name="p_ocupacao", nullable = true)
    private String ocupacao;
	
	@Column(name="p_formacao", nullable = true)
    private String formacao;
	
	@Column(name="p_ref_bibliografica", columnDefinition="TEXT", nullable = true)
	private String referenciaBibliografica;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pers_crencas", joinColumns = { 
			@JoinColumn(name = "id_personagem", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_religiao", 
			nullable = false, updatable = false) })
	private Set<ReligiaoCrencas> religioes = new HashSet<ReligiaoCrencas>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pers_encontros", joinColumns = { 
			@JoinColumn(name = "id_personagem", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_encontro", 
			nullable = false, updatable = false) })
	private Set<Encontro> encontros = new HashSet<Encontro>(0);

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pers_foob", joinColumns = { 
			@JoinColumn(name = "id_personagem", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_fontes", 
			nullable = false, updatable = false) })
	private Set<FontesObras> obras = new HashSet<FontesObras>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pers_locais", joinColumns = { 
			@JoinColumn(name = "id_personagem", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_local", 
			nullable = false, updatable = false) })
	private Set<Local> locaisPersonagens = new HashSet<Local>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pers_correspondencias", joinColumns = { 
			@JoinColumn(name = "id_personagem", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_correspondencia", 
			nullable = false, updatable = false) })
	private Set<Correspondencia> correspondencias = new HashSet<Correspondencia>(0);
	
	@Type(type="yes_no")
	@Column(name = "active")
	private Boolean active;
	
	//
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "leitores")
	private Set<FontesObras> pLeitores = new HashSet<FontesObras>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "autoresCitados")
	private Set<FontesObras> pAutoresCitados = new HashSet<FontesObras>(0);
    
	public Personagem() {}
	
	public Personagem(Long id) {
		this.id = id;
	}

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
	public Local getLocalNascimento() {
		return localNascimento;
	}
	public void setLocalNascimento(Local localNascimento) {
		this.localNascimento = localNascimento;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Local getLocalMorte() {
		return localMorte;
	}
	public void setLocalMorte(Local localMorte) {
		this.localMorte = localMorte;
	}
	public Date getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(Date dataMorte) {
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
	public Set<FontesObras> getpLeitores() {
		return pLeitores;
	}
	public void setpLeitores(Set<FontesObras> pLeitores) {
		this.pLeitores = pLeitores;
	}
	public Set<FontesObras> getpAutoresCitados() {
		return pAutoresCitados;
	}
	public void setpAutoresCitados(Set<FontesObras> pAutoresCitados) {
		this.pAutoresCitados = pAutoresCitados;
	}	
	public String getReferenciaBibliografica() {
		return referenciaBibliografica;
	}
	public void setReferenciaBibliografica(String referenciaBibliografica) {
		this.referenciaBibliografica = referenciaBibliografica;
	}
	public Set<ReligiaoCrencas> getReligioes() {
		return religioes;
	}
	public void setReligioes(Set<ReligiaoCrencas> religioes) {
		this.religioes = religioes;
	}
	public Set<Encontro> getEncontros() {
		return encontros;
	}
	public void setEncontros(Set<Encontro> encontros) {
		this.encontros = encontros;
	}
	public Set<FontesObras> getObras() {
		return obras;
	}
	public void setObras(Set<FontesObras> obras) {
		this.obras = obras;
	}	
	public Set<Local> getLocaisPersonagens() {
		return locaisPersonagens;
	}
	public void setLocaisPersonagens(Set<Local> locaisPersonagens) {
		this.locaisPersonagens = locaisPersonagens;
	}
	public Set<Correspondencia> getCorrespondencias() {
		return correspondencias;
	}
	public void setCorrespondencias(Set<Correspondencia> correspondencias) {
		this.correspondencias = correspondencias;
	}

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}