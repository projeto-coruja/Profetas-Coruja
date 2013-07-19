package business.exceptions.model;

public class DuplicateSourceWorkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8809527036359537876L;

	public DuplicateSourceWorkException() {
	}

	public DuplicateSourceWorkException(String message) {
		super(message);
	}

	public DuplicateSourceWorkException(Throwable cause) {
		super(cause);
	}

	public DuplicateSourceWorkException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateSourceWorkException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
