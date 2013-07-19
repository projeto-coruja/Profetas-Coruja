package business.exceptions.model;

public class DuplicateSCorrespondenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8809527036359537876L;

	public DuplicateSCorrespondenceException() {
	}

	public DuplicateSCorrespondenceException(String message) {
		super(message);
	}

	public DuplicateSCorrespondenceException(Throwable cause) {
		super(cause);
	}

	public DuplicateSCorrespondenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateSCorrespondenceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
