package business.DAO.model;

import java.util.List;

import business.exceptions.model.LocalNotFoundException;
import business.exceptions.model.DuplicateLocalException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.IdentifiedEntity;
import persistence.model.Local;
import persistence.util.DataAccessLayerException;

public class LocalDAO {

	private EntityManager manager;

	public LocalDAO() {
		manager = new EntityManager();	
	}
	
	public Local addLocal(Local local) throws UnreachableDataBaseException, DuplicateLocalException {
		try {
			findExactLocal(local.getLatitude(), local.getLongitude());
			throw new DuplicateLocalException("Local já existente.");
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		} catch (LocalNotFoundException e) {
			manager.save(local);
			return local;
		}
	}
	
	public Local addLocal(String name, double latitude, double longitude) throws UnreachableDataBaseException, DuplicateLocalException {
		return addLocal(new Local(name, latitude, longitude));
	}
	
	public void removeLocal(Local local) throws UnreachableDataBaseException {
		try {
			manager.delete(local);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void updateLocal(Local local) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException {
		try {
			manager.update(local);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public Local findExactLocal(double latitude, double longitude) throws  UnreachableDataBaseException, LocalNotFoundException {		
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Local WHERE latitude = '"+ latitude +"'"
					+ " AND longitude = '" + longitude +"'"
					+ " ORDER BY nome, latitude, longitude");
			if(resultSet == null || resultSet.isEmpty()) {
				throw new LocalNotFoundException ("Local não encontrado.");
			}
			else return (Local) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> findLocalByName(String name) throws  UnreachableDataBaseException, LocalNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Local WHERE nome LIKE '%" + name +"%' ORDER BY nome, latitude, longitude");
			if(resultSet == null) {
				throw new LocalNotFoundException ("Local não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> findAllLocal() throws  UnreachableDataBaseException, LocalNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Local ORDER BY nome, latitude, longitude");
			if(resultSet == null) {
				throw new LocalNotFoundException("Não existe nenhum local cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}	
}
