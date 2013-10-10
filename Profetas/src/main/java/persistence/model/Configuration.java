package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.EntityPersistenceException;
import business.exceptions.general.DuplicatedEntryException;

import com.google.common.base.Strings;

@Entity
public class Configuration extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String entry;

    private String value;

    public Configuration(String entry, String value) {
	this.entry = entry;
	this.value = value;
    }

    public Configuration() {
    } // Hibernate

    @Override
    public Long getId() {
	return id;
    }

    public String getEntry() {
	return entry;
    }

    public void setEntry(String entry) {
	this.entry = entry;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(entry)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicatedEntryException(
		    "Entrada já existente!"));
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(entry)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicatedEntryException(
		    "Entrada inexistente!"));
	}

    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicatedEntryException(
		    "Entrada inexistente!"));
	}

    }
}