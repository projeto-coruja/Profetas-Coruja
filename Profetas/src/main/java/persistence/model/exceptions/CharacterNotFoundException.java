package persistence.model.exceptions;

public class CharacterNotFoundException extends Exception {

    private static final long serialVersionUID = -3341437813760056251L;

    public CharacterNotFoundException() {
	super();
    }

    public CharacterNotFoundException(String msg) {
	super(msg);
    }

}
