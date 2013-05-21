package business.DAO.model;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.FontesObras;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.SourceWorkNotFoundException;

public class FontesObrasDAO {

	private PersistenceAccess manager;

	public FontesObrasDAO() {
		manager = new PersistenceAccess();
	}
	
	public void addSourceWork(FontesObras newSourceWork) throws UnreachableDataBaseException {
		if(newSourceWork == null)	throw new IllegalArgumentException("newSourceWork is null.");
		try {
			manager.saveEntity(newSourceWork);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeSourceWork(FontesObras sourceWork) throws UnreachableDataBaseException {
		if(sourceWork == null)	throw new IllegalArgumentException("Nenhuma Fonte/Obra especificada.");
		try{
			manager.deleteEntity(sourceWork);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public void updateSourceWork(FontesObras sourceWork) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException {
		if(sourceWork == null) throw new IllegalArgumentException("Fonte/Obra inexistente.");
		try { 
			if(sourceWork.getId() == null) addSourceWork(sourceWork);	
			else manager.updateEntity(sourceWork);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public List<DTO> findSourceWorkByQuery(String query) throws SourceWorkNotFoundException, UnreachableDataBaseException {
		List<DTO> resultSet = null;
		if(query == null)	throw new IllegalArgumentException("Query não pode ser null.");
		try {
			resultSet = manager.findEntity(query);
			if(resultSet == null) {
				throw new  SourceWorkNotFoundException("Nenhuma Fonte/Obra encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}	
}
