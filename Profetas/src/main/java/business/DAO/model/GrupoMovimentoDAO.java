package business.DAO.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import datatype.SimpleDate;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateGroupMovementException;
import business.exceptions.model.GroupMovementNotFoundException;
import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.EntityModel;
import persistence.model.GrupoMovimento;
import persistence.model.Local;
import persistence.util.DataAccessLayerException;

public class GrupoMovimentoDAO {

	private EntityManager manager;

	public GrupoMovimentoDAO() {
		manager = new EntityManager();	
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
			manager.save(newGrupoMovimento);
			return newGrupoMovimento;
		}
	}
	
	public void removeGroupMovement(GrupoMovimento groupMovement) throws UnreachableDataBaseException {
		if(groupMovement == null) throw new IllegalArgumentException("Nenhum Grupo/Movimento especificado.");
		try{
			manager.delete(groupMovement);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	/*public void removeGroupMovementByName(String name) throws UnreachableDataBaseException, GroupMovementNotFoundException {
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
	}*/
	
	public void updateGroupMovement(GrupoMovimento groupMovement) 
			throws UnreachableDataBaseException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, NoSuchMethodException, SecurityException, UpdateEntityException, DuplicateGroupMovementException {
		
		if(groupMovement == null) throw new IllegalArgumentException("Nenhum Grupo/Movimento especificado.");
		try { 
			if(groupMovement.getId() == null) addGroupMovement(groupMovement.getNome(), groupMovement.getAnoInicio(), groupMovement.getAnoFim(), groupMovement.getDescricao(), groupMovement.getLocal());
			else manager.update(groupMovement);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public GrupoMovimento findGroupMovementByName(String groupMovementName) throws  UnreachableDataBaseException, GroupMovementNotFoundException {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoMovimento WHERE nome LIKE '%" + groupMovementName + "%' ORDER BY nome");
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo/Movimento não encontrado.");
			}
			else return (GrupoMovimento) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<EntityModel> getAllGroupsMovement() throws  UnreachableDataBaseException, GroupMovementNotFoundException {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoMovimento ORDER BY nome");
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
