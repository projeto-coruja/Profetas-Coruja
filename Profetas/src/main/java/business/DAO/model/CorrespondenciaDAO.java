package business.DAO.model;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Correspondencia;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;
import business.exceptions.model.CorrespondenceNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class CorrespondenciaDAO {

	private PersistenceAccess manager;

	public CorrespondenciaDAO() {
		manager = new PersistenceAccess();
	}
	
	public void addCorrespondence(Correspondencia newCor) throws UnreachableDataBaseException {
		if(newCor == null)	throw new IllegalArgumentException("newCorrespondencia is null.");
		try {
			manager.saveEntity(newCor);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeCorrespondence(Correspondencia cor) throws UnreachableDataBaseException {
		if(cor == null)	throw new IllegalArgumentException("Nenhuma correspondência especificada.");
		try{
			manager.deleteEntity(cor);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public void updateCorrespondence(Correspondencia cor) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException {
		if(cor == null) throw new IllegalArgumentException("Correspondência inexistente.");
		try { 
			if(cor.getId() == null) addCorrespondence(cor);	
			else manager.updateEntity(cor);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public List<DTO> findCorrespondenceByQuery(String query) throws CorrespondenceNotFoundException, UnreachableDataBaseException{
		List<DTO> resultSet = null;
		if(query == null)	throw new IllegalArgumentException("Query não pode ser null.");
		try {
			resultSet = manager.findEntity(query);
			if(resultSet == null) {
				throw new  CorrespondenceNotFoundException("Nenhuma correspondência encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
}
