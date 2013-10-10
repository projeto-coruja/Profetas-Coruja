package persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.DuplicateSourceWorkException;
import persistence.model.exceptions.EntityPersistenceException;
import persistence.model.exceptions.SourceWorkNotFoundException;

import com.google.common.base.Strings;

import datatype.SimpleDate;

@Entity
public class FontesObras extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String titulo;

    private String comentarios;

    private String referenciasCirculacaoObra;

    private String URL;

    private String copiasManuscritas;

    private String traducoes;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate dataImpressao;

    private String editor;

    @ManyToOne
    private GrupoMovimento grupoMovimento;

    @ManyToOne
    private Local localImpressao;

    @ManyToOne
    private Classificacao classificao;

    @OneToMany
    private List<PalavraChave> palavraChave;

    @ManyToMany
    private List<FontesObras> obrasCitadas;

    @ManyToMany
    private List<Personagem> leitores;

    @ManyToMany
    private List<Personagem> personagens;

    @ManyToMany
    private List<Personagem> autoresCitados;

    public FontesObras() {
    } // Hibernate

    public FontesObras(String titulo, String comentarios, String refverenciasirCulacaoObra,
	    String uRL, String copiasManuscritas, String traducoes, SimpleDate dataImpressao,
	    String editor, GrupoMovimento grupoMovimento, Local localImpressao,
	    Classificacao classificao, List<PalavraChave> palavraChave,
	    List<FontesObras> obrasCitadas, List<Personagem> leitores,
	    List<Personagem> personagens, List<Personagem> autoresCitados) {
	this.titulo = titulo;
	this.comentarios = comentarios;
	this.referenciasCirculacaoObra = refverenciasirCulacaoObra;
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

    public String getReferenciasCirculacaoObra() {
	return referenciasCirculacaoObra;
    }

    public void setReferenciasCirculacaoObra(String referenciasCirculacaoObra) {
	this.referenciasCirculacaoObra = referenciasCirculacaoObra;
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

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(titulo)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateSourceWorkException(
		    "Fonte já existente no banco de dados!"));
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(titulo)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new SourceWorkNotFoundException(
		    "Fonte não existente no banco de dados!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new SourceWorkNotFoundException(
		    "Fonte não existente no banco de dados!"));
	}
    }

}
