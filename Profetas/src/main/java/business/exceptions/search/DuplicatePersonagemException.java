package business.exceptions.search;

public class DuplicatePersonagemException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatePersonagemException() {
		super();
	}

	public DuplicatePersonagemException(String msg) {
		super(msg);
	}

	public DuplicatePersonagemException(Throwable arg0) {
		super(arg0);
	}

	public DuplicatePersonagemException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DuplicatePersonagemException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
