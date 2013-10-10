package business.exceptions.search;

public class PersonagemNotFoundException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public PersonagemNotFoundException() {
	super();
    }

    public PersonagemNotFoundException(String msg) {
	super(msg);
    }

    public PersonagemNotFoundException(Throwable arg0) {
	super(arg0);
    }

    public PersonagemNotFoundException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public PersonagemNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
	super(arg0, arg1, arg2, arg3);
    }

}
