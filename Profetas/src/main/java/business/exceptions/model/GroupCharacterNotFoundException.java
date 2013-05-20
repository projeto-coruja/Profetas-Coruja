package business.exceptions.model;

public class GroupCharacterNotFoundException extends Exception {

	private static final long serialVersionUID = 149172638994485821L;

	public GroupCharacterNotFoundException() {
		super();
	}

	public GroupCharacterNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public GroupCharacterNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public GroupCharacterNotFoundException(String msg) {
		super(msg);
	}

	public GroupCharacterNotFoundException(Throwable arg0) {
		super(arg0);
	}
	
}
