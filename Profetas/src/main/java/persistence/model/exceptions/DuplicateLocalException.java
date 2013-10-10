package persistence.model.exceptions;

public class DuplicateLocalException extends Exception {

    private static final long serialVersionUID = -1123937683286049875L;

    public DuplicateLocalException() {
	super();
    }

    public DuplicateLocalException(String msg) {
	super(msg);
    }

    public DuplicateLocalException(Throwable arg0) {
	super(arg0);
    }

    public DuplicateLocalException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public DuplicateLocalException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
	super(arg0, arg1, arg2, arg3);
    }

}
