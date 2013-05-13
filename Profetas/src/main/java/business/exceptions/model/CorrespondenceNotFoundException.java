package business.exceptions.model;

public class CorrespondenceNotFoundException extends Exception {

	private static final long serialVersionUID = -3341437813760056251L;

	public CorrespondenceNotFoundException(){
		super();
	}

	public CorrespondenceNotFoundException(String msg){
		super(msg);
	}
	
}
