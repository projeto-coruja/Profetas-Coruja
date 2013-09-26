package business.DAO.model;

import java.util.List;

import datatype.SimpleDate;
import business.exceptions.model.EncounterNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.Encontro;
import persistence.model.IdentifiedEntity;
import persistence.model.Local;
import persistence.util.DataAccessLayerException;

public class EncontroDAO {

	private EntityManager manager;

	public EncontroDAO() {
		manager = new EntityManager();	
	}
	
	public Encontro addEncounter(SimpleDate date, String localName) throws UnreachableDataBaseException, LocalNotFoundException {
		LocalDAO newLocalDAO = new LocalDAO();
		Local newLocal = null;
		
		List<IdentifiedEntity> check = newLocalDAO.findLocalByName(localName);
		for (IdentifiedEntity dto : check) {
			if(((Local) dto).getNome().equals(localName)) {
				newLocal = (Local) dto;
			}
		}
		
		Encontro newEncounter = new Encontro(date, newLocal);
		
		try {
			manager.save(newEncounter);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
		return newEncounter;
	}
	
	public void removeEncounter(String localName) throws UnreachableDataBaseException, EncounterNotFoundException {
		List<IdentifiedEntity> check = null;
		Encontro select = null;
		try {
			check = findEncounterByLocalName(localName);
			for(IdentifiedEntity dto : check) {
				if (((Encontro) dto).getLocal().getNome().equals(localName))
					select = (Encontro) dto;
			}
			if(select == null)	throw new EncounterNotFoundException("Encontro não encontrado.");
			manager.delete(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public Encontro updateEncounter(String oldEncounterLocal, String newEncounterLocal, SimpleDate newDate) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException, EncounterNotFoundException {
		List<IdentifiedEntity> check = null;
		Encontro selectEncontro = null;
		Local selectLocal = null;
		
		boolean unmodifiedEncounterLocal = false;
		try {
			if(newEncounterLocal != null && !newEncounterLocal.isEmpty()) {
				try {	
					check = (new LocalDAO()).findLocalByName(newEncounterLocal);
					for(IdentifiedEntity dto : check) {
						if(((Local) dto).getNome().equals(newEncounterLocal))
							selectLocal = (Local) dto;
					}
				} catch(DataAccessLayerException e) {
					e.printStackTrace();
					throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
				} catch (LocalNotFoundException e) {
					throw new IllegalArgumentException("Local não existe.");
				}
			}
			else unmodifiedEncounterLocal = true;
			
			check = findEncounterByLocalName(oldEncounterLocal);
			for(IdentifiedEntity dto : check) {
				if(((Encontro) dto).getLocal().getNome().equals(oldEncounterLocal))
					selectEncontro = (Encontro) dto;
			}
			
			if(!unmodifiedEncounterLocal) selectEncontro.setLocal(selectLocal);
			selectEncontro.setData(newDate);
			manager.update(selectEncontro);

		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
		
		return selectEncontro;
	}
	
	public List<IdentifiedEntity> findEncounterByDate(SimpleDate date) throws UnreachableDataBaseException, EncounterNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Encontro WHERE data = '" + date + "' ORDER BY data, local.nome");
			if(resultSet == null) {
				throw new EncounterNotFoundException ("Encontro não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> findEncounterByLocalName(String localName) throws UnreachableDataBaseException, EncounterNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Encontro WHERE local.nome = '" + localName + "' ORDER BY local.nome, data");
			if(resultSet == null) {
				throw new EncounterNotFoundException ("Encontro não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<IdentifiedEntity> getAllEncounters() throws  UnreachableDataBaseException, EncounterNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Encontro ORDER BY local.nome, data");
			if(resultSet == null) {
				throw new EncounterNotFoundException ("Não existe nenhum encontro cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}	
}
