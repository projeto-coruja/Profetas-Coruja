package persistence.model.exceptions;

public class DuplicateReferenceException extends Exception {

    private static final long serialVersionUID = -1123937683286049875L;

    public DuplicateReferenceException() {
	super();
    }

    public DuplicateReferenceException(String msg) {
	super(msg);
    }

    public DuplicateReferenceException(Throwable arg0) {
	super(arg0);
    }

    public DuplicateReferenceException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public DuplicateReferenceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
	super(arg0, arg1, arg2, arg3);
    }

}
