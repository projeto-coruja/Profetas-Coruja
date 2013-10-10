package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.EncounterNotFoundException;
import persistence.model.exceptions.EntityPersistenceException;
import datatype.SimpleDate;

@Entity
public class Encontro extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate data;

    @ManyToOne
    private Local local;

    public Encontro() {
    } // Hibernate

    public Encontro(SimpleDate data, Local local) {
	this.data = data;
	this.local = local;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public SimpleDate getData() {
	return data;
    }

    public void setData(SimpleDate data) {
	this.data = data;
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
	    throw new EntityPersistenceException(new EncounterNotFoundException(
		    "Encontro não existente no banco!"));
	}

    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new EncounterNotFoundException(
		    "Encontro não existente no banco!"));
	}
    }

}
