package persistence.model.exceptions;

public class ReferenceNotFoundException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public ReferenceNotFoundException() {
	super();
    }

    public ReferenceNotFoundException(String string) {
	super(string);
    }

}
