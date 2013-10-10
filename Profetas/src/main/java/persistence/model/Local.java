package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.DuplicateLocalException;
import persistence.model.exceptions.EntityPersistenceException;
import persistence.model.exceptions.LocalNotFoundException;

import com.google.common.base.Strings;

@Entity
public class Local extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nome;

    private double latitude;

    private double longitude;

    public Local() {
    } // Hibernate

    public Local(String nome, double latitude, double longitude) {
	this.nome = nome;
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public double getLatitude() {
	return latitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(nome)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateLocalException("Local já existente!"));
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(nome)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new LocalNotFoundException(
		    "Local inexistente no banco!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new LocalNotFoundException(
		    "Local inexistente no banco!"));
	}
    }

}
