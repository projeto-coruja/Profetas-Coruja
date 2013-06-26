package business.exceptions;

public class DisallowedOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4414958973714745274L;

	public DisallowedOperationException() {
	}

	public DisallowedOperationException(String arg0) {
		super(arg0);
	}

	public DisallowedOperationException(Throwable arg0) {
		super(arg0);
	}

	public DisallowedOperationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DisallowedOperationException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
