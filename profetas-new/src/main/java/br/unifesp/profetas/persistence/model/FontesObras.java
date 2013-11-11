package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "fontes_obras")
public class FontesObras implements Serializable {
	
	@Id
    @Column(name="id_fontes", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foob_seq_name")
	@SequenceGenerator(name = "foob_seq_name", sequenceName = "foob_seq", allocationSize = 1)
	private Long id;
	
	@Column(name="f_titulo", nullable = false, length = 100)
	private String titulo;
	
	@Column(name="f_comentarios", columnDefinition="TEXT", nullable = true)
	private String comentarios;

	@Column(name="f_referencias", columnDefinition="TEXT", nullable = true)
    private String referenciasCirculacaoObra;

	@Column(name="f_url", nullable = true)
    private String url;

	@Column(name="f_copias", columnDefinition="TEXT", nullable = true)
    private String copiasManuscritas;

	@Column(name="f_traducoes", columnDefinition="TEXT", nullable = true)
    private String traducoes;
        
    @Column(name="f_editor", nullable = true)
    private String editor;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "f_data_impresao", nullable = true, length = 10)
    private Date dataImpressao;
    
    @ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_foob_local")
    private Local localImpressao;
    
    @ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_foob_classificacao")
    private Classificacao classificacao;
    
    @ManyToOne
    @PrimaryKeyJoinColumn
    @ForeignKey(name = "fk_foob_gru_movimento")
    private GrupoMovimento grupoMovimento;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "foob_leitores", joinColumns = { 
			@JoinColumn(name = "id_fontes", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_personagem", 
			nullable = false, updatable = false) })
    private Set<Personagem> leitores = new HashSet<Personagem>(0);
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "foob_personagens", joinColumns = { 
			@JoinColumn(name = "id_fontes", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_personagem", 
			nullable = false, updatable = false) })
    private Set<Personagem> personagens = new HashSet<Personagem>(0);
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "foob_autores_citados", joinColumns = { 
			@JoinColumn(name = "id_fontes", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_personagem", 
			nullable = false, updatable = false) })
    private Set<Personagem> autoresCitados = new HashSet<Personagem>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "foob_obras_citadas", joinColumns = { 
			@JoinColumn(name = "id_fontes", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_obras_citadas", 
			nullable = false, updatable = false) })
    private Set<FontesObras> obrasCitadas = new HashSet<FontesObras>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fontesObras")
    private Set<PalavraChave> palavrasChave = new HashSet<PalavraChave>(0);
    
    @Type(type="yes_no")
	@Column(name = "active")
	private Boolean active;
    
    //
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "obrasCitadas")
	private Set<FontesObras> foObrasCitadas = new HashSet<FontesObras>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "obras")
	private Set<Personagem> foPersonagem = new HashSet<Personagem>(0);

	public FontesObras() {}
	
	public FontesObras(Long id) {
		this.id = id;
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
	public Date getDataImpressao() {
		return dataImpressao;
	}
	public void setDataImpressao(Date dataImpressao) {
		this.dataImpressao = dataImpressao;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Local getLocalImpressao() {
		return localImpressao;
	}
	public void setLocalImpressao(Local localImpressao) {
		this.localImpressao = localImpressao;
	}
	public Classificacao getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}	
	public GrupoMovimento getGrupoMovimento() {
		return grupoMovimento;
	}
	public void setGrupoMovimento(GrupoMovimento grupoMovimento) {
		this.grupoMovimento = grupoMovimento;
	}	
	public Set<Personagem> getLeitores() {
		return leitores;
	}
	public void setLeitores(Set<Personagem> leitores) {
		this.leitores = leitores;
	}
	public Set<Personagem> getPersonagens() {
		return personagens;
	}
	public void setPersonagens(Set<Personagem> personagens) {
		this.personagens = personagens;
	}	
	public Set<Personagem> getAutoresCitados() {
		return autoresCitados;
	}
	public void setAutoresCitados(Set<Personagem> autoresCitados) {
		this.autoresCitados = autoresCitados;
	}
	public Set<FontesObras> getObrasCitadas() {
		return obrasCitadas;
	}
	public void setObrasCitadas(Set<FontesObras> obrasCitadas) {
		this.obrasCitadas = obrasCitadas;
	}
	public Set<FontesObras> getFoObrasCitadas() {
		return foObrasCitadas;
	}
	public void setFoObrasCitadas(Set<FontesObras> foObrasCitadas) {
		this.foObrasCitadas = foObrasCitadas;
	}
	public Set<PalavraChave> getPalavrasChave() {
		return palavrasChave;
	}
	public void setPalavrasChave(Set<PalavraChave> palavrasChave) {
		this.palavrasChave = palavrasChave;
	}
	public Set<Personagem> getFoPersonagem() {
		return foPersonagem;
	}
	public void setFoPersonagem(Set<Personagem> foPersonagem) {
		this.foPersonagem = foPersonagem;
	}

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}