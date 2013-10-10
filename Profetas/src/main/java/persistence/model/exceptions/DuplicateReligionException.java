package persistence.model.exceptions;

public class DuplicateReligionException extends Exception {

    private static final long serialVersionUID = -1123937683286049875L;

    public DuplicateReligionException() {
	super();
    }

    public DuplicateReligionException(String msg) {
	super(msg);
    }

    public DuplicateReligionException(Throwable arg0) {
	super(arg0);
    }

    public DuplicateReligionException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public DuplicateReligionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
	super(arg0, arg1, arg2, arg3);
    }

}
