package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.EntityPersistenceException;
import persistence.model.exceptions.GroupCharacterNotFoundException;
import datatype.SimpleDate;

@Entity
public class GrupoPersonagem extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate anoIngresso;

    @ManyToOne
    private GrupoMovimento grupoMovimento;

    public GrupoPersonagem() {
    } // Hibernate

    public GrupoPersonagem(SimpleDate anoIngresso, GrupoMovimento grupoMovimento) {
	this.anoIngresso = anoIngresso;
	this.grupoMovimento = grupoMovimento;
    }

    public Long getId() {
	return id;
    }

    public SimpleDate getAnoIngresso() {
	return anoIngresso;
    }

    public void setAnoIngresso(SimpleDate anoIngresso) {
	this.anoIngresso = anoIngresso;
    }

    public GrupoMovimento getGrupoMovimento() {
	return grupoMovimento;
    }

    public void setGrupoMovimento(GrupoMovimento grupoMovimento) {
	this.grupoMovimento = grupoMovimento;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (isPersisted(em)) {
	    throw new EntityPersistenceException("Grupo/Personagem já existente!", null);
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new GroupCharacterNotFoundException(
		    "Grupo não encontrado!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new GroupCharacterNotFoundException(
		    "Grupo não encontrado!"));
	}
    }

}
