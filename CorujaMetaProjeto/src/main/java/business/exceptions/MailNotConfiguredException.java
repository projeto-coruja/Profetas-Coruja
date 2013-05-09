package business.exceptions;

public class MailNotConfiguredException extends Exception {
	private static final long serialVersionUID = 1L;

	public MailNotConfiguredException() {
	}

	public MailNotConfiguredException(String arg0) {
		super(arg0);
	}

	public MailNotConfiguredException(Throwable arg0) {
		super(arg0);
	}

	public MailNotConfiguredException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MailNotConfiguredException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
