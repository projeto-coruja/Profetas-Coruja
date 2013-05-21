package business.exceptions.model;

public class SourceWorkNotFoundException extends Exception {

	private static final long serialVersionUID = -3341437813760056251L;

	public SourceWorkNotFoundException(){
		super();
	}

	public SourceWorkNotFoundException(String msg){
		super(msg);
	}
	
}
