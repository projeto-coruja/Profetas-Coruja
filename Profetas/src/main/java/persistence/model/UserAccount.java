package persistence.model;

import static persistence.model.base.EntityUtility.notEmpty;
import static persistence.model.base.EntityUtility.notNull;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.EntityPersistenceException;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.UserNotFoundException;

@Entity
public class UserAccount extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    @NotNull
    private Profile profile;

    @NaturalId(mutable = true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    private String generatedToken;

    private GregorianCalendar tokenDate;

    public UserAccount() {
    } // Hibernate

    public UserAccount(String name, Profile profile, String email, String password,
	    String generatedToken, GregorianCalendar tokenDate) {
	this.name = name;
	this.profile = profile;
	this.email = email;
	this.password = password;
	this.generatedToken = generatedToken;
	this.tokenDate = tokenDate;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Profile getProfile() {
	return profile;
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getGeneratedToken() {
	return generatedToken;
    }

    public void setGeneratedToken(String generatedToken) {
	this.generatedToken = generatedToken;
    }

    public GregorianCalendar getTokenDate() {
	return tokenDate;
    }

    public void setTokenDate(GregorianCalendar tokenDate) {
	this.tokenDate = tokenDate;
    }

    private static EmailValidator emailCheck = EmailValidator.getInstance();

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (notEmpty(name, email, password) && notNull(profile) && emailCheck.isValid(email)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException(new DuplicateUserException(
		    "Instância de usuário já existente!"));
	}
	if (isDouble(em, email)) {
	    throw new EntityPersistenceException(
		    new DuplicateUserException("Usuário já existente!"));
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (notEmpty(name, email, password) && notNull(profile) && emailCheck.isValid(email)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new UserNotFoundException(
		    "Instância de usuário não encontrada!"));
	}
	if (isDouble(em, email)) {
	    throw new EntityPersistenceException(
		    new DuplicateUserException("Usuário já existente!"));
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new UserNotFoundException(
		    "Instância de usuário não encontrada!"));
	}
    }

}
