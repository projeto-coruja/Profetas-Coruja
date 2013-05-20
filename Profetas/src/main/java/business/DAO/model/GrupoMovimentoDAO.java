package business.DAO.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import datatype.SimpleDate;

import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateGroupMovementException;
import business.exceptions.model.GroupMovementNotFoundException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.GrupoMovimento;
import persistence.dto.Local;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;

public class GrupoMovimentoDAO {

	private PersistenceAccess manager;

	public GrupoMovimentoDAO() {
		manager = new PersistenceAccess();	
	}
	
	public GrupoMovimento addGroupMovement(String name, SimpleDate yearBegin, SimpleDate yearEnd, String description, List<Local> local) throws UnreachableDataBaseException, DuplicateGroupMovementException {
		GrupoMovimento newGrupoMovimento = new GrupoMovimento(name, yearBegin, yearEnd, description, local);
		try {
			findGroupMovementByName(name);
			throw new DuplicateGroupMovementException("Grupo/Movimento já existente.");
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		} catch (GroupMovementNotFoundException e) {
			manager.saveEntity(newGrupoMovimento);
			return newGrupoMovimento;
		}
	}
	
	public void removeGroupMovement(GrupoMovimento groupMovement) throws UnreachableDataBaseException {
		if(groupMovement == null) throw new IllegalArgumentException("Nenhum Grupo/Movimento especificado.");
		try{
			manager.deleteEntity(groupMovement);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeGroupMovementByName(String name) throws UnreachableDataBaseException, GroupMovementNotFoundException {
		List<DTO> check = null;
		GrupoMovimento select = null;
		try {
			check = findGroupMovementByName(name);
			for(DTO dto : check){
				if (((GrupoMovimento) dto).getNome().equals(name))
					select = (GrupoMovimento) dto;
			}
			if(select == null) throw new GroupMovementNotFoundException("Grupo/Movimento não encontrado.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void updateGroupMovement(GrupoMovimento groupMovement) 
			throws UnreachableDataBaseException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, NoSuchMethodException, SecurityException, UpdateEntityException, DuplicateGroupMovementException {
		
		if(groupMovement == null) throw new IllegalArgumentException("Nenhum Grupo/Movimento especificado.");
		try { 
			if(groupMovement.getId() == null) addGroupMovement(groupMovement.getNome(), groupMovement.getAnoInicio(), groupMovement.getAnoFim(), groupMovement.getDescricao(), groupMovement.getLocal());
			else manager.updateEntity(groupMovement);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> findGroupMovementByName(String groupMovementName) throws  UnreachableDataBaseException, GroupMovementNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM GrupoMovimentoMO WHERE nome LIKE '%" + groupMovementName + "%' ORDER BY nome");
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo/Movimento não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> getAllReligions() throws  UnreachableDataBaseException, GroupMovementNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM GrupoMovimentoMO ORDER BY nome");
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Não existe nenhum grupo/movimento cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}	
}
