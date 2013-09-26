package business.DAO.model;

import java.util.List;

import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.Correspondencia;
import persistence.model.IdentifiedEntity;
import persistence.util.DataAccessLayerException;
import business.exceptions.model.CorrespondenceNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class CorrespondenciaDAO {

	private EntityManager manager;

	public CorrespondenciaDAO() {
		manager = new EntityManager();
	}
	
	public void addCorrespondence(Correspondencia newCor) throws UnreachableDataBaseException {
		if(newCor == null)	throw new IllegalArgumentException("newCorrespondencia is null.");
		try {
			manager.save(newCor);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeCorrespondence(Correspondencia cor) throws UnreachableDataBaseException {
		if(cor == null)	throw new IllegalArgumentException("Nenhuma correspondência especificada.");
		try{
			manager.delete(cor);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public void updateCorrespondence(Correspondencia cor) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException {
		if(cor == null) throw new IllegalArgumentException("Correspondência inexistente.");
		try { 
			if(cor.getId() == null) addCorrespondence(cor);	
			else manager.update(cor);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}

	public List<IdentifiedEntity> findCorrespondenceByQuery(String query) throws CorrespondenceNotFoundException, UnreachableDataBaseException {
		List<IdentifiedEntity> resultSet = null;
		if(query == null)	throw new IllegalArgumentException("Query não pode ser null.");
		try {
			resultSet = manager.find(query);
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
