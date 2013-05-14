package business.exceptions.model;

public class DuplicateClassificationException extends Exception {

	private static final long serialVersionUID = -1123937683286049875L;

	public DuplicateClassificationException() {
		super();
	}

	public DuplicateClassificationException(String msg) {
		super(msg);
	}

	public DuplicateClassificationException(Throwable arg0) {
		super(arg0);
	}

	public DuplicateClassificationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DuplicateClassificationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
}
