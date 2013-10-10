package persistence.model.exceptions;

public class EncounterNotFoundException extends Exception {

    private static final long serialVersionUID = 149172638994485821L;

    public EncounterNotFoundException() {
	super();
    }

    public EncounterNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
	super(arg0, arg1, arg2, arg3);
    }

    public EncounterNotFoundException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public EncounterNotFoundException(String msg) {
	super(msg);
    }

    public EncounterNotFoundException(Throwable arg0) {
	super(arg0);
    }

}
