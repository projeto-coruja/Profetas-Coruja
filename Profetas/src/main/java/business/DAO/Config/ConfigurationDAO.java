package business.DAO.Config;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.Configuration;
import persistence.dto.DTO;
import persistence.util.DataAccessLayerException;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class ConfigurationDAO {

	private PersistenceAccess manager;
	
	public ConfigurationDAO(){
		manager = new PersistenceAccess();
	}
	
	public void addPropertie(String entry, String value) throws UnreachableDataBaseException{
		Configuration newConf = new Configuration(entry,value);
		try{
			manager.saveEntity(newConf);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public void removeEntry(String entry) throws UnreachableDataBaseException, ConfigNotFoundException{
		List<DTO> resultSet = null;
		try{
			resultSet = manager.findEntity("from ConfigurationMO where entry = '" + entry +"'");
			if(resultSet == null) {
				throw new ConfigNotFoundException("Entrada inexistente.");
			}
			for(DTO check : resultSet){
				if(((Configuration)check).getEntry().equals(entry)) manager.deleteEntity(check);
			}
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public Configuration getEntry(String entry) throws UnreachableDataBaseException, ConfigNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ConfigurationMO where entry = '" + entry +"'");
			if(resultSet == null) {
				throw new ConfigNotFoundException("Entrada inexistente.");
			}
			else return (Configuration) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<DTO> getAllEntries() throws UnreachableDataBaseException, ConfigNotFoundException{
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ConfigurationMO order by entry");
			if(resultSet == null) {
				throw new ConfigNotFoundException("Entrada inexistente.");
			}
			return resultSet;
		}catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
}
