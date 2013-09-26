package business.DAO.model;

import java.util.List;

import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.EntityModel;
import persistence.model.Personagem;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.CharacterNotFoundException;

public class PersonagemDAO {

	private EntityManager manager;

	public PersonagemDAO() {
		manager = new EntityManager();
	}
	
	public void addCharacter(Personagem newCharacter) throws UnreachableDataBaseException {
		if(newCharacter == null)	throw new IllegalArgumentException("newCharacter is null.");
		try {
			manager.save(newCharacter);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeCharacter(Personagem character) throws UnreachableDataBaseException {
		if(character == null)	throw new IllegalArgumentException("Nenhum Personagem especificado.");
		try{
			manager.delete(character);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public void updateCharacter(Personagem character) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException {
		if(character == null) throw new IllegalArgumentException("Personagem inexistente.");
		try { 
			if(character.getId() == null) addCharacter(character);	
			else manager.update(character);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public List<EntityModel> findCharacterByQuery(String query) throws CharacterNotFoundException, UnreachableDataBaseException {
		List<EntityModel> resultSet = null;
		if(query == null)	throw new IllegalArgumentException("Query não pode ser null.");
		try {
			resultSet = manager.find(query);
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
