package persistence.model.exceptions;

public class EntityPersistenceException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7542068848489414398L;

    public EntityPersistenceException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public EntityPersistenceException(String message, Throwable cause) {
	super(message, cause);
    }

    public EntityPersistenceException(Throwable cause) {
	super(cause);
    }

}
