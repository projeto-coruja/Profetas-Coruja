package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.ClassificationNotFoundException;
import persistence.model.exceptions.DuplicateClassificationException;
import persistence.model.exceptions.EntityPersistenceException;

import com.google.common.base.Strings;

@Entity
public class Classificacao extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @NotEmpty
    private String tipo;

    public Classificacao() {
    } // Hibernate

    public Classificacao(String tipo) {
	this.tipo = tipo;
    }

    @Override
    public Long getId() {
	return id;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    // Tratadores do EntityModel

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(tipo)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateClassificationException(
		    "Classificação já existente no banco!"));
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(tipo)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new ClassificationNotFoundException(
		    "Classificação não encontrada no banco!"));
	}

    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new ClassificationNotFoundException(
		    "Classificação não encontrada no banco!"));
	}
    }

}
