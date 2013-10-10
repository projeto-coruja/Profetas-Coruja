package persistence.model.base;

import java.io.Serializable;

import persistence.EntityManager;
import persistence.model.exceptions.EntityPersistenceException;

/**
 * Classe base para todas as entidades<br>
 */
public abstract class EntityModel {

    protected abstract void checkSave(EntityManager em) throws EntityPersistenceException;

    protected abstract void checkUpdate(EntityManager em) throws EntityPersistenceException;

    protected abstract void checkRemove(EntityManager em) throws EntityPersistenceException;

    public abstract Long getId();

    public void save(EntityManager em) throws EntityPersistenceException {
	checkSave(em);
	em.save(this);
    }

    public void remove(EntityManager em) throws EntityPersistenceException {
	checkRemove(em);
	em.delete(this);
    }

    public void update(EntityManager em) throws EntityPersistenceException {
	checkUpdate(em);
	em.update(this);
    }

    protected boolean isPersisted(EntityManager em) {
	return this.getId() != null;
    }

    protected boolean isDouble(EntityManager em, Serializable value) {
	EntityModel check = em.findByNaturalId(this.getClass(), value);
	return check != null && !check.getId().equals(getId());
    }

}
