package business.exceptions.model;

public class DuplicateGroupMovementException extends Exception {

	private static final long serialVersionUID = -1123937683286049875L;

	public DuplicateGroupMovementException() {
		super();
	}

	public DuplicateGroupMovementException(String msg) {
		super(msg);
	}

	public DuplicateGroupMovementException(Throwable arg0) {
		super(arg0);
	}

	public DuplicateGroupMovementException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DuplicateGroupMovementException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
}
