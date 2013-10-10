package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.DuplicateReligionException;
import persistence.model.exceptions.EntityPersistenceException;
import persistence.model.exceptions.ReligionNotFoundException;

import com.google.common.base.Strings;

import datatype.SimpleDate;

@Entity
public class ReligiaoCrencas extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @NotEmpty
    private String nome;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate anoInicio;

    @Type(type = "persistence.util.SimpleDateHibernateType")
    private SimpleDate anoFim;

    private String descricao;

    public ReligiaoCrencas() {
    } // Hibernate

    public ReligiaoCrencas(String nome, SimpleDate anoInicio, SimpleDate anoFim, String descricao) {
	this.nome = nome;
	this.anoInicio = anoInicio;
	this.anoFim = anoFim;
	this.descricao = descricao;
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

    public SimpleDate getAnoInicio() {
	return anoInicio;
    }

    public void setAnoInicio(SimpleDate anoInicio) {
	this.anoInicio = anoInicio;
    }

    public SimpleDate getAnoFim() {
	return anoFim;
    }

    public void setAnoFim(SimpleDate anoFim) {
	this.anoFim = anoFim;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(nome)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateReligionException(
		    "Instãncia de religião já existente!"));
	}
	if (isDouble(em, nome)) {
	    throw new EntityPersistenceException(new DuplicateReligionException(
		    "Religião já existente!"));
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(nome)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new ReligionNotFoundException(
		    "Instãncia de religião inexistente!"));
	}
	if (isDouble(em, nome)) {
	    throw new EntityPersistenceException(new DuplicateReligionException(
		    "Religião já existente!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new ReligionNotFoundException(
		    "Instãncia de religião inexistente!"));
	}
    }

}
