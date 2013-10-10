package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.DuplicateKeywordException;
import persistence.model.exceptions.EntityPersistenceException;
import persistence.model.exceptions.KeywordNotFoundException;

import com.google.common.base.Strings;

@Entity
public class PalavraChave extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @NotEmpty
    private String palavraChave;

    public PalavraChave() {
    } // Hibernate

    public PalavraChave(String palavraChave) {
	this.palavraChave = palavraChave;
    }

    @Override
    public Long getId() {
	return id;
    }

    public String getPalavraChave() {
	return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
	this.palavraChave = palavraChave;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(palavraChave)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateKeywordException(
		    "Instância de palavra-chave já existente!"));
	}
	if (isDouble(em, palavraChave)) {
	    throw new EntityPersistenceException(new DuplicateKeywordException(
		    "Palavra-chave já existente!"));
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(palavraChave)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new KeywordNotFoundException(
		    "Instância de palavra-chave inexistente!"));
	}
	if (isDouble(em, palavraChave)) {
	    throw new EntityPersistenceException(new DuplicateKeywordException(
		    "Palavra-chave já existente!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateKeywordException(
		    "Instância de palavra-chave já existente!"));
	}
    }

}
