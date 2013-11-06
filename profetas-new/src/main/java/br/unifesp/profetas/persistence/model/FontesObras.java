package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

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

	public FontesObras() {}

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
}