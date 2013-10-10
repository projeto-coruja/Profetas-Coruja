package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

import persistence.EntityManager;
import persistence.model.base.EntityModel;
import persistence.model.exceptions.EntityPersistenceException;
import business.exceptions.login.ProfileNotFoundException;

import com.google.common.base.Strings;

@Entity
public class Profile extends EntityModel {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @NotEmpty
    private String profile;

    private String[] permissions;

    private boolean isDefault;

    public Profile(String profile, String[] permissions, boolean isDefault) {
	this.profile = profile;
	this.permissions = permissions;
	this.isDefault = isDefault;
    }

    public Profile() {
    } // Hibernate

    @Override
    public Long getId() {
	return id;
    }

    public String getProfile() {
	return profile;
    }

    public void setProfile(String profile) {
	this.profile = profile;
    }

    public String[] getPermissions() {
	return permissions;
    }

    public void setPermissions(String[] permissions) {
	this.permissions = permissions;
    }

    public boolean isDefault() {
	return isDefault;
    }

    public void setDefault(boolean isDefault) {
	this.isDefault = isDefault;
    }

    @Override
    protected void checkSave(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(profile)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (isPersisted(em)) {
	    throw new EntityPersistenceException("Instância de profile já existente!", null);
	}
	if (isDouble(em, profile)) {
	    throw new EntityPersistenceException("Profile já existente!", null);
	}
    }

    @Override
    protected void checkUpdate(EntityManager em) throws EntityPersistenceException {
	if (Strings.isNullOrEmpty(profile)) {
	    throw new EntityPersistenceException(new IllegalStateException(
		    "Instância mal-construída!"));
	}
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new ProfileNotFoundException(
		    "Profile inexistente!"));
	}
	if (isDouble(em, profile)) {
	    throw new EntityPersistenceException("Profile duplicado!", null);
	}
    }

    @Override
    protected void checkRemove(EntityManager em) throws EntityPersistenceException {
	if (!isPersisted(em)) {
	    throw new EntityPersistenceException(new ProfileNotFoundException(
		    "Profile inexistente!"));
	}
    }
}
