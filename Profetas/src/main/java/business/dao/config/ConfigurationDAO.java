package business.dao.config;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Configuration;
import persistence.util.DataAccessLayerException;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * DAO de configurações.
 */
public class ConfigurationDAO {

    private EntityManager manager;

    /**
     * Construtor
     */
    public ConfigurationDAO(EntityManager manager) {
	this.manager = manager;
    }

    /**
     * Busca uma determinada entrada.
     * 
     * @param entry
     *            - Nome da entrada.
     * @return instancia de <b>Configuration</b>.
     * @throws UnreachableDataBaseException
     */
    public Configuration getEntry(String entry) throws UnreachableDataBaseException {
	try {
	    return manager.findByNaturalId(Configuration.class, entry);
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    /**
     * Pega todas as entradas presentes no banco de dados.
     * 
     * @return Lista contendo todas as entradas presentes no banco de dados.
     * @throws UnreachableDataBaseException
     * @throws ConfigNotFoundException
     */
    public List<Configuration> getAllEntries() throws UnreachableDataBaseException, ConfigNotFoundException {
	try {
	    return manager.find("from Configuration order by entry");
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }
}
