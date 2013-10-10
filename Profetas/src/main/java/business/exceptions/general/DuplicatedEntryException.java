package business.exceptions.general;

public class DuplicatedEntryException extends Exception {

    private static final long serialVersionUID = 1512681328562348137L;

    public DuplicatedEntryException() {
    }

    public DuplicatedEntryException(String message) {
	super(message);
    }

    public DuplicatedEntryException(Throwable cause) {
	super(cause);
    }

    public DuplicatedEntryException(String message, Throwable cause) {
	super(message, cause);
    }

    public DuplicatedEntryException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
