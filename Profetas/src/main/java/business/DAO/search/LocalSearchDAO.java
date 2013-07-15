package business.DAO.search;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Local;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.LocalNotFoundException;

public class LocalSearchDAO {
	
	private PersistenceAccess manager;
	
	public LocalSearchDAO(){
		manager = new PersistenceAccess();
	}
	
	/**
	 * Pesquisa por um  Local usando o nome
	 * @param nome  - nome do local
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public Local findExactLocalByNome(String nome) throws LocalNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			
			resultSet = manager.findEntity("FROM localMO WHERE nome = '"+ nome +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  LocalNotFoundException ("Local não encontrado.");
			}
			else{
				
				return  (Local) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um  Local usando PARTE do  nome
	 * @param nome  - nome do local
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public Local findLocalByNome(String nome) throws LocalNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			
			resultSet = manager.findEntity("FROM localMO WHERE nome like '%" + nome +"%' "
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  LocalNotFoundException ("Local não encontrado.");
			}
			else{
				
				return  (Local) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um local usando latitude
	 * @param latitude -  latitude do local 
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public Local findLocalByLatitude(double latitude) throws LocalNotFoundException, UnreachableDataBaseException{
		List<DTO> resultSet = null;
		try {
			
			resultSet = manager.findEntity("FROM localMO WHERE latitude = '"+ latitude +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  LocalNotFoundException ("Local não encontrado.");
			}
			else{
				
				return  (Local) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um local usando longitude
	 * @param longitude -  longitudee do local 
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public Local findLocalByLongitude(double longitude) throws LocalNotFoundException, UnreachableDataBaseException{
		List<DTO> resultSet = null;
		try {
			
			resultSet = manager.findEntity("FROM localMO WHERE lon gitude = '"+ longitude +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  LocalNotFoundException ("Local não encontrado.");
			}
			else{
				
				return  (Local) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}

}
