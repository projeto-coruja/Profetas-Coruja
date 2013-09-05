package business.DAO.Config;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.Configuration;
import persistence.dto.DTO;
import persistence.util.DataAccessLayerException;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.general.DuplicatedEntryException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * DAO de configurações. 
 */
public class ConfigurationDAO {

	private PersistenceAccess manager;
	/**
	 * Construtor
	 */
	public ConfigurationDAO(){
		manager = new PersistenceAccess();
	}
	/**
	 * Adiciona uma nova entrada de configuração no banco de dados
	 * @param entry - Nome da entrada (não pode ser nulo nem repetido).
	 * @param value - Valor da entrada.
	 * @throws DuplicatedEntryException - Exceção caso já exista entrada com o mesmo nome.
	 * @throws UnreachableDataBaseException
	 */
	public void addPropertie(String entry, String value) throws UnreachableDataBaseException, DuplicatedEntryException{
		try{
			getEntry(entry);
			throw new DuplicatedEntryException("Entrada já existe!");
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		} catch (ConfigNotFoundException e) {
			manager.saveEntity(new Configuration(entry,value));
		}
	}
	
	/**
	 * Adiciona uma nova entrada de configuração no banco de dados
	 * @param entry - Nome da entrada.
	 * @param value - Valor da entrada.
	 * @throws ConfigNotFoundException - Exceção caso não exista entrada com o nome.
	 * @throws UnreachableDataBaseException
	 */
	public void updatePropertie(String entry, String value) throws UnreachableDataBaseException, ConfigNotFoundException{
		try{
			Configuration c = getEntry(entry);
			c.setValue(value);
			manager.updateEntity(c);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Remove uma entrada de configuração.
	 * @param entry - Nome da entrada a ser removido.
	 * @throws UnreachableDataBaseException
	 * @throws ConfigNotFoundException
	 */
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
	
	/**
	 * Busca uma determinada entrada.
	 * @param entry - Nome da entrada.
	 * @return instancia de <b>Configuration</b>.
	 * @throws UnreachableDataBaseException
	 * @throws ConfigNotFoundException
	 */
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
	
	/**
	 * Pega todas as entradas presentes no banco de dados. 
	 * @return Lista de <b>DTO</b> contendo todas as entradas presentes no banco de dados.
	 * @throws UnreachableDataBaseException
	 * @throws ConfigNotFoundException
	 */
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
