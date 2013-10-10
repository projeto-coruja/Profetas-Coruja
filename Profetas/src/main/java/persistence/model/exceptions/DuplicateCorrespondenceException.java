package persistence.model.exceptions;

public class DuplicateCorrespondenceException extends Exception {

    private static final long serialVersionUID = 8809527036359537876L;

    public DuplicateCorrespondenceException() {
    }

    public DuplicateCorrespondenceException(String message) {
	super(message);
    }

    public DuplicateCorrespondenceException(Throwable cause) {
	super(cause);
    }

    public DuplicateCorrespondenceException(String message, Throwable cause) {
	super(message, cause);
    }

    public DuplicateCorrespondenceException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
