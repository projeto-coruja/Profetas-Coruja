package business.DAO.search;

import static business.DAO.search.DAOUtility.queryList;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Local;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.LocalNotFoundException;

public class LocalSearchDAO {
	
	private EntityManager manager;
	
	public LocalSearchDAO(){
		manager = new EntityManager();
	}
	
	public List<Local> findLocalByAll(String nome, double latitude, double longitude) throws LocalNotFoundException, UnreachableDataBaseException{
		try {
			List<Local> resultSet = manager.find("FROM Local WHERE nome like '%"+ nome +"%' AND latitude = "+ latitude +" "
					+ "AND longitude = "+ longitude +""
					+ " ORDER BY id");
			if(resultSet == null) {
				throw new  LocalNotFoundException ("Local não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados: "+e);
		}
	}
	
	/**
	 * Pesquisa por um Local usando o nome
	 * @param nome  - nome do local
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public List<Local> findExactLocalByNome(String nome) throws LocalNotFoundException, UnreachableDataBaseException{
		return executeQuery("nome", nome);
	}
	
	/**
	 * Pesquisa por um  Local usando PARTE do  nome
	 * @param nome  - nome do local
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public List<Local> findLocalByNome(String nome) throws LocalNotFoundException, UnreachableDataBaseException{
		try {
			List<Local> resultSet = manager.find("FROM local WHERE nome like '%" + nome +"%' "
					+ " ORDER BY id");
			if(resultSet.isEmpty()) {
				throw new  LocalNotFoundException ("Local não encontrado.");
			}
			else return resultSet;
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
	public List<Local> findLocalByLatitude(double latitude) throws LocalNotFoundException, UnreachableDataBaseException{
		return executeQuery("latitude", latitude);
	}
	
	/**
	 * Pesquisa por um local usando longitude
	 * @param longitude -  longitudee do local 
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public List<Local> findLocalByLongitude(double longitude) throws LocalNotFoundException, UnreachableDataBaseException{
		return executeQuery("longitude", longitude);
	}
	
	private List<Local> executeQuery(String field, Object info) throws LocalNotFoundException, UnreachableDataBaseException {
		List<Local> resultSet = queryList(manager, "local", field, info);
		if(resultSet.isEmpty()) {
			throw new  LocalNotFoundException ("Local Personagem não encontrado.");
		}
		else return resultSet;
	}

}
