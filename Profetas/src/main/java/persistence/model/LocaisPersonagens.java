package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.EntityPersistenceException;
import persistence.model.exceptions.LocalsCharactersNotFoundException;
import datatype.SimpleDate;

@Entity
public class LocaisPersonagens extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate anoChegada;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate anoSaida;

    @ManyToOne
    private Local local;

    public LocaisPersonagens() {
    } // Hibernate

    public LocaisPersonagens(SimpleDate anoChegada, SimpleDate anoSaida, Local local) {
	this.anoChegada = anoChegada;
	this.anoSaida = anoSaida;
	this.local = local;
    }

    public Long getId() {
	return id;
    }

    public SimpleDate getAnoChegada() {
	return anoChegada;
    }

    public void setAnoChegada(SimpleDate anoChegada) {
	this.anoChegada = anoChegada;
    }

    public SimpleDate getAnoSaida() {
	return anoSaida;
    }

    public void setAnoSaida(SimpleDate anoSaida) {
	this.anoSaida = anoSaida;
    }

    public Local getLocal() {
	return local;
    }

    public void setLocal(Local local) {
	this.local = local;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (isPersisted(em)) {
	    throw new EntityPersistenceException("Instância duplicada!", null);
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new LocalsCharactersNotFoundException(
		    "Local/Personagens não existente!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new LocalsCharactersNotFoundException(
		    "Local/Personagens não existente!"));
	}
    }

}
