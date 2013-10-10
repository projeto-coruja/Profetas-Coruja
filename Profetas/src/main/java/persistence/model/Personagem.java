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
import persistence.model.exceptions.CharacterNotFoundException;
import persistence.model.exceptions.EntityPersistenceException;

import com.google.common.base.Strings;

import datatype.SimpleDate;

@Entity
public class Personagem extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nome;

    private String apelido;

    @ManyToOne
    private Local localNascimento;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate dataNascimento;

    @ManyToOne
    private Local localMorte;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate dataMorte;

    private String biografia;

    private String ocupacao;

    private String formacao;

    @ManyToOne
    private FontesObras referencia_bibliografica;

    @ManyToMany
    private List<ReligiaoCrencas> religião;

    @ManyToMany
    private List<GrupoPersonagem> grupo;

    @OneToMany
    private List<LocaisPersonagens> locaisVisitados;

    @ManyToMany
    private List<Encontro> encontro;

    @ManyToMany
    private List<FontesObras> obras;

    public Personagem() {
    } // Hibernate

    public Personagem(String nome, String apelido, Local localNascimento,
	    SimpleDate dataNascimento, Local localMorte, SimpleDate dataMorte, String biografia,
	    String ocupacao, String formacao, FontesObras referencia_bibliografica,
	    List<ReligiaoCrencas> religião, List<GrupoPersonagem> grupo,
	    List<LocaisPersonagens> locaisVisitados, List<Encontro> encontro,
	    List<FontesObras> obras) {
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
	this.obras = obras;
    }

    public Long getId() {
	return id;
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
	return obras;
    }

    public void setObras(List<FontesObras> obras) {
	this.obras = obras;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(nome)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instãncia mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException("Personagem duplicado!", null);
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(nome)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instãncia mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new CharacterNotFoundException(
		    "Personagem não encontrado!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new CharacterNotFoundException(
		    "Personagem não encontrado!"));
	}
    }

}
