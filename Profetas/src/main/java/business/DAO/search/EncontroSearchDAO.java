package business.DAO.search;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Encontro;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.EncounterNotFoundException;
import datatype.SimpleDate;

public class EncontroSearchDAO {
	private PersistenceAccess manager;
	public EncontroSearchDAO(){
		manager = new PersistenceAccess();
	}
	
	/**
	 * Pesquisa por um  Encontro usando a data
	 * @param data - data do encontro
	 * @throws UnreachableDataBaseException
	 * @throws EncounterNotFoundException
	 */
	public Encontro findEncontroByData(SimpleDate data) throws EncounterNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			
			resultSet = manager.findEntity("FROM encontroMO WHERE data = '"+ data +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  EncounterNotFoundException ("Encontro não encontrado.");
			}
			else{
				
				return  (Encontro) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um Encontro usando o local
	 * @param id - id do local do encontro
	 * @throws UnreachableDataBaseException
	 * @throws EncounterNotFoundException
	 */
	public Encontro findEncontroByLocal (long id) throws EncounterNotFoundException, UnreachableDataBaseException{
		List<DTO> resultSet = null;
		try {
			
			resultSet = manager.findEntity("FROM encontroMO WHERE local_id = '"+ id +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  EncounterNotFoundException ("Encontro não encontrado.");
			}
			else{
				
				return  (Encontro) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	

}
