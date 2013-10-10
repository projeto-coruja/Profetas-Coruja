package persistence.model;

import static persistence.model.base.EntityUtility.notNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.CorrespondenceNotFoundException;
import persistence.model.exceptions.DuplicateCorrespondenceException;
import persistence.model.exceptions.EntityPersistenceException;
import datatype.SimpleDate;

@Entity
public class Correspondencia extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Personagem remetente;

    @NotNull
    @ManyToOne
    private Personagem destinatario;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate data;

    @ManyToOne
    private Local local;

    public Correspondencia() {
    } // Hibernate

    public Correspondencia(Personagem remetente, Personagem destinatario, SimpleDate data,
	    Local local) {
	this.remetente = remetente;
	this.destinatario = destinatario;
	this.data = data;
	this.local = local;
    }

    @Override
    public Long getId() {
	return id;
    }

    public Personagem getRemetente() {
	return remetente;
    }

    public void setRemetente(Personagem remetente) {
	this.remetente = remetente;
    }

    public Personagem getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(Personagem destinatario) {
	this.destinatario = destinatario;
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
	if (!notNull(remetente, destinatario)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateCorrespondenceException(
		    "Correspondência já existente!"));
	}

    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (!notNull(remetente, destinatario)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new CorrespondenceNotFoundException(
		    "Correspondência não existente!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new CorrespondenceNotFoundException(
		    "Correspondência não existente!"));
	}
    }

}
