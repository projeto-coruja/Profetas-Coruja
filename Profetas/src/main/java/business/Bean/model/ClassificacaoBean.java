package business.Bean.model;

import business.DAO.model.ClassificacaoDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.DuplicateClassificationException;

public class ClassificacaoBean {

	private ClassificacaoDAO dao;
	
	public ClassificacaoBean() {
		dao = new ClassificacaoDAO();
	}
	
	public synchronized void addClassification(String type) throws UnreachableDataBaseException, DuplicateClassificationException{
		dao.addClassification(type);
	}
	
	public synchronized void removeClassification(String type) throws UnreachableDataBaseException, ClassificationNotFoundException{
		dao.removeClassification(type);		
	}
	
	public synchronized void updateClassification(String oldType, String newType) throws UnreachableDataBaseException, ClassificationNotFoundException{
		dao.updateClassification(oldType, newType);		
	}
	
	
	
	
	
}