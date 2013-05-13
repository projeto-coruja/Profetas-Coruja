package business.DAO.model;

import java.util.List;

import datatype.SimpleDate;

import business.exceptions.model.EncounterNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Encontro;
import persistence.dto.Local;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;

public class EncontroDAO {

	private PersistenceAccess manager;

	public EncontroDAO() {
		manager = new PersistenceAccess();	
	}
	
	public Encontro addEncounter(SimpleDate date, String name) throws UnreachableDataBaseException, LocalNotFoundException {
		LocalDAO newLocalDAO = new LocalDAO();
		Local newLocal = null;
		
		List<DTO> check = newLocalDAO.findLocalByName(name);
		for (DTO dto : check) {
			if(((Local) dto).getNome().equals(name)) {
				newLocal = (Local) dto;
			}
		}
		
		Encontro newEncounter = new Encontro(date, newLocal);
		
		try {
			manager.saveEntity(newEncounter);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
		return newEncounter;
	}
	
	public void removeEncounter(String localName) throws UnreachableDataBaseException, EncounterNotFoundException{
		List<DTO> check = null;
		Encontro select = null;
		try{
			check = findEncounterByLocal(localName);
			for(DTO dto : check){
				if (((Encontro) dto).getLocal().getNome().equals(localName))
					select = (Encontro) dto;
			}
			if(select == null)	throw new EncounterNotFoundException("Encontro não encontrado.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public Encontro updateEncounter(SimpleDate newDate, String newEncounterLocal, String oldEncounterLocal) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException, EncounterNotFoundException {
		List<DTO> check = null;
		Encontro selectEncontro = null;
		Local selectLocal = null;
		
		boolean unmodifiedEncounterLocal = false;
		try {
			if(newEncounterLocal != null && !newEncounterLocal.isEmpty()){
				try{	
					check = (new LocalDAO()).findLocalByName(newEncounterLocal);
					for(DTO dto : check) {
						if(((Local) dto).getNome().equals(newEncounterLocal))
							selectLocal = (Local) dto;
					}
				} catch(DataAccessLayerException e){
					e.printStackTrace();
					throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
				} catch (LocalNotFoundException e) {
					throw new IllegalArgumentException("Local não existe.");
				}
			}
			else unmodifiedEncounterLocal = true;
			
			check = findEncounterByLocal(oldEncounterLocal);
			for(DTO dto : check) {
				if(((Encontro) dto).getLocal().getNome().equals(oldEncounterLocal))
					selectEncontro = (Encontro) dto;
			}
			
			if(!unmodifiedEncounterLocal) selectEncontro.setLocal(selectLocal);
			selectEncontro.setData(newDate);
			manager.updateEntity(selectEncontro);

		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
		
		return selectEncontro;
	}
	
	public List<DTO> findEncounterByDate(SimpleDate date) throws  UnreachableDataBaseException, EncounterNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM EncontroMO WHERE data = '" + date + "' ORDER BY data");
			if(resultSet == null) {
				throw new EncounterNotFoundException ("Encontro não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> findKeyWordByTheme(String theme) throws  UnreachableDataBaseException, EncounterNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from PalavraChaveMO where tema.tema = '" + theme + "' order by tema, palavra");
			if(resultSet == null) {
				throw new EncounterNotFoundException ("Tema não encontrado");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<DTO> getAllKeys() throws  UnreachableDataBaseException, EncounterNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from PalavraChaveMO order by tema.tema");
			if(resultSet == null) {
				throw new EncounterNotFoundException ("Nenhuma palavra aprovada");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
}
