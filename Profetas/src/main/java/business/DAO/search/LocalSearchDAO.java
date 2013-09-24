package business.DAO.search;

import java.util.ArrayList;
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
	
	
	public List<DTO> findLocalByAll(String nome, double latitude, double longitude) throws LocalNotFoundException, UnreachableDataBaseException{
		List<DTO> resultSet = null;
		try {
			
			
			resultSet = manager.findEntity("FROM LocalMO WHERE nome like '%"+ nome +"%' AND latitude = "+ latitude +" "
					+ "AND longitude = "+ longitude +""
					+ " ORDER BY id");
			//resultSet = manager.findEntity("FROM LocalMO");
			
			
			if(resultSet == null) {
				//throw new  LocalNotFoundException ("Local não encontrado.");
				return new ArrayList<DTO>();
			}
			else{
				
				return (List<DTO>) resultSet;
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados: "+e);
		}
		
		
		
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
			
			resultSet = manager.findEntity("FROM localmo WHERE nome = '"+ nome +"'"
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
			
			resultSet = manager.findEntity("FROM localmo WHERE nome like '%" + nome +"%' "
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
			
			resultSet = manager.findEntity("FROM localmo WHERE latitude = '"+ latitude +"'"
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
			
			resultSet = manager.findEntity("FROM localmo WHERE lon gitude = '"+ longitude +"'"
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
