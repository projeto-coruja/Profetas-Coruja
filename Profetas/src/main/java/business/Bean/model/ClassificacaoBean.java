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
	
	/**
	 * Adiciona uma Classificação nova.
	 * @param type - String contendo o tipo da Classificação.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws DuplicateClassificationException - Exceção occore quando uma Classificação com tal tipo já existe.
	 */
	public synchronized void addClassification(String type) throws UnreachableDataBaseException, DuplicateClassificationException{
		dao.addClassification(type);
	}
	
	/**
	 * Remove uma Classificação especificada.
	 * @param type - String contendo o tipo da Classificação a ser removida.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws ClassificationNotFoundException - Exceção occore quando uma Classificação com tal tipo não existe.
	 */
	public synchronized void removeClassification(String type) throws UnreachableDataBaseException, ClassificationNotFoundException{
		dao.removeClassification(type);		
	}
	
	/**
	 * Atualiza uma Classificação especificada.
	 * @param oldType - String contendo o tipo da Classificação a ser modificada.
	 * @param newType - String contendo o tipo novo da Classificação.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws ClassificationNotFoundException - Exceção occore quando uma Classificação com tal tipo não existe.
	 */
	public synchronized void updateClassification(String oldType, String newType) throws UnreachableDataBaseException, ClassificationNotFoundException{
		dao.updateClassification(oldType, newType);		
	}	
}