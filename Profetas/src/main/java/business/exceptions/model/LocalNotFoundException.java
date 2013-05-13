package business.exceptions.model;

public class LocalNotFoundException extends Exception {

	private static final long serialVersionUID = -1123937683286049875L;

	public LocalNotFoundException() {
		super();
	}

	public LocalNotFoundException(String msg) {
		super(msg);
	}

	public LocalNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public LocalNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LocalNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
}
