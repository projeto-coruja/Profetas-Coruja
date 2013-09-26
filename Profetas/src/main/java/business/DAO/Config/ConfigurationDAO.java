package business.DAO.Config;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Configuration;
import persistence.model.IdentifiedEntity;
import persistence.util.DataAccessLayerException;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.general.DuplicatedEntryException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * DAO de configurações. 
 */
public class ConfigurationDAO {

	private EntityManager manager;
	/**
	 * Construtor
	 */
	public ConfigurationDAO(){
		manager = new EntityManager();
	}
	/**
	 * Adiciona uma nova entrada de configuração no banco de dados
	 * @param entry - Nome da entrada (não pode ser nulo nem repetido).
	 * @param value - Valor da entrada.
	 * @throws DuplicatedEntryException - Exceção caso já exista entrada com o mesmo nome.
	 * @throws UnreachableDataBaseException
	 */
	public void addProperty(String entry, String value) throws UnreachableDataBaseException, DuplicatedEntryException{
		try{
			getEntry(entry);
			throw new DuplicatedEntryException("Entrada já existe!");
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		} catch (ConfigNotFoundException e) {
			manager.save(new Configuration(entry,value));
		}
	}
	
	/**
	 * Adiciona uma nova entrada de configuração no banco de dados
	 * @param entry - Nome da entrada.
	 * @param value - Valor da entrada.
	 * @throws ConfigNotFoundException - Exceção caso não exista entrada com o nome.
	 * @throws UnreachableDataBaseException
	 */
	public void updateProperty(String entry, String value) throws UnreachableDataBaseException, ConfigNotFoundException{
		try{
			Configuration c = getEntry(entry);
			c.setValue(value);
			manager.update(c);
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
		try {
			Configuration result = manager.find(Configuration.class, entry);
			if(result == null) {
				throw new ConfigNotFoundException("Entrada inexistente.");
			}
			else manager.delete(result);
		} catch (DataAccessLayerException e) {
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
		try {
			Configuration result = manager.find(Configuration.class, entry);
			if(result == null) {
				throw new ConfigNotFoundException("Entrada inexistente.");
			}
			else return result;
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
	public List<IdentifiedEntity> getAllEntries() throws UnreachableDataBaseException, ConfigNotFoundException{
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from Configuration order by entry");
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
