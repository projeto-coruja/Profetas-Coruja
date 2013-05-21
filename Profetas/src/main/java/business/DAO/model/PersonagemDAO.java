package business.DAO.model;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Personagem;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.CharacterNotFoundException;

public class PersonagemDAO {

	private PersistenceAccess manager;

	public PersonagemDAO() {
		manager = new PersistenceAccess();
	}
	
	public void addCharacter(Personagem newCharacter) throws UnreachableDataBaseException {
		if(newCharacter == null)	throw new IllegalArgumentException("newCharacter is null.");
		try {
			manager.saveEntity(newCharacter);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeCharacter(Personagem character) throws UnreachableDataBaseException {
		if(character == null)	throw new IllegalArgumentException("Nenhum Personagem especificado.");
		try{
			manager.deleteEntity(character);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public void updateCharacter(Personagem character) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException {
		if(character == null) throw new IllegalArgumentException("Personagem inexistente.");
		try { 
			if(character.getId() == null) addCharacter(character);	
			else manager.updateEntity(character);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public List<DTO> findCharacterByQuery(String query) throws CharacterNotFoundException, UnreachableDataBaseException {
		List<DTO> resultSet = null;
		if(query == null)	throw new IllegalArgumentException("Query não pode ser null.");
		try {
			resultSet = manager.findEntity(query);
			if(resultSet == null) {
				throw new  CharacterNotFoundException("Nenhum Personagem encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}	
}
