package business.exceptions.model;

public class CorrespondenciaNotFoundException extends Exception {

	private static final long serialVersionUID = -3341437813760056251L;

	public CorrespondenciaNotFoundException(){
		super();
	}

	public CorrespondenciaNotFoundException(String msg){
		super(msg);
	}
	
}
