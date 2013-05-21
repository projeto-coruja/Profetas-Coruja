package business.Bean.model;

import persistence.dto.AcervoReferencia;
import business.DAO.model.AcervoReferenciaDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateReferenceException;
import business.exceptions.model.ReferenceNotFoundException;

public class AcervoReferenciaBean {

	private AcervoReferenciaDAO dao;
	
	public AcervoReferenciaBean(){
		dao = new AcervoReferenciaDAO();
	}
	
	public synchronized void addNewReference (String name) throws UnreachableDataBaseException, DuplicateReferenceException{
		try{
			dao.findReferenceByName(name);
		} catch (ReferenceNotFoundException e){
			dao.addReference(name);
			e.printStackTrace();
		}
	}
	
	public synchronized void removeReference (String name) throws UnreachableDataBaseException, ReferenceNotFoundException{
		AcervoReferencia acervoReferencia;
		try {
			acervoReferencia = dao.findReferenceByName(name);
			dao.removeReference(acervoReferencia);
		} catch (ReferenceNotFoundException e){
		}
		
	}
	
	
	
	
}