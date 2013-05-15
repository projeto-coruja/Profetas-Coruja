package business.exceptions.model;

public class LocalsCharactersNotFoundException extends Exception {

	private static final long serialVersionUID = 149172638994485821L;

	public LocalsCharactersNotFoundException() {
		super();
	}

	public LocalsCharactersNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public LocalsCharactersNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LocalsCharactersNotFoundException(String msg) {
		super(msg);
	}

	public LocalsCharactersNotFoundException(Throwable arg0) {
		super(arg0);
	}
	
}
