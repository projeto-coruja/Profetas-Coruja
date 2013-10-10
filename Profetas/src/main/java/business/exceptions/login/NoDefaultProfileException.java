package business.exceptions.login;

public class NoDefaultProfileException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7069113178481623461L;

    public NoDefaultProfileException() {
    }

    public NoDefaultProfileException(String message) {
	super(message);
    }

    public NoDefaultProfileException(Throwable cause) {
	super(cause);
    }

    public NoDefaultProfileException(String message, Throwable cause) {
	super(message, cause);
    }

    public NoDefaultProfileException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
