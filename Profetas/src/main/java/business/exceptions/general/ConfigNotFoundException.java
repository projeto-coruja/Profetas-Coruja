package business.exceptions.general;

public class ConfigNotFoundException extends Exception {
    private static final long serialVersionUID = 6987761371475916437L;

    public ConfigNotFoundException() {
	super();
    }

    public ConfigNotFoundException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public ConfigNotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public ConfigNotFoundException(String message) {
	super(message);
    }

    public ConfigNotFoundException(Throwable cause) {
	super(cause);
    }

}
