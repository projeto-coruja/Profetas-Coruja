package business.DAO.model;

import java.util.List;

import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.EntityModel;
import persistence.model.FontesObras;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.SourceWorkNotFoundException;

public class FontesObrasDAO {

	private EntityManager manager;

	public FontesObrasDAO() {
		manager = new EntityManager();
	}
	
	public void addSourceWork(FontesObras newSourceWork) throws UnreachableDataBaseException {
		if(newSourceWork == null)	throw new IllegalArgumentException("newSourceWork is null.");
		try {
			manager.save(newSourceWork);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeSourceWork(FontesObras sourceWork) throws UnreachableDataBaseException {
		if(sourceWork == null)	throw new IllegalArgumentException("Nenhuma Fonte/Obra especificada.");
		try{
			manager.delete(sourceWork);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public void updateSourceWork(FontesObras sourceWork) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException {
		if(sourceWork == null) throw new IllegalArgumentException("Fonte/Obra inexistente.");
		try { 
			if(sourceWork.getId() == null) addSourceWork(sourceWork);	
			else manager.update(sourceWork);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public List<EntityModel> findSourceWorkByQuery(String query) throws SourceWorkNotFoundException, UnreachableDataBaseException {
		List<EntityModel> resultSet = null;
		if(query == null)	throw new IllegalArgumentException("Query não pode ser null.");
		try {
			resultSet = manager.find(query);
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
