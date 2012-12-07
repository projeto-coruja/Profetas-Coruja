package persistence.util;

/**
 * Small exception class used in DTOUtility
 * 
 * @author Vitor Kawai
 * @since Milestone 1
 */

public class UpdateEntityException extends Exception {

	private static final long serialVersionUID = 887995666636556777L;

	public UpdateEntityException() {
		super();
	}

	public UpdateEntityException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateEntityException(String message) {
		super(message);
	}

	public UpdateEntityException(Throwable cause) {
		super(cause);
	}

	
}
