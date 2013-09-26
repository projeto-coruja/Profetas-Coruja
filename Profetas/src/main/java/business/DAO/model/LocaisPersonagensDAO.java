package business.DAO.model;

import java.util.List;

import datatype.SimpleDate;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.model.LocalsCharactersNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.Encontro;
import persistence.model.EntityModel;
import persistence.model.LocaisPersonagens;
import persistence.model.Local;
import persistence.util.DataAccessLayerException;

public class LocaisPersonagensDAO {

	private EntityManager manager;

	public LocaisPersonagensDAO() {
		manager = new EntityManager();	
	}
	
	public LocaisPersonagens addLocalsCharacters(String localName, SimpleDate arrivalYear, SimpleDate leaveYear) throws UnreachableDataBaseException, LocalNotFoundException {
		LocalDAO newLocalDAO = new LocalDAO();
		Local newLocal = null;
		
		List<EntityModel> check = newLocalDAO.findLocalByName(localName);
		for (EntityModel dto : check) {
			if(((Local) dto).getNome().equals(localName)) {
				newLocal = (Local) dto;
			}
		}
		
		LocaisPersonagens newLocalsCharacters = new LocaisPersonagens(arrivalYear, leaveYear, newLocal);
		
		try {
			manager.save(newLocalsCharacters);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
		return newLocalsCharacters;
	}
	
	public void removeLocalsCharacters(String localName) throws UnreachableDataBaseException, LocalsCharactersNotFoundException {
		List<EntityModel> check = null;
		LocaisPersonagens select = null;
		try {
			check = findLocalsCharacters(localName);
			for(EntityModel dto : check) {
				if (((LocaisPersonagens) dto).getLocal().getNome().equals(localName))
					select = (LocaisPersonagens) dto;
			}
			if(select == null)	throw new LocalsCharactersNotFoundException("Locais-Personagens não encontrado.");
			manager.delete(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public LocaisPersonagens updateLocalsCharacters(String oldLocal, String newLocal, SimpleDate arrivalYear, SimpleDate leaveYear) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException, LocalsCharactersNotFoundException {
		List<EntityModel> check = null;
		LocaisPersonagens selectLocalsCharacters = null;
		Local selectLocal = null;
		
		boolean unmodifiedLocal = false;
		try {
			if(newLocal != null && !newLocal.isEmpty()) {
				try {	
					check = (new LocalDAO()).findLocalByName(newLocal);
					for(EntityModel dto : check) {
						if(((Local) dto).getNome().equals(newLocal))
							selectLocal = (Local) dto;
					}
				} catch(DataAccessLayerException e) {
					e.printStackTrace();
					throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
				} catch (LocalNotFoundException e) {
					throw new IllegalArgumentException("Local não existe.");
				}
			}
			else unmodifiedLocal = true;
			
			check = findLocalsCharacters(oldLocal);
			for(EntityModel dto : check) {
				if(((Encontro) dto).getLocal().getNome().equals(oldLocal))
					selectLocalsCharacters = (LocaisPersonagens) dto;
			}
			
			if(!unmodifiedLocal) selectLocalsCharacters.setLocal(selectLocal);
			selectLocalsCharacters.setAnoChegada(arrivalYear);
			selectLocalsCharacters.setAnoSaida(leaveYear);
			manager.update(selectLocalsCharacters);

		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
		
		return selectLocalsCharacters;
	}
	
	public List<EntityModel> findLocalsCharacters(String localName) throws UnreachableDataBaseException, LocalsCharactersNotFoundException {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM LocaisPersonagens WHERE local.nome = '" + localName + "' ORDER BY local.nome, anoChegada, anoSaida");
			if(resultSet == null) {
				throw new LocalsCharactersNotFoundException ("Locais-Personagens não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<EntityModel> findLocalsCharactersByArrivalYear(SimpleDate arrivalYear) throws UnreachableDataBaseException, LocalsCharactersNotFoundException {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM LocaisPersonagens WHERE data = '" + arrivalYear + "' ORDER BY anoChegada, local.nome");
			if(resultSet == null) {
				throw new LocalsCharactersNotFoundException ("Locais-Personagens não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<EntityModel> findLocalsCharactersByLeaveYear(SimpleDate leaveYear) throws UnreachableDataBaseException, LocalsCharactersNotFoundException {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM LocaisPersonagens WHERE data = '" + leaveYear + "' ORDER BY anoSaida, local.nome");
			if(resultSet == null) {
				throw new LocalsCharactersNotFoundException ("Locais-Personagens não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<EntityModel> getAllLocalsCharacters() throws  UnreachableDataBaseException, LocalsCharactersNotFoundException {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM LocaisPersonagens ORDER BY ORDER BY local.nome, anoChegada, anoSaida");
			if(resultSet == null) {
				throw new LocalsCharactersNotFoundException ("Não existe nenhum locais-personagens cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}	
}
