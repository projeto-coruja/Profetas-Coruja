package persistence.model.exceptions;

public class DuplicateKeywordException extends Exception {

    private static final long serialVersionUID = -1123937683286049875L;

    public DuplicateKeywordException() {
	super();
    }

    public DuplicateKeywordException(String msg) {
	super(msg);
    }

    public DuplicateKeywordException(Throwable arg0) {
	super(arg0);
    }

    public DuplicateKeywordException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public DuplicateKeywordException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
	super(arg0, arg1, arg2, arg3);
    }

}
