package business.Bean.model;

import business.DAO.model.PalavraChaveDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateKeywordException;
import business.exceptions.model.KeywordNotFoundException;

public class PalavraChaveBean {

	private PalavraChaveDAO dao;
	
	public PalavraChaveBean() {
		dao = new PalavraChaveDAO();
	}
	
	/**
	 * Adiciona uma Palavra-chave nova.
	 * @param keyword - String contendo a palavra-chave.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws DuplicateKeywordException - Exceção occore quando a Palavra-chave já existe.
	 */
	public synchronized void addKeyword(String keyword) throws UnreachableDataBaseException, DuplicateKeywordException {
		dao.addKeyword(keyword);
	}
	
	/**
	 * Remove uma Palavra-chave especificada.
	 * @param keyword - String contendo a palavra-chave.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws KeywordNotFoundException - Exceção occore quando a Palavra-chave não existe.
	 */
	public synchronized void removeKeyWord(String keyword) throws UnreachableDataBaseException, KeywordNotFoundException {
		dao.removeKeyWord(keyword);		
	}
	
	/**
	 * Atualiza uma Palavra-chave especificada.
	 * @param oldKeyword - String contendo a Palavra-chave a ser modificada.
	 * @param newKeyword - String contendo a Palavra-chave nova.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws KeywordNotFoundException - Exceção occore quando a Palavra-chave não existe.
	 */
	public synchronized void updateKeyword(String oldKeyword, String newKeyword) throws UnreachableDataBaseException, KeywordNotFoundException {
		dao.updateKeyword(oldKeyword, newKeyword);		
	}	
}