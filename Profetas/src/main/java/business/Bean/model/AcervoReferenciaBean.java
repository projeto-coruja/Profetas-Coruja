package business.Bean.model;

import business.DAO.model.AcervoReferenciaDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateReferenceException;
import business.exceptions.model.ReferenceNotFoundException;

public class AcervoReferenciaBean {

	private AcervoReferenciaDAO dao;
	
	public AcervoReferenciaBean() {
		dao = new AcervoReferenciaDAO();
	}
	
	/**
	 * Adiciona uma Referência nova.
	 * @param name - String contendo o nome da Referência.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws DuplicateReferenceException - Exceção occore quando uma Referência com tal nome já existe.
	 */
	public synchronized void addNewReference(String name) throws UnreachableDataBaseException, DuplicateReferenceException {
		dao.addReference(name);
	}
	
	/**
	 * Remove uma Referência especificada.
	 * @param name - String contendo nome da Referência a ser removida.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws ReferenceNotFoundException - Exceção ocorre quando a Referência com tal nome não existe.
	 */
	public synchronized void removeReference(String name) throws UnreachableDataBaseException, ReferenceNotFoundException {
		dao.removeReference(name);		
	}
	
	/**
	 * Atualiza uma Referência especificada.
	 * @param oldName - String contendo nome da Referência a ser modificada.
	 * @param newName - String contendo novo nome da Referência.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws ReferenceNotFoundException - Exceção ocorre quando a Referência com tal nome não existe.
	 */
	public synchronized void updateReference(String oldName, String newName) throws UnreachableDataBaseException, ReferenceNotFoundException {
		dao.updateReference(oldName, newName);
	}	
}