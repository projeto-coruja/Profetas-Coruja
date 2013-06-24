package business.DAO.model;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.PalavraChave;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateKeywordException;
import business.exceptions.model.KeywordNotFoundException;

public class PalavraChaveDAO {

	private PersistenceAccess manager;

	public PalavraChaveDAO() {
		manager = new PersistenceAccess();	
	}
	
	public PalavraChave addKeyword(String keyword) throws UnreachableDataBaseException, DuplicateKeywordException {
		if(keyword.isEmpty() || keyword == null)	throw new IllegalArgumentException("Palavra-chave vazia ou nula.");
		PalavraChave newPalavraChave = new PalavraChave(keyword);
		try {
			findKeyword(keyword);
			throw new DuplicateKeywordException("Palavra-chave já existente.");
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		} catch (KeywordNotFoundException e) {
			manager.saveEntity(newPalavraChave);
			return newPalavraChave;
		}
	}
	
	public void removeKeyWord(String keyword) throws UnreachableDataBaseException, KeywordNotFoundException {
		if(keyword.isEmpty() || keyword == null)	throw new IllegalArgumentException("Palavra-chave vazia ou nula.");
		List<DTO> check = null;
		PalavraChave select = null;
		try {
			check = findKeyword(keyword);
			for(DTO dto : check){
				if (((PalavraChave) dto).getPalavraChave().equals(keyword))
					select = (PalavraChave) dto;
			}
			if(select == null)	throw new KeywordNotFoundException("Palavra-chave não encontrada.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public PalavraChave updateKeyword(String oldKeyword, String newKeyword) throws UnreachableDataBaseException, KeywordNotFoundException {
		if(oldKeyword.isEmpty() || oldKeyword == null || newKeyword.isEmpty() || newKeyword == null)	throw new IllegalArgumentException("Palavra-chave vazia ou nula.");
		List<DTO> check = null;
		PalavraChave select = null;
		try{
			check = findKeyword(oldKeyword);
			for(DTO dto : check){
				if (((PalavraChave) dto).getPalavraChave().equals(oldKeyword))
					select = (PalavraChave) dto;
			}
			try{
				check = findKeyword(newKeyword);
				for(DTO dto : check) {
					if (((PalavraChave) dto).getPalavraChave().equals(newKeyword))
						throw new IllegalArgumentException("Palavra-chave nova já existente.");
				}
			} catch (KeywordNotFoundException e) {}

			select.setPalavraChave(newKeyword);

			manager.updateEntity(select);
			return select;
			
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<DTO> findKeyword(String keyword) throws  UnreachableDataBaseException, KeywordNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM PalavraChaveMO WHERE palavraChave LIKE '%" + keyword + "%' ORDER BY palavraChave");
			if(resultSet == null) {
				throw new KeywordNotFoundException ("Palavra-chave não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> getAllKeywords() throws  UnreachableDataBaseException, KeywordNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM PalavraChaveMO ORDER BY palavraChave");
			if(resultSet == null) {
				throw new KeywordNotFoundException ("Não existe nenhuma palavra-chave cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}	
}
